package com.example.studying.domain.interaction.base;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class UseCase<InParam, OutParam> {
    private Disposable disposable;

    protected abstract Observable<OutParam> buildUseCase(InParam param);

    public void execute(InParam param, DisposableObserver<OutParam> disposableObserver){
        disposable = buildUseCase(param)
                .observeOn(AndroidSchedulers.mainThread()) //то, где будет обработан ответ
                .subscribeOn(Schedulers.newThread()) //то, где будет выполнен код
                .subscribeWith(disposableObserver);
        //В итоге выполняться все будет в одном потоке, а ответы будут подаваться в поток UI
        //не выполняется, пока на него кто-нибудь не подпишется
    }

    public void dispose(){
        if (!disposable.isDisposed() && disposable!=null){
            Log.e("CCC", "Disposing");
            disposable.dispose();
        }
    }

}
