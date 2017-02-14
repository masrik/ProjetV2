package simon.projetfinalv2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.*;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class imageView extends Activity {


    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    private int position;
    private List<String> photosURL;
    private ImageView imgV;

    float x1,x2;
    float y1, y2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_image_view);
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");
        photosURL = (ArrayList) extras.getSerializable("photosUrl");
        imgV = (ImageView) findViewById(R.id.imageView);

        Glide.with(this).load(photosURL.get(position)).into(imgV);

    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {

        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                if (x1 < x2) {
                    if(position != 0) {
                        position = position - 1;
                    }
                    else
                    {
                        position = photosURL.size()-1;

                    }
                    Glide.with(this).load(photosURL.get(position)).into(imgV);}




                // if right to left sweep event on screen
                if (x1 > x2) {
                    if(position != 0 && position !=(photosURL.size()-1)) {
                        position = position + 1;
                    }
                    else if( position == 0)
                    {
                        position = 1;

                    }
                    else
                    {
                        position =0;
                    }
                    Glide.with(this).load(photosURL.get(position)).into(imgV);}
                }
                break;
            }



        return false;


}

}
