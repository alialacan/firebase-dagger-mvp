package com.alialacan.archfire.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.alialacan.archfire.R;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.LoginView {

    @Inject
    LoginContract.LoginPresenter<LoginContract.LoginView> presenter;

    @BindView(R.id.etEmail)
    EditText etUserName;

    @BindView(R.id.etPass)
    EditText etPass;

    @Inject
    Lazy<FirebaseUser> user;


    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        if (TextUtils.isEmpty(etUserName.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString())) {
            Toast.makeText(this, "Not Valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading();
        presenter.login(etUserName.getText().toString(), etPass.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.attach(this);
        presenter.checkLogin();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onCheckLogin(boolean isLoggedIn) {
        showLoading();
        if (isLoggedIn) {
            Toast.makeText(this, "Already Signed In!", Toast.LENGTH_SHORT).show();
            // TODO: 23/07/2018 redirect
        } else {
            hideLoading();
        }
    }

    @Override
    public void onLoginResult(boolean isSuccess, FirebaseUser user) {
        if (isSuccess)
            Toast.makeText(this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();

        hideLoading();
        // TODO: 23/07/2018 redirect
    }

    @Override
    public void onRegisterResult(boolean isSuccess, FirebaseUser user) {
        if (isSuccess) {
            // TODO: 23/07/2018 redirect
            finish();
        } else {
            Toast.makeText(this, "Failed to register!", Toast.LENGTH_SHORT).show();
            hideLoading();
        }
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }
}
