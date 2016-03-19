package me.tonyduco.tuhi.model;
import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

public class NoteContentItem extends SugarRecord<NoteContentItem> implements Serializable {
    // SugarORM will provide a long id field (we will use this as "nc_local_id")
    private long note;
    private long nc_sync_id;
    private long date_created;
    private int deleted;
    private String packaged_data;


    /**
     * Default constructor for SugarORM
     */
    public NoteContentItem(){
    }

    /**
     * Constructor used by the newContent() method of NoteItem to create new Note Contents
     */
    public NoteContentItem(NoteItem note, JSONObject unpackagedData, int deleted, Properties props) {
        this.note = note.getId();
        this.nc_sync_id = 0;
        this.date_created = System.currentTimeMillis() / 1000;
        this.deleted = deleted;
        PackagingMethod method = note.getPackagingMethod();
        this.packaged_data = method.pack(unpackagedData.toString(), props);
    }

    public JSONObject getUnpackagedData(Properties props){
        NoteItem note = NoteItem.forId(this.note);
        PackagingMethod method = note.getPackagingMethod();
        try {
            return new JSONObject(method.unpack(this.packaged_data, props));
        } catch (JSONException e) {
            throw new IllegalStateException("Unexpected invalid JSON in database!", e);
        }
    }

    public long getDateCreatedRaw() {
        return date_created;
    }
    public Date getDateCreated(){
        return new Date(date_created * 1000);
    }

    public String getTitle(Properties props) {
        try {
            return getUnpackagedData(props).getString("title");
        } catch (JSONException e) {
            throw new IllegalStateException("Unexpected invalid JSON in database!", e);
        }
    }

    public String getContentPreview(Properties props){
        try {
            JSONObject o = getUnpackagedData(props);
            switch (o.getString("type")) {
                case "plain":
                   String s = o.getString("text");
                   if (!s.contains("\n")) {
                        return "";
                   } else {
                       return s.substring(s.indexOf("\n")+1, s.length());
                   }

                default: return "";
            }
        } catch (JSONException e) {
            throw new IllegalStateException("Unexpected invalid JSON in database!", e);
        }
    }

    public long getSyncId() {
        return this.nc_sync_id;
    }
    public void setSyncId(long syncId) {
        this.nc_sync_id = syncId;
    }
}
