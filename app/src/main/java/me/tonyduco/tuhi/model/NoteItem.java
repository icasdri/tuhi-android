package me.tonyduco.tuhi.model;

import com.orm.SugarRecord;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class NoteItem extends SugarRecord<NoteItem> implements Serializable {
    // SugarORM will provide a long id field (we will use this as "n_local_id")
    private long n_sync_id;
    private long date_created;
    private String packaging_method;
    private String type;

    private static Map<Long, NoteItem> cache = new HashMap<>();

    /**
     * Default constructor for SugarORM
     */
    public NoteItem() {
    }

    /**
     * Constructor to be used internally when creating new Notes
     */
    public NoteItem(PackagingMethod packagingMethod, NoteType type) {
        this.n_sync_id = 0;
        this.date_created = System.currentTimeMillis() / 1000;
        this.packaging_method = packagingMethod.getName();
        this.type = type.getName();
    }

    public static NoteItem forId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("A negative Note id was passed: " + id);
        }

        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            NoteItem item = findAsIterator(NoteItem.class, "id=?", Long.toString(id)).next();
            cache.put(id, item);
            return item;
        }
    }

    public NoteContentItem newContent(JSONObject unpackagedData, int deleted) {
        return new NoteContentItem(this, unpackagedData, deleted);
    }

    public static List<NoteItem> allWithDeletedFlag(int flag) {
        // TODO: this is super-expensive. Will need to optimize (maybe cache which notes are deleted)
        List<NoteItem> list = new ArrayList<>();
        Iterator<NoteItem> iter = NoteItem.findAll(NoteItem.class);
        while (iter.hasNext()) {
            NoteItem n = iter.next();
            if (n.headContent().getDeletedFlag() == flag) {
                list.add(n);
            }
        }
        return list;
    }
    public static List<NoteItem> allSoftDeleted() {
        return allWithDeletedFlag(1);
    }
    public static List<NoteItem> allNonDeleted() {
        return allWithDeletedFlag(0);
    }

    public List<NoteContentItem> allContents() {
        return NoteContentItem.find(NoteContentItem.class, "note = ?", Long.toString(getId()));
    }

    public NoteContentItem headContent(){
        Iterator<NoteContentItem> iter = NoteContentItem.findAsIterator(NoteContentItem.class,
                "note = ?", new String[] { Long.toString(getId()) }, null, "datecreated DESC", "1");
        return iter.next();
    }

    public long getDateCreatedRaw() {
        return date_created;
    }
    public Date getDateCreated(){
        return new Date(date_created * 1000);
    }

    public PackagingMethod getPackagingMethod() {
        return PackagingMethod.forName(this.packaging_method);
    }

    public NoteType getType() {
        return NoteType.forName(this.type);
    }

    public String getTitle() {
        return this.headContent().getTitle();
    }

    public String getContentPreview() {
        return this.headContent().getContentPreview();
    }

    public long getSyncId() {
        return this.n_sync_id;
    }
    public void setSyncId(long syncId) {
        this.n_sync_id = syncId;
    }
}
