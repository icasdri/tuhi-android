package me.tonyduco.tuhi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;
import com.malinskiy.superrecyclerview.swipe.SwipeLayout;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.historyview.HistoryViewActivity;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

public class DeletedAdapter extends BaseSwipeAdapter<DeletedAdapter.ViewHolder> {

    private Activity activity;

    private List<NoteItem> noteDataset;

    public DeletedAdapter(Activity activity){
        super();
        this.activity = activity;
        refreshDataset();
    }

    public class ViewHolder extends BaseSwipeAdapter.BaseSwipeableViewHolder {
        public TextView title;
        public TextView data;
        public Button deletedButton;
        public RelativeLayout relativeLayout;

        public ViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.note_title);
            data = (TextView) v.findViewById(R.id.note_data);
            deletedButton = (Button) itemView.findViewById(R.id.delete);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.deleted_info);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deleted_item, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        SwipeLayout swipeLayout = vh.swipeLayout;
        swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openRestore(getNote(vh.getPosition()));
                    }

                });

        vh.deletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(vh.getPosition());
            }
        });

        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final NoteItem note = noteDataset.get(position);
        final NoteContentItem nc = note.headContent();
        holder.title.setText(nc.getTitle());
        holder.data.setText(formatDate(nc.getDateCreated()));
    }

    public void refreshDataset(){
        noteDataset = NoteItem.allSoftDeleted();
    }

    public void openRestore(NoteItem NOTE_ITEM){
        Intent i = new Intent(activity, HistoryViewActivity.class);
        i.putExtra("NOTE_CONTENT", NOTE_ITEM.headContent());
        i.putExtra("NOTE_ID", NOTE_ITEM.getId());
        i.putExtra("CONTEXT", "DeletedFragment");
        activity.startActivity(i);
    }
    public NoteItem getNote(int position){
        return noteDataset.get(position);
    }

    @Override
    public int getItemCount(){
            return noteDataset.size();
    }

    public String formatDate(Date date){
        return new SimpleDateFormat("MMM d, hh:mm:ss aa").format(date);
    }

    public void remove(int position) {
        NoteItem note = noteDataset.get(position);
        for (NoteContentItem nc : note.allContents()) {
            // TODO: this iterative approach may be expensive. May need to consider bulk delete operation
            nc.delete();
        }
        NoteContentItem permaDeleteNc = noteDataset.get(position).newContent(null, 2);
        permaDeleteNc.save();
        closeItem(position);
        refreshDataset();
        notifyDataSetChanged();
        if(position == 0 && noteDataset.isEmpty()){
            TextView mEmptyView = (TextView) activity.findViewById(R.id.empty_deleted_view);
            SuperRecyclerView mRecyclerView = (SuperRecyclerView) activity.findViewById(R.id.deleted_view);
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }
        Toast.makeText(activity, "Note Permanently Deleted!", Toast.LENGTH_SHORT).show();
    }

}
