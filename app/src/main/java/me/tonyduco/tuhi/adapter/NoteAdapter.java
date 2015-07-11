package me.tonyduco.tuhi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.tonyduco.tuhi.R;

/**
 * Created by tony on 7/10/15.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<String> mDataset = new ArrayList<String>() {{
        add("Test note");
        add("hehe");
        add("Tuhi = cewl");
        add("pls work");
        add("xmls shouldnt exist");
        add("java?");
    }};


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

    public void remove(String item){
        int position = mDataset.indexOf(item);
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
                remove(name);
            }
        });
        holder.data.setText("asdf");
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}
