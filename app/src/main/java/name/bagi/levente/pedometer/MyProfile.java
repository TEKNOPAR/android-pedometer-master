package name.bagi.levente.pedometer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import name.bagi.levente.pedometer.imageurl.ImageLoader;
import name.bagi.levente.pedometer.report.StepReport2;
import name.bagi.levente.pedometer.signinup.SignUp;

/**
 * Created by eray on 17.09.2015.
 */
public class MyProfile extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    Integer imgid[] = {R.drawable.profilim, R.drawable.zamantuneli,  R.drawable.raporlar,R.drawable.stepicon, R.drawable.icon_options, R.drawable.icon_exit};
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilim);

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
                        Intent intentProfilim = new Intent(MyProfile.this, MyProfile.class);
                        startActivity(intentProfilim);
                        break;
                    case 1:
                        Intent intentZamanTuneli = new Intent(MyProfile.this, MyProfile.class);
                        startActivity(intentZamanTuneli);
                        break;
                    case 2:
                        Intent intentReport = new Intent(MyProfile.this, StepReport2.class);
                        startActivity(intentReport);
                        break;

                    case 3:
                        Intent intent = new Intent(MyProfile.this, Pedometer.class);
                        startActivity(intent);
                        break;

                    case 4:
                        Intent ayarlarIntent = new Intent(MyProfile.this, Map.class);
                        startActivity(ayarlarIntent);
                        break;
                    case 5:

                        Intent kayitOl = new Intent(MyProfile.this, SignUp.class);
                        startActivity(kayitOl);
                        break;


                }

            }
        });





        int loader = R.drawable.loader;

        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.profilResmi);

        // Image url
        String image_url = "http://api.androidhive.info/images/sample.jpg";

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());

        imgLoader.DisplayImage(image_url, loader, image);

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
