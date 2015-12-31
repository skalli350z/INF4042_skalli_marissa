package org.esiea.skalli_marissa.univers_des_bieres;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.*;
import android.widget.Toast;

public class SixthActivity extends FragmentActivity {
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizime);
        Toast.makeText(getApplicationContext(), getString(R.string.msgxos), Toast.LENGTH_LONG).show();

        viewpager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);

    }
}