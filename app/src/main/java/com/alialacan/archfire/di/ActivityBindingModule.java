package com.alialacan.archfire.di;

import com.alialacan.archfire.ui.login.LoginActivity;
import com.alialacan.archfire.ui.login.LoginActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivity();

}
