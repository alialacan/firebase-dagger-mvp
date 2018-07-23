package com.alialacan.archfire.di;

import android.content.Context;

import com.alialacan.archfire.App;
import com.alialacan.archfire.UserModel;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {
    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    UserModel provideUser() {

        return getCurrentUser();
    }

    private UserModel getCurrentUser() {
        UserModel user = new UserModel();
        user.setUid("1");
        user.setUserName("alialacan");
        user.setPass("123");
        return user;
    }
}
