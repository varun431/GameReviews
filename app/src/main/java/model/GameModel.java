package model;

import java.security.PublicKey;

/**
 * Created by HaSh on 11/18/2016.
 */

public class GameModel {
    String name;
    String rating;
    String release;
    String desc;

    public GameModel(String name, String rating, String release, String desc) {
        this.rating = rating;
        this.name = name;
        this.release = release;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getRelease() {
        return release;
    }

    public String getDesc() {
        return desc;
    }
}