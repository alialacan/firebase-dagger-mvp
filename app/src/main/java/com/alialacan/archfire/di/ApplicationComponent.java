package com.alialacan.archfire.di;

import com.alialacan.archfire.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<App> {

    void inject(App application);

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
