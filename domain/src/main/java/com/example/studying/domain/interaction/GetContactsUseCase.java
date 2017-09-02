package com.example.studying.domain.interaction;


import android.util.Log;

import com.example.studying.data.entity.ContactProfile;
import com.example.studying.data.net.RestService;
import com.example.studying.domain.entity.Contact;
import com.example.studying.domain.interaction.base.UseCase;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class GetContactsUseCase extends UseCase<String, Contact> {

    @Override
    protected Observable<Contact> buildUseCase(String s) {
        return RestService.getInstance().getContact(s).map(new Function<ContactProfile, Contact>() {
            @Override
            public Contact apply(@NonNull ContactProfile contactProfile) throws Exception {
                Contact contact = new Contact();
                contact.setTitle(contactProfile.getTitle());
                contact.setAddress(contactProfile.getAddress());
                contact.setPhone(contactProfile.getPhone());
                contact.setSchedule(contactProfile.getSchedule());
                contact.setEmail(contactProfile.getEmail());
                contact.setBank(contactProfile.getBank());
                return contact;
            }
        });
    }
}