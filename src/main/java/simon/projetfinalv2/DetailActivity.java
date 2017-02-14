package simon.projetfinalv2;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import simon.projetfinalv2.Model.Destination;
import simon.projetfinalv2.Model.HorizontalAdapter;
import simon.projetfinalv2.Model.POI;
import simon.projetfinalv2.Model.Parcours;
import simon.projetfinalv2.Model.Ville;

import static java.security.AccessController.getContext;
import static simon.projetfinalv2.R.id.horizontal_recycler_view;
import static simon.projetfinalv2.R.layout.horizontal_adapter;
import static simon.projetfinalv2.R.layout.parcours_detail;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private POI poi;
    private Ville ville;
    private Parcours parcours;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        final Destination destination = (Destination) extras.getSerializable("data");
        if (destination.getType().equals("POI")) {
            poi = destination.getPoi();
            setContentView(R.layout.poi_detail);
            TextView tvTitre = (TextView) findViewById(R.id.titre);
            tvTitre.setText(poi.getName());
            tvTitre.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            TextView tvDescription = (TextView) findViewById(R.id.Description);
            tvDescription.setText(poi.getDescription());

            TextView TvHoraire = (TextView) findViewById(R.id.Horaire);
            TextView TvTitreHoraire = (TextView) findViewById(R.id.titreHoraire);
            if (poi.getHoraire().equals("null")) {
                TvHoraire.setVisibility(View.GONE);
                TvTitreHoraire.setVisibility(View.GONE);
            } else {
                TvHoraire.setText(poi.getHoraire());
            }

            TextView Tvduree = (TextView) findViewById(R.id.visit_duration);
            TextView TvTitreduree =( TextView) findViewById(R.id.Titrevisit_duration);
            if(poi.getDuree().equals(""))
            {
                Tvduree.setVisibility(View.GONE);
                TvTitreduree.setVisibility(View.GONE);
            }
            else
            {
                Tvduree.setText(poi.getDuree());
            }

            TextView TvRating = (TextView)findViewById(R.id.rating);
            TextView TvTitreRating = ( TextView) findViewById(R.id.TitreRating);
            if(poi.getStars().equals(""))
            {
                TvRating.setVisibility(View.GONE);
                TvTitreRating.setVisibility(View.GONE);
            }
            else
            {
                TvRating.setText(poi.getStars());
            }

            TextView TvTitreTarif = (TextView) findViewById(R.id.TitreTarif);
            TextView TvTarif = (TextView) findViewById(R.id.Tarif);
            if (poi.getTarif().equals("null")) {
                TvTarif.setVisibility(View.GONE);
                TvTitreTarif.setVisibility(View.GONE);
            } else {
                TvTarif.setText(poi.getTarif());
            }


            TextView TvTitreLangue = (TextView) findViewById(R.id.TitreLangue);
            TextView TvLangue = (TextView) findViewById(R.id.Langue);
            if (poi.getLg().equals("null")) {
                TvLangue.setVisibility(View.GONE);
                TvTitreLangue.setVisibility(View.GONE);
            } else {
                TvLangue.setText(poi.getLg());
            }


            TextView TvTitreTelephone = (TextView) findViewById(R.id.TitreTéléphone);
            TextView TvTelephone = (TextView) findViewById(R.id.Telephone);
            if (poi.getPhone().equals("")) {

                TvTelephone.setVisibility(View.GONE);
                TvTitreTelephone.setVisibility(View.GONE);
            } else {
                TvTelephone.setText(poi.getPhone());

            }


            TextView TvTitreWeb = (TextView) findViewById(R.id.TitreWeb);
            TextView TvWeb = (TextView) findViewById(R.id.web);
            if (poi.getWeb().equals("")) {
                TvWeb.setVisibility(View.GONE);
                TvTitreWeb.setVisibility(View.GONE);
            } else {
                TvWeb.setText(poi.getWeb());
            }



            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(this, poi.getPhotos(), new HorizontalAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(List<String> items, int position) {
                    Intent i = new Intent(DetailActivity.this, imageView.class);
                    i.putExtra("position", position);
                    i.putExtra("photosUrl", (Serializable) items);
                    startActivity(i);

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(horizontalAdapter);

            initMap();




            TvTelephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + destination.getPoi().getPhone()));
                    startActivity(callIntent);
                }
            });
            TvWeb.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(destination.getPoi().getWeb()));
                    startActivity(intent);
                }
            });


        }

        else if (destination.getType().equals("CITY"))
        {

            setContentView(R.layout.ville_detail);
            ville = destination.getVille();
            TextView tvTitre = (TextView) findViewById(R.id.titre);
            tvTitre.setText(ville.getName());
            tvTitre.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

            TextView tvDescription = (TextView) findViewById(R.id.Description);
            tvDescription.setText(ville.getDescription());

            TextView TvTittreNbPoi = ( TextView) findViewById(R.id.TitreNbPOI);
            TextView TvNbPOI = (TextView) findViewById(R.id.nbPOI);

            if(ville.getNbPOI().equals("0"))
            {
                TvTittreNbPoi.setVisibility(View.GONE);
                TvNbPOI.setVisibility(View.GONE);
            }
            else
            {
                TvNbPOI.setText(ville.getNbPOI());
            }

            TextView TvTitreCountry = (TextView) findViewById(R.id.TitreCountry);
            TextView TvCountry = (TextView) findViewById(R.id.country);
            if(ville.getCountry().equals(""))
            {
                TvTitreCountry.setVisibility(View.GONE);
                TvCountry.setVisibility(View.GONE);
            }
            else
            {
                TvCountry.setText(ville.getCountry());
            }

            TextView TvTittreNbParcours= ( TextView) findViewById(R.id.TitreNbParcours);
            TextView TvNbParcours = (TextView) findViewById(R.id.nbParcours);


            if(ville.getNbParcours().equals("0"))
            {
                TvTittreNbParcours.setVisibility(View.GONE);
                TvNbParcours.setVisibility(View.GONE);
            }
            else
            {
                TvNbParcours.setText(ville.getNbPOI());
            }

            TextView TvRating = (TextView)findViewById(R.id.rating);
            TextView TvTitreRating = ( TextView) findViewById(R.id.TitreRating);
            if(ville.getStars().equals(""))
            {
                TvRating.setVisibility(View.GONE);
                TvTitreRating.setVisibility(View.GONE);
            }
            else
            {
                TvRating.setText(ville.getStars());
            }

            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(this, ville.getPhotos(), new HorizontalAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(List<String> items, int position) {
                    Intent i = new Intent(DetailActivity.this, imageView.class);
                    i.putExtra("position", position);
                    i.putExtra("photosUrl", (Serializable) items);
                    startActivity(i);

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(horizontalAdapter);

            initMap();




        }
        if (destination.getType().equals("PARCOURS"))
        {
            setContentView(R.layout.parcours_detail);
            parcours = destination.getParcours();
            TextView tvTitre = (TextView) findViewById(R.id.titre);
            tvTitre.setText(parcours.getTitle());
            tvTitre.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

            TextView tvDescription = (TextView) findViewById(R.id.Description);
            tvDescription.setText(parcours.getDescription());

            TextView tvTitreDuree = (TextView) findViewById(R.id.TitreDuration);
            TextView tvDuration = (TextView)  findViewById(R.id.Duration);
                String text="";
                if(!parcours.getDurees().get(0).equals("0"))
                {
                    text = text +"En Vélo : " + parcours.getDurees().get(0) + " minutes \n";
                }
                if(!parcours.getDurees().get(1).equals("0"))
                {
                    text = text + "En voiture : " + parcours.getDurees().get(1) + " minutes \n";
                }
                if(!parcours.getDurees().get(2).equals("0"))
                {
                    text = text + "A pied : " + parcours.getDurees().get(2) + " minutes \n";
                }
                if(text.equals(""))
                {
                    tvTitreDuree.setVisibility(View.GONE);
                    tvDuration.setVisibility(View.GONE);
                }

            tvDuration.setText(text);

            TextView TvTitreRating = (TextView) findViewById(R.id.TitreRating);
            TextView TvRating = (TextView) findViewById(R.id.rating);
            if( parcours.getStars().equals("0"))
            {
                TvTitreRating.setVisibility(View.GONE);
                TvRating.setVisibility(View.GONE);
            }
            else
            {
                TvRating.setText(parcours.getStars());
            }
            TextView TvDistance = (TextView) findViewById(R.id.Distance);
            TextView TvTitreDistance = (TextView) findViewById(R.id.TitreDistance);
            if(parcours.getDistance().equals("0"))
            {
                TvDistance.setVisibility(View.GONE);
                TvTitreDistance.setVisibility(View.GONE);
            }
            else{
                TvDistance.setText(parcours.getDistance());
            }

            TextView TvLoop = (TextView) findViewById(R.id.Loop);
            TextView TvTitreLoop = (TextView) findViewById(R.id.TitreLoop);
            if(parcours.getLoop().equals(""))
            {
                TvLoop.setVisibility(View.GONE);
                TvTitreLoop.setVisibility(View.GONE);
            }
            else
            {
                TvLoop.setText(parcours.getLoop());
            }
            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(this, parcours.getPhotos(), new HorizontalAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(List<String> items, int position) {
                    Intent i = new Intent(DetailActivity.this, imageView.class);
                    i.putExtra("position", position);
                    i.putExtra("photosUrl", (Serializable) items);
                    startActivity(i);

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(horizontalAdapter);

            initMap();








        }





        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public boolean googlePlayServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "connection impossible au google ply services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        double lat;
        double lon;
        if(poi != null){
            lat = Double.parseDouble(poi.getLat());
            lon = Double.parseDouble(poi.getLon());
            LatLng coord = new LatLng(lat, lon);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coord, 10);
            map.moveCamera(update);
            MarkerOptions options = new MarkerOptions().title(poi.getName()).position(coord);
            map.addMarker(options);}
        else if (ville !=null)
        {
            lat = Double.parseDouble(ville.getLat());
            lon = Double.parseDouble(ville.getLon());
            LatLng coord = new LatLng(lat, lon);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coord, 10);
            map.moveCamera(update);
            MarkerOptions options = new MarkerOptions().title(ville.getName()).position(coord);
            map.addMarker(options);
        }
        else if ( parcours!=null)
        {

            lat = Double.parseDouble(parcours.getLat());
            lon = Double.parseDouble(parcours.getLon());
            LatLng coord = new LatLng(lat, lon);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coord, 10);
            map.moveCamera(update);
            MarkerOptions options = new MarkerOptions().title(parcours.getName()).position(coord);
            map.addMarker(options);
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Detail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}