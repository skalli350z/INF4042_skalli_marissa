package org.esiea.skalli_marissa.univers_des_bieres;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        new TestA((ImageView) findViewById(R.id.imageView4))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/71/_20140506_220855.JPG");
        new TestU((TextView) findViewById(R.id.text4))
                .execute("http://binouze.fabrigli.fr/bieres/71.json");




        new TestA((ImageView) findViewById(R.id.imageView5))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/35/Dremmwel_France.png");
        new TestU((TextView) findViewById(R.id.text5))
                .execute("http://binouze.fabrigli.fr/bieres/35.json");



        new TestA((ImageView) findViewById(R.id.imageView7))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/11/MunchnerBier_Allemagne.png");
        new TestU((TextView) findViewById(R.id.text7))
                .execute("http://binouze.fabrigli.fr/bieres/11.json");



        new TestA((ImageView) findViewById(R.id.imageView2))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/4/Chouffe_belgique.png");
        new TestU((TextView) findViewById(R.id.text2))
                .execute("http://binouze.fabrigli.fr/bieres/4.json");



        new TestA((ImageView) findViewById(R.id.imageView11))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/3/Hoegaarden_Belgique.jpg");
        new TestU((TextView) findViewById(R.id.text11))
                .execute("http://binouze.fabrigli.fr/bieres/3.json");

/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });*/


        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
                .setTicker("toutes les bières")
                .setContentTitle("Univers des bières ")
                .setContentText("Amusez-vous bien")
                .setSmallIcon(R.drawable.b1)
                .setContentIntent(pIntent).getNotification();
        noti.flags= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);

    }





    class TestU extends AsyncTask<String, String, String> {

        TextView displayt;

        public TestU(TextView displayt) {
            this.displayt = displayt;
        }


        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                String u = parentObject.getString("name");
                int x = parentObject.getInt("number_of_notes");
                int y = parentObject.getInt("note_moyenne");

                return u + ", " + "noté : " + x + " fois, il a une note de : "+  y + "/5";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onProgressUpdate(URL... progress) {
            Log.d("TAG", "THread async " + progress[0] + "name:" + Thread.currentThread().getName());

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            displayt.setText(result);

        }
    }


    public class TestA extends AsyncTask<String , Integer , Bitmap > {

        ImageView bmImage;

        public TestA(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Log.d("TAG", "THread async doing name:" + Thread.currentThread().getName());

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = ( HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input= connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (MalformedURLException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        protected void onProgressUpdate(Integer... progress){
            Log.d("TAG","THread async "+ progress[0]+"name:" +Thread.currentThread().getName());

        }
        protected void onPostExecute(Bitmap result ){
            Log.d("TAG","THread async "+ result +"name:" + Thread.currentThread().getName());

            bmImage.setImageBitmap(result);


        }
    }

}
