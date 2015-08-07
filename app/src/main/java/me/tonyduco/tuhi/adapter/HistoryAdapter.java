package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

/**
 * Created by Tony on 8/5/2015.
 */
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

    public class ViewHolder extends RecyclerView.ViewHolder{
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
        holder.date.setText(String.valueOf(noteContent.getDateCreated()));
        holder.title.setText(noteContent.getContentPreview());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO STUFF HERE
            }
        });
    }

    @Override
    public int getItemCount(){
        return historyDataset.size();
    }


}