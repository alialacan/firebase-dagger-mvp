package com.alialacan.archfire.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.alialacan.archfire.R;
import com.alialacan.archfire.UserModel;

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
    Lazy<UserModel> user;


    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        if (TextUtils.isEmpty(etUserName.getText().toString()) || TextUtils.isEmpty(etPass.getText().toString())) {
            Toast.makeText(this, "Not Valid", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onLoginResult(boolean isSuccess) {
        if (isSuccess)
            Toast.makeText(this, "Welcome " + user.get().getUserName(), Toast.LENGTH_SHORT).show();

        hideLoading();
    }

    @Override
    public void onRegisterResult(boolean isSuccess) {

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
