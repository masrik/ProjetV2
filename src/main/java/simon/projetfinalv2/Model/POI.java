package simon.projetfinalv2.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;

/**
 * Created by simon on 21/01/2017.
 */

public class POI extends Destination {
    String urlDetail;
    String name;
    String lg;
    String web;
    String displayPhone;
    String phone;
    String horaire;
    String tarif;
    String description;
    String duree;


    public POI(JSONObject jsonObject) throws JSONException {
        name= jsonObject.getString("name");
        lg =jsonObject.getString("lg");
        web = jsonObject.getString("web");
        displayPhone=jsonObject.getString("display_phone");
        phone = jsonObject.getString("phone");
        horaire = jsonObject.getString("horaire");
        tarif = jsonObject.getString("tarif");
        description=jsonObject.getString("description");
        duree = jsonObject.getString("duree");
        urlDetail = "http://voyage2.corellis.eu/api/v2/poi?id=" + this.getId();
    }




    public String getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(String urlDetail) {
        this.urlDetail = urlDetail;
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
