package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.model.NoteEnum;
import me.tonyduco.tuhi.model.NoteItem;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Activity activity;
   // private List<NoteItem> noteDataset = NoteItem.find(NoteItem.class, "type = ?", String.valueOf(NoteEnum.PLAINTEXT));
    private List<NoteItem> noteDataset = NoteItem.find(NoteItem.class, "type = ?", "0");

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
        holder.data.setText(note.getContent().getContentPreview());
    }

    public void refreshDataset(){
        //noteDataset = NoteItem.find(NoteItem.class, "type = ?", String.valueOf(NoteEnum.PLAINTEXT));
        noteDataset = NoteItem.find(NoteItem.class, "type = ?", "0");
        Log.d("Tuhi", "NoteEnum === " + NoteEnum.PLAINTEXT);
    }

    public NoteItem getNote(int position){
        return noteDataset.get(position);
    }

    @Override
    public int getItemCount(){
            return noteDataset.size();
    }
}
