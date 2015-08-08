package me.tonyduco.tuhi.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class NoteItem extends SugarRecord<NoteItem> implements Serializable {

    private String noteid;
    private String type;
    private long date_modified;

    public NoteItem(String note_id, String type, long date_modified){
        this.noteid = note_id;
        this.type = type;
        this.date_modified = date_modified;
    }
    public NoteItem(){
        this(UUID.randomUUID().toString(), "0", System.currentTimeMillis()/1000);
    }

    public String getNoteId(){
        return noteid;
    }

    public NoteContentItem getContent(){
        List<NoteContentItem> noteContentDataset = NoteContentItem.find(NoteContentItem.class, "note = ?", new String[] {noteid}, null, "datecreated DESC", "1");
        return noteContentDataset.get(0);
    }

    public String getType(){
        return type;
    }

    public long getDateModified(){
        return date_modified;
    }

    public void setNoteId(String note_id){
        this.noteid = note_id;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setDateModified(long date_modified){
        this.date_modified = date_modified;
    }
}
