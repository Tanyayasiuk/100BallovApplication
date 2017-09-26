package com.example.studying.a100ballovapplication.enroll;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studying.a100ballovapplication.MainActivity;
import com.example.studying.a100ballovapplication.MyApplication;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseViewModel;
import com.example.studying.a100ballovapplication.news.NewsActivity;
import com.example.studying.domain.entity.EnrollDomain;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.interaction.EnrollUseCase;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;


public class EnrollFragmentViewModel implements BaseViewModel {

    private Activity activity;
    public enum STATE {PROGRESS, DATA};
    private final String TEXTMESSAGE = "textmessage";

    public ObservableField<String> parentSurname = new ObservableField<>("");
    public ObservableField<String> parentPhone = new ObservableField<>("");
    public ObservableField<String> emailInput = new ObservableField<>("");
    public ObservableField<String> studentName = new ObservableField<>("");
    public ObservableField<String> studentPhone = new ObservableField<>("");

    private Spinner spinner;

    @Inject
    public EnrollUseCase useCase;

    public ObservableField<STATE> state = new ObservableField<>(STATE.DATA);

    public EnrollFragmentViewModel(Activity activity) {
        MyApplication.appComponent.inject(this);
        this.activity = activity;
    }

    @Override
    public void init() {}

    @Override
    public void release() {}

    @Override
    public void resume() {

        Button enrollButton = (Button) activity.findViewById(R.id.enroll_button);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                spinner = (Spinner) activity.findViewById(R.id.spinner_enroll);

                if(isDataFull()){
                    if(isEmailValid(emailInput.get())){
                        HashMap<String, String> map = new HashMap<>();
                        map.put(TEXTMESSAGE, buildLetter());
                        String[] to = new String[]{activity.getString(R.string.enrollment_email)};

                        EnrollDomain enrollDomain = new EnrollDomain();
                        enrollDomain.setSubject(activity.getString(R.string.new_enrollment_title));
                        enrollDomain.setBodyparts(map);
                        enrollDomain.setTo(to);
                        state.set(STATE.PROGRESS);

                        useCase.execute(enrollDomain, new DisposableObserver<OkDomain>() {
                            @Override
                            public void onNext(@NonNull OkDomain okDomain) {
                                state.set(STATE.DATA);
                                Toast toast = Toast.makeText(activity, R.string.enrollment_success, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                Log.e("SSS", "OnNExt enroll useCase");
                                activity.startActivity(new Intent(activity, MainActivity.class));
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(activity, R.string.enroll_error, Toast.LENGTH_SHORT)
                                        .show();
                                Log.e("SSS", "ERROR enroll useCase " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() {
                                Log.e("SSS", "onComplete");
                                useCase.dispose();
                            }
                        });
                    } else {
                        showError(activity.getString(R.string.error_invalid_email));
                    }
                } else {
                    showError(activity.getString(R.string.error_fields_required));
                }


                    }
        });

    }

    @Override
    public void pause() {}

    private String buildLetter(){
        StringBuilder enrollmentBuilder = new StringBuilder();
        enrollmentBuilder.append(activity.getString(R.string.new_enrollment))
                .append("\n\n");
        enrollmentBuilder.append(activity.getString(R.string.parent_enroll))
                .append(" ")
                .append(parentSurname.get())
                .append("\n")
                .append(activity.getString(R.string.parent_phone_enroll))
                .append(" ")
                .append(parentPhone.get())
                .append("\n")
                .append(activity.getString(R.string.email))
                .append(" ")
                .append(emailInput.get())
                .append("\n")
                .append(activity.getString(R.string.student_enroll))
                .append(" ")
                .append(studentName.get())
                .append("\n")
                .append(activity.getString(R.string.student_phone_enroll))
                .append(" ")
                .append(studentPhone.get())
                .append("\n")
                .append(activity.getString(R.string.class_enroll))
                .append(spinner.getSelectedItem());
        Log.e("SSS", activity.getString(R.string.new_enrollment));
        return enrollmentBuilder.toString();
    }

    private boolean isDataFull(){
        return (!parentSurname.get().trim().equals("") &&
                !parentPhone.get().trim().equals("") &&
                !emailInput.get().trim().equals("") &&
                !studentName.get().trim().equals("") &&
                !studentPhone.get().trim().equals(""));
    }

    private boolean isEmailValid(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showError(String error){
        Toast toast = Toast.makeText(activity, error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
