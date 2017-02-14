package simon.projetfinalv2.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by simon on 21/01/2017.
 */

public class Parcours extends Destination{


    String title;
    String distance;
    String loop;
    String stars;
    String description;
    String lat;
    String lon;
    ArrayList<String> photos;


    ArrayList<String> Durees;


public Parcours(JSONObject jsonObject) throws JSONException {


    title= jsonObject.getString("title");
    distance = jsonObject.getString("distance");
    stars=jsonObject.getString("stars");
    loop = jsonObject.getString("loop");
    description=jsonObject.getString("description");
    JSONObject duration = new JSONObject(jsonObject.getString("duration"));
    Durees = new ArrayList<String>();
    Durees.add(String.valueOf(duration.get("bicycle")));
    Durees.add(String.valueOf(duration.get("car")));
    Durees.add(String.valueOf(duration.get("by_foot")));

    JSONObject adresse = (JSONObject) jsonObject.get("location");
    JSONObject coord = (JSONObject) adresse.get("coords");
    lat = coord.getString("lat");
    lon = coord.getString("lon");
    photos = new ArrayList<>();
    JSONArray medias = (JSONArray) jsonObject.get("medias");
    for(int i=0; i<medias.length(); i++)
    {
        JSONObject obj = medias.getJSONObject(i);
        String photo = obj.getString("url");
        photos.add(photo);
    }
}

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLoop() {
        return loop;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLoop(String loop) {
        this.loop = loop;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getDurees() {
        return Durees;
    }

    public void setDurees(ArrayList<String> durees) {
        Durees = durees;
    }
}
