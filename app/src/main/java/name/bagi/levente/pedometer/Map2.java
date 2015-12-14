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
import java.util.List;

import name.bagi.levente.pedometer.gps.GPSTracker;
import name.bagi.levente.pedometer.routedraw.GMapV2Direction;

public class Map2 extends Activity  {
    // static final LatLng TutorialsPoint = new LatLng(39.9402 , 32.8625);
    private GoogleMap mMap;

    //for getting gps data
    private Location mLastLocation;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;


    ArrayList al = new ArrayList();
    ArrayList map2lang = new ArrayList();

    Double []lang ;

    Double []lat ;


    double pointX[]={39.99126565551758,42.991009112679154,42.901009112679154,42.997009112679154,42.998009112679154,39.94026565551758};
    double pointY[]={32.74454119873047,32.15882598803263,32.76082598803263,32.76182598803263,32.86282598803263,32.86254119873047};

   // double x[] = Map.lang;


    private Button btnGpsGoster;
    GPSTracker gpstrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.harita);


        lang = new Double[Map.lang.size()];
        Map.lang.toArray(lang);

       lat = new Double[Map.lat.size()];
        Map.lat.toArray(lat);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        gpstrack = new GPSTracker(getApplicationContext());





        btnGpsGoster = (Button)findViewById(R.id.btnGpsGoster);

        btnGpsGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpstrack = new GPSTracker(getApplicationContext());

                gpstrack.getLatitude();
                gpstrack.getLongitude();
                al.add(gpstrack.getLatitude());

                Toast.makeText(getApplicationContext(), "lattitude: " + gpstrack.getLatitude() + "longitude: " + gpstrack.getLongitude() + "Konum Listesi: " + al , Toast.LENGTH_SHORT).show();

            }
        });







        LatLng fromPosition = new LatLng(gpstrack.getLatitude(),  gpstrack.getLongitude());
        LatLng toPosition = new LatLng(39.981009112679154, 32.74882598803263);

        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();


        /*
        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int z = 0; z < list.size(); z++) {
            LatLng point = list.get(z);
            options.add(point);
        }
        line = myMap.addPolyline(options);
*/

        /*
        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.BLUE);

        for(int i = 0 ; i < al.size() ; i++) {
            LatLng point = list.get(i);


            rectLine.add(point);
        }
*/

        /*
        List<LatLng> list=new ArrayList<LatLng>();

        PolylineOptions rectLine = new PolylineOptions().width(5).color(Color.BLUE);

        for (int i = 0 ; i < pointX.length-1; i++){
            rectLine.add(new LatLng(pointX[i],pointY[i]));
        };

     //   rectLine.add(list);
        mMap.addPolyline(rectLine);

*/


/*

        List<LatLng> points=new ArrayList<LatLng>();
        for (int i = 0 ; i < pointX.length; i++){
            points.add(new LatLng(pointX[i],pointY[i]));
        };


        for (int i = 0; i < points.size() - 1; i++) {
            LatLng src = points.get(i);
            LatLng dest = points.get(i + 1);

            // mMap is the Map Object
            Polyline line = mMap.addPolyline(
                    new PolylineOptions().add(
                            new LatLng(src.latitude, src.longitude),
                            new LatLng(dest.latitude,dest.longitude)
                    ).width(5).color(Color.RED).geodesic(true)
            );
        }

*/
        List<LatLng> points=new ArrayList<LatLng>();
        for (int i = 0 ; i < lang.length; i++){
            points.add(new LatLng(lang[i],lat[i]));
        };


        for (int i = 0; i < points.size() - 1; i++) {
            LatLng src = points.get(i);
            LatLng dest = points.get(i + 1);

            // mMap is the Map Object
            Polyline line = mMap.addPolyline(
                    new PolylineOptions().add(
                            new LatLng(src.latitude, src.longitude),
                            new LatLng(dest.latitude,dest.longitude)
                    ).width(5).color(Color.RED).geodesic(true)
            );
        }



        /*

        for (int i = 0; i < 1000; i++) {

            // mMap is the Map Object
            Polyline line = mMap.addPolyline(
                    new PolylineOptions().add(
                            new LatLng(gpstrack.getLatitude(), gpstrack.getLongitude()), new LatLng(gpstrack.getLatitude(), gpstrack.getLongitude())
                    ).width(5).color(Color.RED).geodesic(true)
            );
        }
*/







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






}
