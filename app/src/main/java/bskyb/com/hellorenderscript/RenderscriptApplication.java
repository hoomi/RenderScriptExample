package bskyb.com.hellorenderscript;

import android.app.Application;

import bskyb.com.hellorenderscript.di.ApplicationComponent;
import bskyb.com.hellorenderscript.di.ApplicationModule;
import bskyb.com.hellorenderscript.di.DaggerApplicationComponent;

/**
 * Created by hos05 on 5/23/16.
 */
public class RenderscriptApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, BuildConfig.DEBUG))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
