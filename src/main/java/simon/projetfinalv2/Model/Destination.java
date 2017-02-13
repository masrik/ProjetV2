package simon.projetfinalv2.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
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
    private POI poi;
    private Parcours parcours;
    private Ville ville;
    public Destination(){};

    public POI getPoi() {
        return poi;
    }

    public void setPoi(POI poi) {
        this.poi = poi;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public  Destination(JSONObject obj, Context context) throws JSONException, IOException {
        id=obj.getString("id");
        type = obj.getString("type");
        MainPictureURL = obj.getString("media");
        name=obj.getString("display");
        MainPicture=null;


    }
    public void GetDetail()
    {
        String url = "";
        if(type.equals("POI"))
        {
            url = "http://voyage2.corellis.eu/api/v2/poi?id="+ id;
        }
        if(type.equals("PARCOURS"))
        {
           url = "http://voyage2.corellis.eu/api/v2/parcours?id="+id;
        }
        if(type.equals("CITY"))
        {
            url = "http://voyage2.corellis.eu/api/v2/destination?id="+id;
        }

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
            String result = responseStrBuilder.toString();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            JSONObject obj = jsonArray.getJSONObject(0);

            if(type.equals("POI"))
            {
                poi=new POI(obj);
            }
            if(type.equals("PARCOURS"))
            {
                parcours = new Parcours(obj);
            }
            if(type.equals("CITY"))
            {
                ville = new Ville(obj);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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


}