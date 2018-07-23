package com.alialacan.archfire.ui.login;

import com.alialacan.archfire.CommonPresenter;
import com.alialacan.archfire.UserModel;

import javax.inject.Inject;

import dagger.Lazy;

public class LoginPresenterImpl<V extends LoginContract.LoginView> extends CommonPresenter<V>
        implements LoginContract.LoginPresenter<V> {

    private UserModel user;

    @Inject
    LoginPresenterImpl(Lazy<UserModel> _user) {
        user = _user.get();
    }

    @Override
    public void login(String email, String pass) {
        if (user.getPass().equals(pass) || user.getEmail().equals(email)) {
            getView().onLoginResult(true);
        }
    }

    @Override
    public void register(String email, String pass) {

    }
}
