package name.bagi.levente.pedometer.signinup;

/**
 * Created by EREN on 27.11.2015.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;



import name.bagi.levente.pedometer.JSONParser;
import name.bagi.levente.pedometer.Main;
import name.bagi.levente.pedometer.Map;
import name.bagi.levente.pedometer.Map2;
import name.bagi.levente.pedometer.MyProfile;
import name.bagi.levente.pedometer.R;

/**
 * Created by eray on 17.09.2015.
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputPassword;
    EditText inputMail;

    String sifre, email;


    // url to create new user
    private static String url_login_user = "http://api.teknoparbilisim.com/get_users.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Edit Text
        inputPassword = (EditText) findViewById(R.id.etLoginSifre);
        inputMail = (EditText) findViewById(R.id.etLoginEposta);



        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCheckLogin);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread

                sifre = inputPassword.getText().toString();
                email = inputMail.getText().toString();

                /*
                sifre = inputPassword.getText().toString();
                email = inputMail.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences("LoginScreen", MODE_PRIVATE).edit();
                editor.putBoolean("isLoggedIn", true);
                editor.commit();
*/
               new  LoginUser().execute();


                /*
                Intent intentMain = new Intent(Login.this, Main.class);
                startActivity(intentMain);
*/

                //     new LoginUser().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class LoginUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Üye Girişi Denetleniyor..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating user
         * */
        protected String doInBackground(String... args) {
            /*
            String ad = inputName.getText().toString();
            String soyad = inputSurname.getText().toString();
            String sifre = inputPassword.getText().toString();
            String email = inputMail.getText().toString();
*/

                    // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("EMAIL", email));
            params.add(new BasicNameValuePair("PASSWORD", sifre));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_login_user,"POST", params);

            // check log cat fro response
//                     Log.d("Create Response", json.toString());
            // check for success tag



            try {
                int success = json.getInt(TAG_SUCCESS);
                //  int success = 1;

                if (success == 1) {
                    // successfully created product

                    Intent i = new Intent(getApplicationContext(), MyProfile.class);
                    startActivity(i);

                    // closing this screen
                    //       finish();
                } else {
                    // failed to create product
                    Intent l = new Intent(getApplicationContext(), Login.class);
                    startActivity(l);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}