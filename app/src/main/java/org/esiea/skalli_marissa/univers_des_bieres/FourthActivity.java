package org.esiea.skalli_marissa.univers_des_bieres;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class FourthActivity extends Activity {
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quatrieme);


        IntentFilter intentFilter = new IntentFilter("org.esiea.newlife");
        BierUpdate bier = new BierUpdate();
        LocalBroadcastManager.getInstance(this).registerReceiver( bier , intentFilter);
        rv = (RecyclerView) findViewById(R.id.rv_biere);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new BiersAdapter());
        MyIntentService.startActionget_all_beers(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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



    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {



            Log.d("TAG", "vuo" + getIntent().getAction().toString());
            ((BiersAdapter) rv.getAdapter()).setNewBier(getBiersFromFile());
        }
    }

    public class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {
        private JSONArray x = new JSONArray();
        @Override
        public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
            return new BierHolder(v);
        }


        @Override
        public void onBindViewHolder(BierHolder holder, int position) {

            JSONObject jso = null;
            try {
                jso = (JSONObject) x.get(position);
                Log.d("TAG","wa lhmar" + x.get(position));
                holder.mTextView.setText(jso.getString("name"));
                holder.description.setText(jso.getString("description"));
                holder.id.setText(jso.getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            Log.d("TAG","wa lkelb" + x.length());
            return x.length();
        }

        public void setNewBier(JSONArray x) {
            this.x = x;
            notifyDataSetChanged();


        }

        public class BierHolder extends RecyclerView.ViewHolder {
            public TextView mTextView,description,id;

            public BierHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.textView4);
                description = (TextView) itemView.findViewById(R.id.textView5);
                id =  (TextView) itemView.findViewById(R.id.textView7);

            }
        }
    }

    public JSONArray getBiersFromFile(){

        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json" );
            byte[]buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray( new String(buffer, "UTF-8" ));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

}