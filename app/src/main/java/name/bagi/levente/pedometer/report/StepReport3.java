package name.bagi.levente.pedometer.report;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;



import java.util.ArrayList;
import java.util.List;

import name.bagi.levente.pedometer.CustomListAdapter;
import name.bagi.levente.pedometer.Pedometer;
import name.bagi.levente.pedometer.R;

/**
 * Created by EREN on 04.11.2015.
 */
public class StepReport3 extends Activity {

    Context context;
    ArrayList <String> al;


    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepreport3);


        al = new ArrayList();


        // add elements to the array list
        al.add("Cdsa");
        al.add("Ads");
        al.add("Ed");
        al.add("Bd");
        al.add("Dd");
        al.add("Fdsa");
        al.add("fdfd");
        al.add("ff");


        LineChart chart = (LineChart) findViewById(R.id.chart);

        //    LineData data = new LineData(getXAxisValues(), getDataSet());
        LineData data = new LineData(al, getDataSet());

        chart.setData(data);
        chart.setDescription("Atılan Adım");
        chart.animateXY(2000, 2000);
        chart.invalidate();


        /**
         * Sol Drawer
         * **/

        //////////////////////////////////////////



        //////////////////////////////////////////

        /////////////////////////////////

    }
























    private ArrayList<LineDataSet> getDataSet() {
        ArrayList<LineDataSet> dataSets = null;

        ArrayList<Entry> valueSet1 = new ArrayList<>();
        Entry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        Entry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        Entry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        Entry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        Entry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        Entry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        /*
        ArrayList<Entry> valueSet2 = new ArrayList<>();
        Entry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        Entry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        Entry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        Entry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        Entry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        Entry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);
*/

        /*
        LineDataSet lineDataSet1 = new LineDataSet(valueSet1, "Brand 1");
        lineDataSet1.setColor(Color.rgb(0, 155, 0));
        LineDataSet lineDataSet2 = new LineDataSet(valueSet2, "Brand 2");
        lineDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
*/

        LineDataSet lineDataSet1 = new LineDataSet(valueSet1, "#Steps");
        lineDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        //     dataSets.add(lineDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        xAxis.add("11-01-2015");
        xAxis.add("11-02-2015");
        xAxis.add("11-03-2015");
        xAxis.add("11-04-2015");
        xAxis.add("11-05-2015");
        xAxis.add("11-06-2015");




/*
        try {
            for (int i = 0; i < 6; i++) {
                xAxis.add((al.get(i)).toString());
            }
        }catch (Exception e)
        {

        }
  */

        return xAxis;
    }

}
