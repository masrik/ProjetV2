package simon.projetfinalv2;


import simon.projetfinalv2.Model.Destination;
import simon.projetfinalv2.Model.Geolocalisation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SplashscreenAct extends AppCompatActivity {

    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mProgressBar = (ProgressBar) findViewById(R.id.pBAsync);

        new BackgroundSplashTask(this).execute();

    }

    /**
     * Async Task: can be used to load DB, images during which the splash screen
     * is shown to user
     */
    private class BackgroundSplashTask extends AsyncTask<Void, Integer, List<Destination>> {
        Context mcontext;
        String result;
        double longitude;
        double latitude;
        double offset;
        int nombredeDestination;
        ArrayList<Destination> listDestination ;


        public BackgroundSplashTask(Context context){
            this.mcontext=context;
            this.listDestination = new ArrayList<Destination>();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // Toast.makeText(getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_SHORT).show();
            Geolocalisation geolocalisation = new Geolocalisation(mcontext);
            latitude=geolocalisation.getLatitude();
            longitude=geolocalisation.getLongitude();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mProgressBar.setMax(nombredeDestination);
                mProgressBar.setProgress(values[0]);


        }

        @Override
        protected List<Destination> doInBackground(Void... params) {

            String url = "http://voyage2.corellis.eu/api/v2/homev2?lat="+latitude+"&lon="+longitude+"&offset=10";
            URL urlObj = null;
            try {
                urlObj = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                StringBuilder responseStrBuilder = new StringBuilder();
                while ((line = bR.readLine()) != null) {

                    responseStrBuilder.append(line);
                }
                inputStream.close();
               result = responseStrBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                double offset = jsonObject.getDouble("offset");
                nombredeDestination = jsonArray.length();
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject obj = jsonArray.getJSONObject(i);
                    Destination destination = new Destination(obj, mcontext);
                    //les trucs de type GEOLOC sont vides et inutiles

                    Log.d("Probleme", "test1");
                    listDestination.add(destination);
                    Log.d("Probleme", "test2");


                    publishProgress(i);


                }
                return listDestination;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return listDestination;

        }




            @Override
        protected void onPostExecute(List<Destination> lisDestination) {
                super.onPostExecute(lisDestination);

            Intent i = new Intent(SplashscreenAct.this,
                    MainActivity.class);
            // any info loaded can during splash_show
            // can be passed to main activity using
            // below
            i.putExtra("data", listDestination);
            startActivity(i);
            finish();
        }

    }
}
