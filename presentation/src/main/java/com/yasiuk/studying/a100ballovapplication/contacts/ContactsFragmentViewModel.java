package com.yasiuk.studying.a100ballovapplication.contacts;


import android.app.Activity;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.yasiuk.studying.a100ballovapplication.base.BaseViewModel;
import com.yasiuk.studying.domain.entity.Contact;
import com.yasiuk.studying.domain.interaction.GetContactsUseCase;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ContactsFragmentViewModel implements BaseViewModel {

    private Activity activity;

    public ContactsFragmentViewModel(Activity activity) {
        this.activity = activity;
    }

    public enum STATE {PROGRESS, DATA};
    public ObservableField<STATE> state = new ObservableField<>(STATE.PROGRESS);

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> phones = new ObservableField<>("");
    public ObservableField<String> schedule = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> bank = new ObservableField<>("");

    private GetContactsUseCase useCase = new GetContactsUseCase();

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {
        useCase.execute("contact", new DisposableObserver<Contact>() {
            @Override
            public void onNext(@NonNull Contact contact) {
                title.set(contact.getTitle());
                address.set(contact.getAddress());
                phones.set(contact.getPhone());
                email.set(contact.getEmail());
                schedule.set(contact.getSchedule());
                bank.set(contact.getBank());
                Log.e("SSS", bank.toString());
                state.set(STATE.DATA);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("SSS", "Error: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pause() {
        useCase.dispose();
    }
}
