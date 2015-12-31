package org.esiea.skalli_marissa.univers_des_bieres;

/**
 * Created by quake on 30/12/15.
 */



import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";


    public MyIntentService() {

        super("MyIntentService");
    }



    public static void startActionget_all_beers(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);


    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */


    @Override
    protected void onHandleIntent(Intent intent) {
        handleactionget_all_beers();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private void handleactionget_all_beers() {
        Log.d(TAG, "Thread service name:" + Thread.currentThread().getName());
        URL url = null;
        try {
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                Log.d(TAG, "Bieres json downloaded !");

                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("org.esiea.skalli_marissa.univers_des_bieres"));


            }else{ Log.d(TAG,"p");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}




