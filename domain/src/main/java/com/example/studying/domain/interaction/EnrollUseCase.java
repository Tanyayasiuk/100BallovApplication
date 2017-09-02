package com.example.studying.domain.interaction;

import com.example.studying.domain.entity.Student;
import com.example.studying.domain.interaction.base.UseCase;

import io.reactivex.Observable;


public class EnrollUseCase extends UseCase<Student, Boolean> {
    @Override
    protected Observable<Boolean> buildUseCase(Student student) {
        return null;
    }
}
