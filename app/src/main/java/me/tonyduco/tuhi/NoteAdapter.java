package me.tonyduco.tuhi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by tony on 5/31/15.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private ArrayList<Note> noteDataSet;

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewInfo;

        public NoteViewHolder(View itemView){
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewInfo = (TextView) itemView.findViewById(R.id.textViewInfo);
        }

    }

    public NoteAdapter(ArrayList<Note> notes){
        this.noteDataSet = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        view.setOnClickListener(MainActivity.tuhiOnClickListener);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;
        TextView textViewInfo = holder.textViewInfo;

        textViewName.setText(noteDataSet.get(listPosition).getTitle());
        textViewInfo.setText(noteDataSet.get(listPosition).getInformation());
    }

    @Override
    public int getItemCount() {
        return noteDataSet.size();
    }

}
