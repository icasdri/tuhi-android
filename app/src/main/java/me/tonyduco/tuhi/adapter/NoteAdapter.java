package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.OnMoreListener;

import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.NoteActivity;
import me.tonyduco.tuhi.model.NoteItem;

/**
 * Created by tony on 7/10/15.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Activity activity;
    private List<NoteItem> noteDataset = NoteItem.find(NoteItem.class, "deleted = ?", "0");
    public NoteAdapter(Activity activity){
        super();
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView data;

        public ViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.note_title);
            data = (TextView) v.findViewById(R.id.note_data);

        }
    }


    public void openNote(NoteItem note){
        Intent i = new Intent(activity, NoteActivity.class);
        i.putExtra("NOTE_ITEM", note);
        activity.startActivity(i);

        //Use code below to reset view
        //notifyItemRemoved(position);

    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final NoteItem note = noteDataset.get(position);
        holder.title.setText(note.getContent().getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNote(note);
            }
        });
        holder.data.setText(note.getContent().getContentPreview());
    }

    public void refreshDataset(){
        noteDataset = NoteItem.find(NoteItem.class, "deleted = ?", "0");
    }

    @Override
    public int getItemCount(){
            return noteDataset.size();
    }
}
