package com.alialacan.archfire.ui.login;

import com.alialacan.archfire.base.BasePresenter;
import com.alialacan.archfire.base.BaseView;

public class LoginContract {
    public interface LoginView extends BaseView {

        void onLoginResult(boolean isSuccess);

        void onRegisterResult(boolean isSuccess);
    }

    public interface LoginPresenter<V extends LoginView> extends BasePresenter<V> {
        void login(String email, String pass);

        void register(String email, String pass);
    }
}