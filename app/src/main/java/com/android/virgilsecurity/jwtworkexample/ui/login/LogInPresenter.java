package com.android.virgilsecurity.jwtworkexample.ui.login;

import com.android.virgilsecurity.jwtworkexample.ui.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by Danylo Oliinyk on 3/22/18 at Virgil Security.
 * -__o
 */

public class LogInPresenter implements BasePresenter {

    private CompositeDisposable compositeDisposable;
    private LogInInteractor logInInteractor;

    public LogInPresenter(LogInInteractor logInInteractor) {
        this.logInInteractor = logInInteractor;
    }

    public void fireLogIn() {
        logInInteractor.onSuccess();
        logInInteractor.onError();
    }

    @Override public void disposeAll() {
        compositeDisposable.clear();
    }
}
