package me.tonyduco.tuhi.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Tony on 7/10/2015.
 */
public class NoteItem extends SugarRecord<NoteItem> implements Serializable {

    private String note_id;
    private String title;
    private boolean deleted;
    private long date_modified;

    public NoteItem(){

    }

    public NoteItem(String note_id, String title, boolean deleted, long date_modified){
        this.note_id = note_id;
        this.title = title;
        this.deleted = deleted;
        this.date_modified = date_modified;
    }
    public NoteItem(String title){
        this(UUID.randomUUID().toString(), title, false, System.currentTimeMillis()/1000);
    }

    public String getNoteId(){
        return note_id;
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
