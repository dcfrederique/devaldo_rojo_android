package minivoetbal.devaldorojo;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Frederique on 2/12/2014.
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "KvXR4nBYYeZ4BSJCC5s7AInIHL2qjDZtMi9wFa1A", "aGdu1unoCDxuAdKysoDnUPNzYbZt41LHnMW6MRet");

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}