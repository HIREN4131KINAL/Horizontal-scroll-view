package com.tranetech.horizontalscroll;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CenterLockHorizontalScrollview centerLockHorizontalScrollview;
    CustomListAdapter customListAdapter;
    private String jsonstr;
    private String DEPARTMENT;

    ArrayList<String> PRICE = new ArrayList<String>();
    ArrayList<String> ITEM = new ArrayList<String>();
    ArrayList<String> IMAGE = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Start download
        new AsyncHttpTask().execute();
    }

    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                JSONParser call = new JSONParser();
                DEPARTMENT = "http://api.androidhive.info/json/movies.json";
                jsonstr = call.makeServiceCall(DEPARTMENT);


            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("Json url view", jsonstr);
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Download complete. Lets update UI
            //Hide progressbar
            //mProgressBar.setVisibility(View.GONE);


            if (jsonstr != null) {
                try {
                    JSONArray response = new JSONArray(jsonstr);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject post = response.getJSONObject(i);

                        String item_name = post.getString("title").toString();
                        String price = post.getString("releaseYear").toString();
                        String imagename = post.getString("image").toString();


                        PRICE.add(price);
                        ITEM.add(item_name);
                        IMAGE.add(imagename);

                    }

                    centerLockHorizontalScrollview = (CenterLockHorizontalScrollview) findViewById(R.id.scrollView);
                    customListAdapter = new CustomListAdapter(MainActivity.this,
                            R.layout.news_list_item, PRICE, ITEM, IMAGE);
                    centerLockHorizontalScrollview.setAdapter(MainActivity.this, customListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //mGridAdapter.setGridData(mGridData);
            } else
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();

        }
    }
}
