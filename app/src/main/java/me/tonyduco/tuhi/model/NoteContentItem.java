package me.tonyduco.tuhi.model;

import android.util.Log;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class NoteContentItem extends SugarRecord<NoteContentItem> implements Serializable {
    private String note_content_id;
    private String note;
    private String data;
    private Long datecreated;
    private int type;


    public NoteContentItem(){

    }

    public NoteContentItem(String note_content_id, String note, String data, Long datecreated){
        this.note_content_id = note_content_id;
        this.note = note;
        this.data = data;
        this.datecreated = datecreated;
        this.type = 0;
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

    public String getTitle() {
        if(getData().indexOf("\n") == -1) {
            return getData();
        }else{
            return getData().substring(0, getData().indexOf("\n"));
        }
    }

    public String getContentPreview(){
        if(getData().indexOf("\n") == -1) {
            return "";
        }else{
            return getData().substring(getData().indexOf("\n")+1, getData().length());
        }
    }

    public Long getDateCreated(){
        return datecreated;
    }

    public int getType() {
        return type;
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

    public void setDateCreated(long datecreated){
        this.datecreated = datecreated;
    }

    public void setType(int type){
        this.type = type;
        List<NoteItem> noteDataset = NoteItem.find(NoteItem.class, "noteid = ?", new String[] { note }, null, null, "1");
        noteDataset.get(0).setType(String.valueOf(type));
        noteDataset.get(0).save();
    }
}
