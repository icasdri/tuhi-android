package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
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
        NOTE_ITEM = NoteItem.forId(this.activity.getIntent().getLongExtra("NOTE_ID", -1));
        historyDataset = NOTE_ITEM.allContents();
        Collections.reverse(historyDataset);

        //Removes the current NoteItem (hence cannot restore it itself)
        historyDataset.remove(0);
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
        Date expiry = noteContent.getDateCreated();
        holder.date.setText(formatDate(expiry));
        holder.title.setText(noteContent.getTitle());
    }

    public String formatDate(Date date){
        return new SimpleDateFormat("MMM d, hh:mm:ss aa").format(date);
    }

    @Override
    public int getItemCount(){
        return historyDataset.size();
    }

    public NoteContentItem getNoteContent(int position){
       return historyDataset.get(position);
    }


}
