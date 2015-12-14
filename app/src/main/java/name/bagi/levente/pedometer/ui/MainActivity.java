package name.bagi.levente.pedometer.ui;

/**
 * Created by EREN on 04.12.2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

import name.bagi.levente.pedometer.Main;
import name.bagi.levente.pedometer.R;
import name.bagi.levente.pedometer.encap.UserType;
import name.bagi.levente.pedometer.util.IntentUtil;
import name.bagi.levente.pedometer.util.PrefUtil;


public class MainActivity extends Activity {

    private CallbackManager callbackManager;
    private TextView info;
    private ImageView profileImgView;
    private LoginButton loginButton;

    private PrefUtil prefUtil;
    private IntentUtil intentUtil;

    SharedPreferences.Editor editor;
    UserType usertype;
    //gpuserid degisecek
    String personName, email, personPhotoUrl, gpUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        prefUtil = new PrefUtil(this);
        intentUtil = new IntentUtil(this);

        info = (TextView) findViewById(R.id.info);
        profileImgView = (ImageView) findViewById(R.id.profile_img);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        usertype = new UserType();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                info.setText(message(profile));

                String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();
                String userid = loginResult.getAccessToken().getUserId();
                Log.e("SHA: ", userid);


                usertype.setUserType(2);

                gpUserID = userId;
                personName = Profile.getCurrentProfile().getName();
                personPhotoUrl = (Profile.getCurrentProfile().getProfilePictureUri(200, 200)).toString();
                email = Profile.getCurrentProfile().getName();



                editor = getSharedPreferences("LOGINDATA", MODE_PRIVATE).edit();
                editor.putString("NAME", personName);
                editor.putString("EMAIL", email);
                editor.putString("PHOTOURL", personPhotoUrl);
                editor.putString("GPUSERID", userId);
                editor.putString("USERTYPE", "Facebook");

                editor.commit();



                System.out.print(userid);

                // save accessToken to SharedPreference
                prefUtil.saveAccessToken(accessToken);

                String profileImgUrl = "https://graph.facebook.com/" + userId + "/picture?type=large";


                Glide.with(MainActivity.this)
                        .load(profileImgUrl)
                        .into(profileImgView);

                Intent intentReport = new Intent(MainActivity.this, Main.class);
                startActivity(intentReport);

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
                info.setText("Login attempt failed.");
            }
        });




        /*
    //    List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
     //   loginButton.setReadPermissions(permissionNeeds);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("onSuccess");
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*???
        switch (item.getItemId()) {
            case R.id.action_show_access_token:
                intentUtil.showAccessToken();
                break;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        deleteAccessToken();
        Profile profile = Profile.getCurrentProfile();
        info.setText(message(profile));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private String message(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("Welcome ").append(profile.getName());
        }
        return stringBuffer.toString();
    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    //User logged out
                    prefUtil.clearToken();
                    clearUserArea();
                }
            }
        };
    }

    private void clearUserArea() {
        info.setText("");
        profileImgView.setImageDrawable(null);
    }






}
