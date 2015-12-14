package name.bagi.levente.pedometer.util;

/**
 * Created by EREN on 04.12.2015.
 */
import android.app.Activity;
import android.content.Intent;

import name.bagi.levente.pedometer.ui.AccessTokenActivity;

/**
 * Created by kirkita on 06.10.15.
 */
public class IntentUtil {

    private Activity activity;

    // constructor
    public IntentUtil(Activity activity) {
        this.activity = activity;
    }

    public void showAccessToken() {
        Intent i = new Intent(activity, AccessTokenActivity.class);
        activity.startActivity(i);
    }
}
