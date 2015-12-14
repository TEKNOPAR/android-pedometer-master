package name.bagi.levente.pedometer.imageurl;

/**
 * Created by EREN on 26.11.2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import name.bagi.levente.pedometer.R;

public class AndroidLoadImageFromURLActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_main);

        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;

        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.image);

        // Image url
        String image_url = "http://api.androidhive.info/images/sample.jpg";

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
        imgLoader.DisplayImage(image_url, loader, image);
    }
}