package name.bagi.levente.pedometer;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import name.bagi.levente.pedometer.gps.GPSTracker;
import name.bagi.levente.pedometer.report.StepReport2;
import name.bagi.levente.pedometer.routedraw.GMapV2Direction;

public class Map extends Activity  {
   // static final LatLng TutorialsPoint = new LatLng(39.9402 , 32.8625);
    private GoogleMap mMap;

    //for getting gps data
    private Location mLastLocation;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    int value;
    String baslangicAdresi;

    public static ArrayList lang = new ArrayList();;
    public static ArrayList lat = new ArrayList();;

    Timer timer;
    public Map()
{

}


    ArrayList al = new ArrayList();
    Button btnSaveRoute;
    boolean saveroute = false;

    private ArrayList<LatLng> koordinatlar;



    private Button btnGpsGoster;
    GPSTracker gpstrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.harita);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        gpstrack = new GPSTracker(getApplicationContext());

        btnSaveRoute = (Button) findViewById(R.id.btnSaveRoute);


        btnGpsGoster = (Button)findViewById(R.id.btnGpsGoster);

        timer = new Timer();

timer.schedule(new TimerTask() {
    @Override
    public void run() {
        try {
            if(gpstrack.canGetLocation()) {
                lat.add(gpstrack.getLongitude());
                lang.add(gpstrack.getLatitude());
            }
        }catch (Exception e)
        {

        }

    }
},3000,3000);



    btnGpsGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpstrack = new GPSTracker(getApplicationContext());

                gpstrack.getLatitude();
                gpstrack.getLongitude();
                al.add(gpstrack.getLatitude());


              //  koordinatlar2.add((new LatLng(gpstrack.getLatitude(), gpstrack.getLongitude())));

                double x = gpstrack.getLongitude();


                Toast.makeText(getApplicationContext(), "lattitude: " + gpstrack.getLatitude() + "longitude: " + gpstrack.getLongitude() + "Konum Listesi: " + al + "value: " + value + "baslangic adresi: " + baslangicAdresi, Toast.LENGTH_SHORT).show();

            }
        });




    btnSaveRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     koordinatlar.set(0,new LatLng(gpstrack.getLatitude(), gpstrack.getLongitude())) ;

                Intent intentMap2 = new Intent(Map.this, Map2.class);
                startActivity(intentMap2);

                saveroute = true;
            }
        });






        LatLng fromPosition = new LatLng(gpstrack.getLatitude(),  gpstrack.getLongitude());
        LatLng toPosition = new LatLng(39.981009112679154, 32.74882598803263);

        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        GMapV2Direction md = new GMapV2Direction();

        Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_WALKING);

        value = md.getDistanceValue(doc);
        baslangicAdresi = md.getStartAddress(doc);



        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.BLUE);

        for(int i = 0 ; i < directionPoint.size() ; i++) {
            rectLine.add(directionPoint.get(i));
        }

        mMap.addPolyline(rectLine);




        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(gpstrack.getLatitude(), gpstrack.getLongitude())).title("Teknopar");

// Changing marker icon
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_redflag));


        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng( gpstrack.getLatitude(), gpstrack.getLongitude())).zoom(14).build();


        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

// adding marker
        mMap.addMarker(marker);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    }

    /*
public ArrayList<LatLng> koordinatlar(ArrayList<LatLng> points)
{

    points.add(new LatLng(gpstrack.getLatitude(),gpstrack.getLongitude()));
    return points;
}

*/

    public ArrayList<LatLng> getKoordinatlar() {
        return koordinatlar;
    }

    public void setKoordinatlar(ArrayList<LatLng> koordinatlar) {
        this.koordinatlar = koordinatlar;
    }




}
