package com.alialacan.archfire.di;

import android.content.Context;

import com.alialacan.archfire.App;
import com.alialacan.archfire.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {
    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    FirebaseFirestore provideFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    @Nullable
    @Provides
    FirebaseUser provideUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
}
