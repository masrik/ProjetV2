package simon.projetfinalv2.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simon on 19/01/2017.
 */

public class Destination implements Serializable{


    private String id;
    private String name;
    private String type;
    private String MainPictureURL;
    private byte[] MainPicture;

    public Destination(){};

    public  Destination(JSONObject obj, Context context) throws JSONException, IOException {
        id=obj.getString("id");
        type = obj.getString("type");
        MainPictureURL = obj.getString("media");
        name=obj.getString("display");
        MainPicture=null;
        MainPictureDownload(MainPictureURL,context);

    }

    public void MainPictureDownload(String url, Context context) throws IOException {
        InputStream in = new java.net.URL(url).openStream();
        Bitmap bmp= BitmapFactory.decodeStream(in);


        bmp = scaleDownBitmap(bmp,30,context);



        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        MainPicture = stream.toByteArray();


    }

    public Bitmap ConvertToBimap()
    {
        Bitmap bmp = BitmapFactory.decodeByteArray(MainPicture, 0, MainPicture.length);
        return bmp;

    }

    public String getMainPictureURL() {
        return MainPictureURL;
    }

    public void setMainPictureURL(String mainPictureURL) {
        MainPictureURL = mainPictureURL;
    }

    public byte[] getMainPicture() {
        return MainPicture;
    }

    public void setMainPicture(byte[] mainPicture) {
        MainPicture = mainPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Destination(String id, String name, String URLMedia, String Type, String Stars )
    {
        this.id = id;

        this.type=Type;

    }

    public String getId() {
        return id;
    }


    public String getType() {
        return type;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setType(String type) {
        type = type;
    }


    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

}