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

import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.ScatterData;
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
public class StepReportPieChart extends Activity {

    Context context;
    ArrayList <String> al;


    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepreport_piechart);


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


        BubbleChart chart = (BubbleChart) findViewById(R.id.chart);

        //    LineData data = new LineData(getXAxisValues(), getDataSet());
        BubbleData data = new BubbleData(al, getDataSet());



        chart.setData(data);
        chart.setDescription("Atılan Adım");
        chart.animateXY(2000, 2000);
        chart.invalidate();


    }


    private ArrayList<BubbleDataSet> getDataSet() {
        ArrayList<BubbleDataSet> dataSets = null;

        ArrayList<BubbleEntry> valueSet1 = new ArrayList<>();
        BubbleEntry v1e1 = new BubbleEntry(0,3.5f, 0f); // Jan
        valueSet1.add(v1e1);
        BubbleEntry v1e2 = new BubbleEntry(1,40.000f, 40.000f); // Feb
        valueSet1.add(v1e2);
        BubbleEntry v1e3 = new BubbleEntry(2,60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BubbleEntry v1e4 = new BubbleEntry(3,30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BubbleEntry v1e5 = new BubbleEntry(4, 90.000f, 4); // May
        valueSet1.add(v1e5);
        BubbleEntry v1e6 = new BubbleEntry(5, 100.000f, 10.000f); // Jun
        valueSet1.add(v1e6);


        BubbleDataSet lineDataSet1 = new BubbleDataSet(valueSet1, "#Steps");
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





        return xAxis;
    }

}
