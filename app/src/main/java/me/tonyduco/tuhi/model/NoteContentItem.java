package me.tonyduco.tuhi.model;

import com.orm.SugarRecord;

import java.util.Comparator;
import java.util.UUID;

/**
 * Created by Tony on 7/10/2015.
 */
public class NoteContentItem extends SugarRecord<NoteContentItem> {
    private String note_content_id;
    private String note;
    private String data;
    private Long date_created;
    private int type;


    public NoteContentItem(){

    }

    public NoteContentItem(String note_content_id, String note, String data, Long date_created){
        this.note_content_id = note_content_id;
        this.note = note;
        this.data = data;
        this.date_created = date_created;
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
        return date_created;
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

    public void setDateCreated(long date_created){
        this.date_created = date_created;
    }

    public void setType(int type){
        this.type = type;
    }

}
