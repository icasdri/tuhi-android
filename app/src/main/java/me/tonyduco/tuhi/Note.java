package me.tonyduco.tuhi;

/**
 * Created by tony on 5/31/15.
 */
public class Note {
    String title;
    String information;
    int id_;

    Note(String title, int id_){
        this.title = title;
        this.information = "";
        this.id_ = id_;
    }

    Note(String title, String information, int id_){
        this.title = title;
        this.information = information;
        this.id_ = id_;
    }

    public String getTitle(){
        return title;
    }

    public String getInformation(){
        return information;
    }

    public int getId(){
        return id_;
    }
}
