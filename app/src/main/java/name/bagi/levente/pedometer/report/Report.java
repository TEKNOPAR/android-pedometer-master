package name.bagi.levente.pedometer.report;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View.OnClickListener;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


import android.os.AsyncTask;



import info.hoang8f.widget.FButton;
import name.bagi.levente.pedometer.JSONParser;
import name.bagi.levente.pedometer.R;
import name.bagi.levente.pedometer.signinup.GooglePlusLoginActivity;

/**
 * Created by EREN on 04.11.2015.
 */
public class Report extends Activity {

    /*
    json islemleri
     */

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "http://api.teknoparbilisim.com/extract_report.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    // products JSONArray
    JSONArray products = null;









    private TextView tvDisplayDate;
    private TextView tvDisplayDate2;

    private Button btnChangeDate;
    private Button btnChangeDate2;

    private RadioGroup radioChart;
    private RadioButton radioSexButton;


    FButton btnRaporla;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID2 = 998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepreport);

        /*
        json
         */
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();




        setCurrentDateOnView();
        addListenerOnButton();

        btnRaporla = (FButton) findViewById(R.id.btnRaporla);
        radioChart = (RadioGroup) findViewById(R.id.radioChart);



        btnRaporla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Loading products in Background Thread
       //         new LoadAllProducts().execute();


                // get selected radio button from radioGroup
                int selectedId = radioChart.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                if(radioSexButton.getText().equals("BarChart"))
                {
                    Intent intentStepReport2 = new Intent(Report.this, StepReport2.class);
                    startActivity(intentStepReport2);

                }
                else if(radioSexButton.getText().equals("LineChart"))
                {
                    Intent intentStepReport3 = new Intent(Report.this, StepReport3.class);
                    startActivity(intentStepReport3);

                }
                else if(radioSexButton.getText().equals("BubbleChart"))
                {
                    Intent intentStepReport3 = new Intent(Report.this, StepReportPieChart.class);
                    startActivity(intentStepReport3);

                }

                Toast.makeText(Report.this,radioSexButton.getText(), Toast.LENGTH_SHORT).show();


                /*
                Intent intentStepReport2 = new Intent(Report.this, StepReport2.class);
                startActivity(intentStepReport2);
                */


            }
        });

    }









    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Report.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
      //      Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
          //      int success = json.getInt(TAG_SUCCESS);

                if (true) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),StepReport3.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                }
            });

        }

    }


































    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        tvDisplayDate2 = (TextView) findViewById(R.id.tvDate2);

        //  dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into textview
        tvDisplayDate2.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnChangeDate2 = (Button) findViewById(R.id.btnChangeDate2);

        btnChangeDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

        btnChangeDate2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID2);

            }

        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,year, month,day);
            case DATE_DIALOG_ID2:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener2,year, month,day);

        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));


            /*
            // set selected date into textview
            tvDisplayDate2.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
*/
        }
    };

    //kisitlama yapilmadi??????????????????????????????????????????????????????
    private DatePickerDialog.OnDateSetListener datePickerListener2
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;


            Calendar cal = Calendar.getInstance();
            if(selectedYear <= cal.get(Calendar.YEAR))
            {
                year = selectedYear;
            }
            if(selectedYear == cal.get(Calendar.YEAR))
            {
                if(selectedMonth <= cal.get(Calendar.MONTH))
                {
                    month = selectedMonth;
                }
            }

            /*
            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
*/


            // set selected date into textview
            tvDisplayDate2.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

}