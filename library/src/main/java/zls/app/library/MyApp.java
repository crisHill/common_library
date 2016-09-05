package zls.app.library;

import android.app.Application;
import android.content.Context;

/**
 * Created by zls on 2016/9/2.
 */
public class MyApp extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();
    }
}
