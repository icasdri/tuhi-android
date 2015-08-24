package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Activity activity;
    private NoteItem NOTE_ITEM;
    private List<NoteContentItem> historyDataset;

    public HistoryAdapter(Activity activity){
        super();
        this.activity = activity;
        NOTE_ITEM = (NoteItem) this.activity.getIntent().getSerializableExtra("NOTE_ITEM");
        historyDataset = NoteContentItem.find(NoteContentItem.class, "note = ?", NOTE_ITEM.getNoteId());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView title;

        public ViewHolder(View v){
            super(v);
            date = (TextView) v.findViewById(R.id.history_date);
            title = (TextView) v.findViewById(R.id.history_title);
        }

    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final NoteContentItem noteContent = historyDataset.get(position);
        Date expiry = new Date(noteContent.getDateCreated() * 1000);
        holder.date.setText(expiry.toString());
        holder.title.setText(noteContent.getTitle());
    }

    @Override
    public int getItemCount(){
        return historyDataset.size();
    }

    public NoteContentItem getNoteContent(int position){
       return historyDataset.get(position);
    }


}
