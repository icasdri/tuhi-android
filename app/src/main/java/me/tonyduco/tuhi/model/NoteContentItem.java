package me.tonyduco.tuhi.model;

import com.orm.SugarRecord;

import java.util.UUID;

/**
 * Created by Tony on 7/10/2015.
 */
public class NoteContentItem extends SugarRecord<NoteContentItem> {
    private String note_content_id;
    private String note;
    private String data;
    private long date_created;

    public NoteContentItem(String note_content_id, String note, String data, long date_created){
        this.note_content_id = note_content_id;
        this.note = note;
        this.data = data;
        this.date_created = date_created;
    }

    public NoteContentItem(String note, String data){
        this(UUID.randomUUID().toString(), note, data, System.currentTimeMillis()/1000);
    }


    public String getNoteContentId(){
        return note_content_id;
    }

    public String getNote(){
        return note;
    }

    public String getData(){
        return data;
    }

    public long getDateCreated(){
        return date_created;
    }

    public void setNoteContentId(String note_content_id){
        this.note_content_id = note_content_id;
    }

    public void setNote(String note){
        this.note = note;
    }

    public void setData(String data){
        this.data = data;
    }

    public void setDateCreated(long date_created){
        this.date_created = date_created;
    }
}
