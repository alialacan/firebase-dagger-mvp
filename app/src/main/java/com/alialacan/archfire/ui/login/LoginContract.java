package com.alialacan.archfire.ui.login;

import com.alialacan.archfire.base.BasePresenter;
import com.alialacan.archfire.base.BaseView;
import com.google.firebase.auth.FirebaseUser;

public class LoginContract {
    public interface LoginView extends BaseView {
        void onCheckLogin(boolean isLoggedIn);

        void onLoginResult(boolean isSuccess, FirebaseUser user);

        void onRegisterResult(boolean isSuccess, FirebaseUser user);
    }

    public interface LoginPresenter<V extends LoginView> extends BasePresenter<V> {
        void checkLogin();

        void login(String email, String pass);

        void register(String email, String pass);
    }
}