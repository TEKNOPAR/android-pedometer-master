package name.bagi.levente.pedometer.signinup;

/**
 * Created by EREN on 18.11.2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import name.bagi.levente.pedometer.JSONParser;
import name.bagi.levente.pedometer.Main;
import name.bagi.levente.pedometer.MyProfile;
import name.bagi.levente.pedometer.R;

/**
 * Created by eray on 17.09.2015.
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.EditText;

public class SignUp extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputName;
    EditText inputSurname;
    EditText inputPassword;
    EditText inputMail;

    String ad,soyad, sifre, email;


    // url to create new user
    private static String url_create_user = "http://api.teknoparbilisim.com/create_user.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Edit Text
        inputName = (EditText) findViewById(R.id.etAd);
        inputSurname = (EditText) findViewById(R.id.etSoyad);
        inputPassword = (EditText) findViewById(R.id.etLoginSifre);
        inputMail = (EditText) findViewById(R.id.etLoginEposta);



        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnUyeOl);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread

                ad = inputName.getText().toString();
                soyad = inputSurname.getText().toString();
                sifre = inputPassword.getText().toString();
                email = inputMail.getText().toString();

                new CreateNewUser().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SignUp.this);
            pDialog.setMessage("Üye Kaydı Yapılıyor..");
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
            params.add(new BasicNameValuePair("AD", ad));
            params.add(new BasicNameValuePair("SOYAD", soyad));
            params.add(new BasicNameValuePair("SIFRE", sifre));
            params.add(new BasicNameValuePair("EMAIL", email));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_user,"POST", params);

            // check log cat fro response
   //         Log.d("Create Response", json.toString());
            // check for success tag
            try {
               int success = json.getInt(TAG_SUCCESS);
              //  int success = 1;

                if (success == 1) {
                    // successfully created product

                    Intent i = new Intent(getApplicationContext(), Main.class);
                    startActivity(i);

                    // closing this screen
             //       finish();
                } else {
                    // failed to create product
                    Intent l = new Intent(getApplicationContext(), MyProfile.class);
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