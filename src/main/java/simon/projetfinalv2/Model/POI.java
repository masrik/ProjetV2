package simon.projetfinalv2.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by simon on 21/01/2017.
 */

public class POI implements Serializable {
    String name;
    String lg;
    String web;
    String displayPhone;
    String phone;
    String horaire;
    String tarif;
    String description;
    String duree;
    String lat;
    String lon;
    ArrayList<String> photos;
    String stars;

    public POI(){}

    public POI(JSONObject jsonObject) throws JSONException {
        name= jsonObject.getString("name");
        lg =jsonObject.getString("lg");
        web = jsonObject.getString("web");
        displayPhone=jsonObject.getString("display_phone");
        phone = jsonObject.getString("phone");
        horaire = jsonObject.getString("horaires");
        tarif = jsonObject.getString("tarif");
        description=jsonObject.getString("description");
        duree = jsonObject.getString("visit_duration");
        stars = jsonObject.getString("stars");
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
        System.out.println(photos.size());
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

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public String getLon() {

        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
}
