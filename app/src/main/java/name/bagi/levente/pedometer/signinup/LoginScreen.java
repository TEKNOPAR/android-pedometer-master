package name.bagi.levente.pedometer.signinup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.hoang8f.widget.FButton;
import name.bagi.levente.pedometer.FacebookLoginActivity;
import name.bagi.levente.pedometer.Main;
import name.bagi.levente.pedometer.MyApplication;
import name.bagi.levente.pedometer.R;
import name.bagi.levente.pedometer.ui.MainActivity;

/**
 * Created by EREN on 19.11.2015.
 */
public class LoginScreen extends Activity {

    Button login, btnSignUp;
    FButton btnGooglePlus, btnFacebook;
    Context context;
    Boolean isLogged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreem);



        login = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnGooglePlus = (FButton) findViewById(R.id.btnGooglePlus);
        btnFacebook = (FButton) findViewById(R.id.btnFacebook);


        SharedPreferences prefs = getSharedPreferences("LoginScreen", MODE_PRIVATE);
        Boolean isLogged = prefs.getBoolean("isLoggedIn", false);

        btnGooglePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentLogin = new Intent(LoginScreen.this, GooglePlusLoginActivity.class);
                startActivity(intentLogin);

            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentFacebook = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(intentFacebook);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentLogin = new Intent(LoginScreen.this, Login.class);
                startActivity(intentLogin);

            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMain = new Intent(LoginScreen.this, SignUp.class);
                startActivity(intentMain);


            }
        });


        if(isLogged)
        {
            Intent intentMain = new Intent(LoginScreen.this, Main.class);
            startActivity(intentMain);
        }



    }

    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGooglePlus:



           Intent intentLogin = new Intent(LoginScreen.this, GooglePlusLoginActivity.class);
                        startActivity(intentLogin);



        }
    }
*/
    }
