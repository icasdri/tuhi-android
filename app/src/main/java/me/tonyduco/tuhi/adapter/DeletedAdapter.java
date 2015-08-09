package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;
import com.malinskiy.superrecyclerview.swipe.SwipeLayout;

import java.util.Date;
import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.listener.RecyclerItemClickListener;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;
import me.tonyduco.tuhi.model.NoteType;

public class DeletedAdapter extends BaseSwipeAdapter<DeletedAdapter.ViewHolder> {

    private Activity activity;

    private List<NoteItem> noteDataset = NoteItem.find(NoteItem.class, "type = ?", String.valueOf(NoteType.DELETED));

    public DeletedAdapter(Activity activity){
        super();
        this.activity = activity;
    }

    public class ViewHolder extends BaseSwipeAdapter.BaseSwipeableViewHolder {
        public TextView title;
        public TextView data;
        public Button deletedButton;

        public ViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.note_title);
            data = (TextView) v.findViewById(R.id.note_data);
            deletedButton = (Button) itemView.findViewById(R.id.delete);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deleted_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        SwipeLayout swipeLayout = vh.swipeLayout;
        swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final NoteItem note = noteDataset.get(position);
        Date expiry = new Date(note.getContent().getDateCreated() * 1000);
        holder.title.setText(note.getContent().getTitle());
        holder.data.setText(expiry.toString());
    }

    public void refreshDataset(){
        noteDataset = NoteItem.find(NoteItem.class, "type = ?", String.valueOf(NoteType.DELETED));
    }

    public NoteItem getNote(int position){
        return noteDataset.get(position);
    }

    @Override
    public int getItemCount(){
            return noteDataset.size();
    }

    public void remove(int position) {

        NoteContentItem newContent = new NoteContentItem(noteDataset.get(position).getNoteId(), noteDataset.get(position).getContent().getNote());
        newContent.setType(NoteType.PERMANENTLY_DELETED);
        newContent.save();
        closeItem(position);
        refreshDataset();
        notifyDataSetChanged();
    }

}
