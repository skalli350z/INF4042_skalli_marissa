package org.esiea.skalli_marissa.univers_des_bieres;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class FifthActivity extends Activity {

    TextView tv_hw = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinquieme);

        tv_hw = (TextView) findViewById(R.id.texte);
           tv_hw.setText(getString(R.string.hello_info));

    }
}
