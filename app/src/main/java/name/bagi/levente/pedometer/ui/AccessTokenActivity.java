package name.bagi.levente.pedometer.ui;

/**
 * Created by EREN on 04.12.2015.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import name.bagi.levente.pedometer.R;
import name.bagi.levente.pedometer.util.PrefUtil;

public class AccessTokenActivity extends AppCompatActivity {

    private TextView tokenTV;
    private PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_access_token);

        init();

    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefUtil = new PrefUtil(this);

        tokenTV = (TextView) findViewById(R.id.token_tv);
        tokenTV.setText(prefUtil.getToken());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
