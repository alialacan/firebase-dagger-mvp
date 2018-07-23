package com.alialacan.archfire.ui.login;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginActivityModule {
    @Binds
    abstract LoginContract.LoginPresenter<LoginContract.LoginView> providePresenter(LoginPresenterImpl<LoginContract.LoginView> presenter);
}
