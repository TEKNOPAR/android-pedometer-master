package name.bagi.levente.pedometer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import name.bagi.levente.pedometer.imageurl.ImageLoader;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        CircleImageView ustLogo = (CircleImageView) rowView.findViewById(R.id.ustLogo);
        TextView ad = (TextView) rowView.findViewById(R.id.txtAd);
        TextView soyad = (TextView) rowView.findViewById(R.id.txtSoyad);
        ImageView altresim = (ImageView) rowView.findViewById(R.id.altResim);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);

        if(position == 0)
        {
            extratxt.setText("Profil Sayfam ");
            ustLogo.setVisibility(View.VISIBLE);
            ad.setVisibility(View.VISIBLE);
            soyad.setVisibility(View.VISIBLE);
            altresim.setVisibility(View.GONE);

        }
        else if(position == 1)
        {
            extratxt.setText("Zaman Tünelim ");
            ustLogo.setVisibility(View.GONE);
            ad.setVisibility(View.GONE);
            soyad.setVisibility(View.GONE);
            altresim.setVisibility(View.GONE);

        }
        else if(position == 2)
        {
            extratxt.setText("Grafiksel Raporlar ");
            ustLogo.setVisibility(View.GONE);
            ad.setVisibility(View.GONE);
            soyad.setVisibility(View.GONE);
            altresim.setVisibility(View.GONE);

        }
        else if(position == 3)
        {
            extratxt.setText("Adım Sayıcı ");
            ustLogo.setVisibility(View.GONE);
            ad.setVisibility(View.GONE);
            soyad.setVisibility(View.GONE);
            altresim.setVisibility(View.GONE);

        }
        else if(position == 4)
        {
            extratxt.setText("Ayarlar Sayfası ");
            ustLogo.setVisibility(View.GONE);
            ad.setVisibility(View.GONE);
            soyad.setVisibility(View.GONE);
            altresim.setVisibility(View.GONE);
        }

        else if(position == 5)
        {
            extratxt.setText("Çıkış Sayfası ");
            ustLogo.setVisibility(View.GONE);
            ad.setVisibility(View.GONE);
            soyad.setVisibility(View.GONE);
            altresim.setVisibility(View.VISIBLE);

        }

/*
        SharedPreferences prefs = getActivity().getSharedPreferences("GooglePlusUserData", Context.MODE_PRIVATE);
        String gpname = prefs.getString("NAME", null);
        String gpemail = prefs.getString("EMAIL", null);
        String gpphotourl = prefs.getString("PHOTOURL", null);
*/

        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;

        // Imageview to show
     //   ImageView image = (ImageView) findViewById(R.id.ustLogo);

        // Image url
        String image_url = "https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male2-64.png";

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(context);

        imgLoader.DisplayImage(image_url, loader, ustLogo);

        return rowView;

    };
}