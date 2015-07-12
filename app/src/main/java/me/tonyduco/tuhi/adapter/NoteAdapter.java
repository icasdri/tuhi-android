package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.NoteActivity;
import me.tonyduco.tuhi.activity.SettingsActivity;

/**
 * Created by tony on 7/10/15.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<String> mDataset = new ArrayList<String>() {{
        add("Test note");
        add("hehe");
        add("Tuhi = cewl");
        add("pls work");
        add("xmls shouldnt exist");
        add("java?");
    }};

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

    public void add(int position, String item){
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void openNote(String item){
        int position = mDataset.indexOf(item);
        Intent i = new Intent(activity, NoteActivity.class);
        i.putExtra("NOTE_ITEM", "This is a note hahahhahahahahahah");
        activity.startActivity(i);
        mDataset.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final String name = mDataset.get(position);
        holder.title.setText(mDataset.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNote(name);
            }
        });
        holder.data.setText("asdf");
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}
