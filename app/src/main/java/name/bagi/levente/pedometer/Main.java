package name.bagi.levente.pedometer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import name.bagi.levente.pedometer.encap.UserType;
import name.bagi.levente.pedometer.imageurl.ImageLoader;
import name.bagi.levente.pedometer.report.Report;
import name.bagi.levente.pedometer.report.StepReport2;
import name.bagi.levente.pedometer.signinup.GooglePlusLoginActivity;
import name.bagi.levente.pedometer.signinup.LoginScreen;
import name.bagi.levente.pedometer.ui.MainActivity;


public class Main extends Activity {

    //////////
    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

   // String ad,soyad, sifre, email;

    // url to create new user
    private static String url_create_gpuser = "http://api.teknoparbilisim.com/create_gpuser.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
///////////////////

    UserType usertypeenc;


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    Integer imgid[] = {R.drawable.profilim, R.drawable.zamantuneli,  R.drawable.raporlar,R.drawable.stepicon, R.drawable.icon_options, R.drawable.icon_exit};
    ListView list;


    TextView txtAd, txtEmail;
    TextView txtgpID ,txtUserType;

    //sol drawer
    ImageView ustlogo;
    TextView ad;
    TextView soyad;

    //kullanilmadi
    private static boolean isLoggedIn = false;

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        Main.isLoggedIn = isLoggedIn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilim);

        txtAd = (TextView) findViewById(R.id.txtgpProfilAd);
        txtEmail = (TextView) findViewById(R.id.txtgpEmail);
        txtgpID= (TextView) findViewById(R.id.txtgpId);
        txtUserType= (TextView) findViewById(R.id.txtUserType);

        usertypeenc = new UserType();


        /*
        SharedPreferences prefs = getSharedPreferences("GooglePlusUserData", MODE_PRIVATE);
        String gpname = prefs.getString("NAME", null);
        String gpemail = prefs.getString("EMAIL", null);
        String gpphotourl = prefs.getString("PHOTOURL", null);
        String gpuserid = prefs.getString("GPUSERID", null);
*/
        SharedPreferences prefs = getSharedPreferences("LOGINDATA", MODE_PRIVATE);
        String gpname = prefs.getString("NAME", null);
        String gpemail = prefs.getString("EMAIL", null);
        String gpphotourl = prefs.getString("PHOTOURL", null);
        String gpuserid = prefs.getString("GPUSERID", null);

        String usertype = prefs.getString("USERTYPE", null);

        if(usertype == null)
        {
            usertype = "Kullanıcı Kayıt Oldu.";
        }
////////////////
        txtAd.setText(gpname);
        txtEmail.setText(gpemail);
        txtgpID.setText(gpuserid);

        txtUserType.setText(usertype);
////////////
        /*
        ad = (TextView) findViewById(R.id.txtAd);
        ustlogo = (ImageView)findViewById(R.id.ustLogo);

        ad.setText(gpname);
*/

        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.profilResmi);
        // Image url
      //  String image_url = "https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male2-64.png";
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        // whenever you want to load an image from url
        imgLoader.DisplayImage(gpphotourl, loader, image);




        /**
         * Sol Drawer
         * **/

        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);


        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        String[] itemname ={
                "Profilim",
                "Zaman Tüneli",
                "Raporlar",
                "Adım Sayar",
                "Ayarlar",
                "Çıkış"
        };

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.navdrawer);
        list.setAdapter(adapter);

        /*
        mDrawerList.setAdapter(new ArrayAdapter<String>(
                this, R.layout.mylist,
                R.id.Itemname,values));
*/
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentProfilim = new Intent(Main.this, Main.class);
                        startActivity(intentProfilim);
                        break;
                    case 1:
                        Intent intentZamanTuneli = new Intent(Main.this, ImageUploadTest.class);
                        startActivity(intentZamanTuneli);
                        break;
                    case 2:
                        Intent intentReport = new Intent(Main.this, Report.class);
                        startActivity(intentReport);
                        break;

                    case 3:
                        Intent intent = new Intent(Main.this, Pedometer.class);
                        startActivity(intent);
                        break;

                    case 4:
                        Intent ayarlarIntent = new Intent(Main.this, Map.class);
                        startActivity(ayarlarIntent);
                        break;
                    case 5:

                            clearSharedPreference(getApplicationContext());
                            exitGooglePlus();
                        if(usertypeenc.getUserType() == 1) {
                            usertypeenc.setExit(1);
                            Intent kayitOl = new Intent(Main.this, GooglePlusLoginActivity.class);
                            startActivity(kayitOl);
                        }
                        else if(usertypeenc.getUserType() == 2){
                            usertypeenc.setExit(1);
                            Intent kayitOl = new Intent(Main.this, MainActivity.class);
                            startActivity(kayitOl);
                        }
                        else {
                            Intent kayitOl = new Intent(Main.this, LoginScreen.class);
                            startActivity(kayitOl);
                        }
                            break;

                }

            }
        });
    }


    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences("LoginScreen", Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }


    public void exitGooglePlus() {
        GooglePlusLoginActivity gps = new GooglePlusLoginActivity();

        try {
            gps.onStop();
        }catch (Exception e)
        {
            Log.e("google plus onstop hatasi: " , e.toString());

        }
        try {

            gps.signOutFromGplus();

        }catch (Exception e)
        {
            Log.e("google plus logout hatasi: " , e.toString());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }











    //db postgresql islemi
    /**
     * Background Async Task to Create new product
     * */
    class CreateNewGPUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Main.this);
            pDialog.setMessage("GP Üye Kaydı Yapılıyor..");
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
            params.add(new BasicNameValuePair("AD", "asd"));
            params.add(new BasicNameValuePair("SOYAD", "asd"));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_gpuser,"POST", params);

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

