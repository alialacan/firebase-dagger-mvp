package com.alialacan.archfire.ui.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alialacan.archfire.CommonPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.Lazy;

public class LoginPresenterImpl<V extends LoginContract.LoginView> extends CommonPresenter<V>
        implements LoginContract.LoginPresenter<V> {

    private FirebaseUser user;

    @Inject
    LoginPresenterImpl(Lazy<FirebaseUser> firebaseUser) {
        user = firebaseUser.get();
    }

    @Override
    public void checkLogin() {
        getView().onCheckLogin(user != null);
    }

    @Override
    public void login(String email, String pass) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            return;
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            getView().onLoginResult(true, task.getResult().getUser());
                        } else {
                            getView().onLoginResult(false, null);
                        }
                    }
                });
    }

    @Override
    public void register(String email, String pass) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            return;
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            getView().onRegisterResult(true, task.getResult().getUser());
                        } else {
                            getView().onRegisterResult(false, null);
                        }
                    }
                });
    }
}
