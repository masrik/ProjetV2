package simon.projetfinalv2.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by simon on 21/01/2017.
 */

public class Ville extends Destination {

    String urlDetail;
    String name;
    String lg;
    String country;
    String description;
    String star;
    String nbPOI;
    String nbParcours;
    public Ville(JSONObject jsonObject) throws JSONException {

        name= jsonObject.getString("name");
        lg =jsonObject.getString("lg");
        country = jsonObject.getString("country");
        star=jsonObject.getString("stars");
        nbPOI = jsonObject.getString("nbPOis");
        nbParcours = jsonObject.getString("nbParcours");
        description=jsonObject.getString("description");
        urlDetail = "http://voyage2.corellis.eu/api/v2/destination?id=" + this.getId();
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
