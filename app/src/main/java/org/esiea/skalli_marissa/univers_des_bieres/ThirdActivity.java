package org.esiea.skalli_marissa.univers_des_bieres;

import android.app.Activity;
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


public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troisieme);


        new TestA((TextView) findViewById(R.id.textv))
                .execute("http://binouze.fabrigli.fr/bieres/24.json");


        new TestX((ImageView) findViewById(R.id.imageViewv))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/24/L_toileduNord_France.png");



        new TestA((TextView) findViewById(R.id.textr))
                .execute("http://binouze.fabrigli.fr/bieres/42.json");


        new TestX((ImageView) findViewById(R.id.imageViewr))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/42/Bi_reAmbr_NosR_gionsontdutaleontduTalent_France_.png");




        new TestA((TextView) findViewById(R.id.textu))
                .execute("http://binouze.fabrigli.fr/bieres/52.json");


        new TestX((ImageView) findViewById(R.id.imageViewu))
                .execute("http://binouze.fabrigli.fr/uploads/biere/image/52/thumb_Jenlain_France.jpg");




        new TestA((TextView) findViewById(R.id.texty))
                .execute("http://binouze.fabrigli.fr/bieres/25.json");


        new TestX((ImageView) findViewById(R.id.imageViewy))
                .execute("http://binouze.fabrigli.fr//uploads/biere/image/25/Castelain_Ch_tiBlonde_France.png");






    }



    class TestA extends AsyncTask<String, String, String> {

        TextView displayt;


        public TestA(TextView displayt) {
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
                String v = parentObject.getString("buveur");

                return u + ", " + "noté : " + x + " fois, buveur : " + v;

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
            Log.d("TAG", "hani lik " + result);
            displayt.setText(result);

        }
    }




    public class TestX extends AsyncTask<String , Integer , Bitmap> {

        ImageView bmImage;


        public TestX(ImageView bmImage) {
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
            Log.d("TAG", "THread async " + result + "name:" + Thread.currentThread().getName());

            bmImage.setImageBitmap(result);


        }
    }


}