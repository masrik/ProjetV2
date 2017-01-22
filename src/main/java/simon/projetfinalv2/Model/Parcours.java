package simon.projetfinalv2.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by simon on 21/01/2017.
 */

public class Parcours extends Destination{



    String urlDetail;
    String title;
    String distance;
    String loop;
    String star;
    String description;
    String duree;


public Parcours(JSONObject jsonObject) throws JSONException {


    title= jsonObject.getString("title");
    distance = jsonObject.getString("distance");
    star=jsonObject.getString("stars");
    loop = jsonObject.getString("loop");
    description=jsonObject.getString("description");
    urlDetail = "http://voyage2.corellis.eu/api/v2/parcours?id=" + this.getId();
}

    public String getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(String urlDetail) {
        this.urlDetail = urlDetail;
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

    public void setLoop(String loop) {
        this.loop = loop;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
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
