package name.bagi.levente.pedometer.report;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;


import java.util.ArrayList;
import java.util.List;

import name.bagi.levente.pedometer.CustomListAdapter;
import name.bagi.levente.pedometer.Pedometer;
import name.bagi.levente.pedometer.R;

/**
 * Created by EREN on 04.11.2015.
 */
public class StepReport2 extends Activity {

    Context context;

    //drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    Integer imgid[] = {R.drawable.profilim, R.drawable.zamantuneli,  R.drawable.raporlar,R.drawable.stepicon, R.drawable.icon_options, R.drawable.icon_exit};
    ListView list;

    Button btnBarChart, btnPieChart;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepreport2);


        btnBarChart = (Button) findViewById(R.id.btnBarChart);

        /*
        SharedPreferences prefs = getSharedPreferences("LOGINDATA", MODE_PRIVATE);
        String gpuserid = prefs.getString("GPUSERID", null);
*/

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
                    case 1:
                    case 2:
                    case 3:
                        Intent intent = new Intent(StepReport2.this, Pedometer.class);
                        startActivity(intent);
                        break;

                    case 4:
                        Intent hakkimizdaIntent = new Intent(StepReport2.this, StepReport2.class);
                        startActivity(hakkimizdaIntent);
                        break;
                    case 5:
                        Intent ayarlarIntent = new Intent(StepReport2.this, StepReport2.class);
                        startActivity(ayarlarIntent);
                        break;
                }

            }
        });
        //////////////////////////////////////////

        btnBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepReport2.this, StepReport2.class);
                startActivity(intent);

            }
        });

        /*  hata veriyor?
        btnPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StepReport2.this, StepReport3.class);
                startActivity(intent);
            }
        });
*/


        //////////////////////////////////////////

        /////////////////////////////////

    ArrayList<BarEntry> entries = new ArrayList<>();
    entries.add(new BarEntry(4f, 0));
    entries.add(new BarEntry(8f, 1));
    entries.add(new BarEntry(6f, 2));
    entries.add(new BarEntry(12f, 3));
    entries.add(new BarEntry(18f, 4));
    entries.add(new BarEntry(9f, 5));

    BarDataSet dataset = new BarDataSet(entries, "# of Calls");

    dataset.setColors(ColorTemplate.COLORFUL_COLORS);

    ArrayList<String> labels = new ArrayList<String>();
    labels.add("January");
    labels.add("February");
    labels.add("March");
    labels.add("April");
    labels.add("May");
    labels.add("June");

    BarData data = new BarData(labels, dataset);
        /*
        BarChart chart = new BarChart(getApplicationContext());
        setContentView(chart);
*/
    BarChart chart = (BarChart) findViewById(R.id.chartmp);

    chart.setData(data);

    chart.setDescription("# of times Alice called Bob");
    chart.animateXY(2000, 2000);
    chart.invalidate();




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

}
