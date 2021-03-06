package com.yasiuk.studying.domain.interaction;

import com.yasiuk.studying.data.entity.Enrollment;
import com.yasiuk.studying.data.net.RestService;
import com.yasiuk.studying.domain.entity.EnrollDomain;
import com.yasiuk.studying.domain.entity.OkDomain;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class EnrollUseCase extends UseCase<EnrollDomain, OkDomain> {

    @Inject
    public EnrollUseCase(){}

    @Override
    protected Observable<OkDomain> buildUseCase(EnrollDomain enroll) {
        return RestService.getInstance().enroll(convert(enroll))
                .map(new Function<Void, OkDomain>() {
                    @Override
                    public OkDomain apply(@NonNull Void aVoid) throws Exception {
                        return new OkDomain();
                    }
                });
    }

    private Enrollment convert (EnrollDomain enrollDomain){
        Enrollment data = new Enrollment();
        data.setSubject(enrollDomain.getSubject());
        data.setBodyparts(enrollDomain.getBodyparts());
        data.setTo(enrollDomain.getTo());
        return data;
    }
}
