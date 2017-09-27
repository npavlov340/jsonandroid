package com.example.user.remotemachineuilayout;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.HttpAuthHandler;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.client.HttpClient;

public class MainActivity extends AppCompatActivity {


   String[] photoLast;
   String [] titleLast;
    ArrayList<String> urlPhoto = new ArrayList<String>();
    ArrayList<String> titleName = new ArrayList<String>();
    ListView jsonDisplay;
    CustomAdapter displayAdapter;

    final static String URL = "http://jsonplaceholder.typicode.com/photos";
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Read().execute();


//        jsonDisplay = (ListView) findViewById(R.id.jsonDisplay);
//        displayAdapter = new CustomAdapter(this, title);
//        jsonDisplay.setAdapter(displayAdapter);
    }



    public class Read extends AsyncTask <Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
       protected Void doInBackground(Void... arg0) {
            //TODO Auto-generated method stub
            HttpHandler sh = new HttpHandler();
            String url = URL;
            String jsonStr = sh.makeServiceCall(url);



            Log.e(TAG, "Response from url: " + jsonStr);
            if(jsonStr != null) {
                try {
                    //JSONObject json = new JSONObject(jsonStr);

                    JSONArray photos = new JSONArray(jsonStr);
                    photoLast = new String[photos.length()];
                    titleLast = new String[photos.length()];
                    for (int i = 0; i < photos.length(); i++) {
                        JSONObject temp = photos.getJSONObject(i);
                        String title = temp.getString("title");
                        String thumbNailURL = temp.getString("thumbnailUrl");

                        photoLast[i]=thumbNailURL;
                        titleLast[i]=i+" - "+title;
//                        urlPhoto.add(thumbNailURL);
//                        titleName.add(title);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error2: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
                } else{
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            return null;
        }
        @Override
       protected void onPostExecute(Void result){
           //TODO Auto-Generated method stub
            //photoLast = urlPhoto.toArray(photoLast);
            //titleLast = titleName.toArray(titleLast);
            displayAdapter = new CustomAdapter(MainActivity.this,titleLast, photoLast);
            jsonDisplay = (ListView) findViewById(R.id.jsonDisplay);
            jsonDisplay.setAdapter(displayAdapter);
             super.onPostExecute(result);


       }



    }
}

