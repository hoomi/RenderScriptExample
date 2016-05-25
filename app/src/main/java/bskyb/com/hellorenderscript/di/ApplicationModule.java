package bskyb.com.hellorenderscript.di;

import android.app.Application;
import android.os.Build;
import android.support.v8.renderscript.RenderScript;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hos05 on 5/23/16.
 */
@Module
public class ApplicationModule {
    private final Application application;
    private final boolean debug;

    public ApplicationModule(Application application, boolean debug) {
        this.application = application;
        this.debug = debug;
    }

    @Provides
    @Singleton
    public RenderScript providesRenderScript() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return RenderScript.create(application, (debug ? RenderScript.ContextType.DEBUG : RenderScript.ContextType.NORMAL));
        } else {
            return RenderScript.create(application);
        }
    }
}
