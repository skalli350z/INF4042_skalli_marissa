package org.esiea.skalli_marissa.univers_des_bieres;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    TextView tv_hw = null;
    DatePickerDialog dpd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Univers des bières");


        tv_hw = (TextView) findViewById(R.id.tv_hello_world);
        String date_now = DateUtils.formatDateTime(getApplicationContext(), (new Date()).getTime(), DateFormat.FULL);
        tv_hw.setText(getString(R.string.app_name) + " " + date_now);


        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_hw.setText(getString(R.string.app_name) + " " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, 2015, 12, 31);
/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

*/




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }


    public void btnHwAct(View v) {
        dpd.show();
        Toast.makeText(getApplicationContext(), getString(R.string.msg), Toast.LENGTH_LONG).show();
    }

    public void tnSdAct(View v) {
        Intent i = new Intent(MainActivity.this, SecondeActivity.class);
        startActivity(i);
    }

    public void onclickbutton(View v) {
        Intent i = new Intent();
        i.setAction("org.esiea.newlife");
        startActivity(i);


    }

    public void surleclick(View v) {
        Intent i = new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(i);

    }


    public void hbibi(View v) {
        Intent e = new Intent(MainActivity.this, FifthActivity.class);
        startActivity(e);

    }

    public void hna(View v) {
        Intent e = new Intent(MainActivity.this, SixthActivity.class);
        startActivity(e);

    }


}
