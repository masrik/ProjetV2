package simon.projetfinalv2.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by simon on 21/01/2017.
 */

public class Ville extends Destination {

    String name;
    String country;
    String description;
    String star;
    String nbPOI;
    String nbParcours;
    String stars;
    String lat;
    String lon;
    ArrayList<String> photos;

    public Ville(JSONObject jsonObject) throws JSONException {

        name= jsonObject.getString("name");
        country = jsonObject.getString("country");
        star=jsonObject.getString("stars");
        nbPOI = jsonObject.getString("nbPois");
        nbParcours = jsonObject.getString("nbParcours");
        description=jsonObject.getString("description");
        stars = jsonObject.getString("stars");
        JSONObject coord = (JSONObject) jsonObject.get("location");
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

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getNbPOI() {
        return nbPOI;
    }

    public void setNbPOI(String nbPOI) {
        this.nbPOI = nbPOI;
    }

    public String getNbParcours() {
        return nbParcours;
    }

    public void setNbParcours(String nbParcours) {
        this.nbParcours = nbParcours;
    }
}
