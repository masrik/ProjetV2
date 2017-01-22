package simon.projetfinalv2;

import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import simon.projetfinalv2.Model.Destination;
import simon.projetfinalv2.Model.DestinationAdapter;
import simon.projetfinalv2.Model.POI;
import simon.projetfinalv2.Model.Parcours;


public class MainActivity extends AppCompatActivity {

    ListView LvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        ArrayList<Destination>destinations= (ArrayList<Destination>) extras.getSerializable("data");
         LvList= (ListView) findViewById(R.id.ListView);

            final DestinationAdapter adapter = new DestinationAdapter(this, destinations);
            LvList.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        LvList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, position, Toast.LENGTH_LONG);
                    }
                }
        );

    }

}

