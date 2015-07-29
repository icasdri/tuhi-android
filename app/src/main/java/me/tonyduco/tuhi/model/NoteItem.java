package me.tonyduco.tuhi.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tony on 7/10/2015.
 */
public class NoteItem extends SugarRecord<NoteItem> implements Serializable {

    private String noteid;
    private String deleted;
    private long date_modified;

    public NoteItem(String note_id, String deleted, long date_modified){
        this.noteid = note_id;
        this.deleted = deleted;
        this.date_modified = date_modified;
    }
    public NoteItem(){
        this(UUID.randomUUID().toString(), "0", System.currentTimeMillis()/1000);
    }

    public String getNoteId(){
        return noteid;
    }

    public NoteContentItem getContent(){
        List<NoteContentItem> noteContentDataset = NoteContentItem.find(NoteContentItem.class, "note = ?", noteid);
        Collections.sort(noteContentDataset, new Comparator<NoteContentItem>(){
            public int compare(NoteContentItem a, NoteContentItem b){
                return b.getDateCreated().compareTo(a.getDateCreated());
            }
        });
        return noteContentDataset.get(0);
    }

    public String getDeleted(){
        return deleted;
    }

    public long getDateModified(){
        return date_modified;
    }

    public void setNoteId(String note_id){
        this.noteid = note_id;
    }

    public void setDeleted(String deleted){
        this.deleted = deleted;
    }

    public void setDateModified(long date_modified){
        this.date_modified = date_modified;
    }
}
