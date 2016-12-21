package nami.testproject;

import android.app.Application;

public abstract class NamiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public abstract void init();
}