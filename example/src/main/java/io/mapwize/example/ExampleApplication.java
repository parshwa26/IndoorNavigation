package io.mapwize.example;

import android.app.Application;

import io.mapwize.mapwize.MWZAccountManager;

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MWZAccountManager.start(this, "dee8d4216f051e86b92f64493c9dfce6"); // PASTE YOU API KEY HERE !!! This is a demo key. It is not allowed to use it for production. The key might change at any time without notice.
    }

}
