package me.tonyduco.tuhi.model;

import java.util.UUID;

/**
 * Created by Tony on 7/10/2015.
 */
public class NoteItem {

    private String note_id;
    private int user;
    private String title;
    private boolean deleted;
    private long date_modified;

    public NoteItem(String note_id, int user, String title, boolean deleted, long date_modified){
        this.note_id = note_id;
        this.user = user;
        this.title = title;
        this.deleted = deleted;
        this.date_modified = date_modified;
    }
    public NoteItem(int user, String title){
        this.note_id = UUID.randomUUID().toString();
        this.user = user;
        this.title = title;
        this.deleted = false;
        this.date_modified = System.currentTimeMillis()/1000;
    }

    public String getNoteId(){
        return note_id;
    }

    public int getUser(){
        return user;
    }

    public String getTitle(){
        return title;
    }

    public boolean isDeleted(){
        return deleted;
    }

    public long getDateModified(){
        return date_modified;
    }

    public void setNoteId(String note_id){
        this.note_id = note_id;
    }

    public void setUser(int user){
        this.user = user;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

    public void setDateModified(long date_modified){
        this.date_modified = date_modified;
    }
}
