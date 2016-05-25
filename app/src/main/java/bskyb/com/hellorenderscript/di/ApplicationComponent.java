package bskyb.com.hellorenderscript.di;

import javax.inject.Singleton;

import bskyb.com.hellorenderscript.view.CustomRenderScriptView;
import dagger.Component;

/**
 * Created by hos05 on 5/23/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(CustomRenderScriptView renderScriptView);
}
