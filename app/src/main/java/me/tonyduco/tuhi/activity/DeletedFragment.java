package me.tonyduco.tuhi.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.SwipeItemManagerInterface;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.note.NoteActivity;
import me.tonyduco.tuhi.adapter.DeletedAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;
import me.tonyduco.tuhi.listener.RecyclerItemClickListener;
import me.tonyduco.tuhi.model.NoteItem;

public class DeletedFragment extends Fragment {

    private SuperRecyclerView mRecyclerView;
    private DeletedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public DeletedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deleted, container, false);
        mRecyclerView = (SuperRecyclerView) rootView.findViewById(R.id.deleted_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAdapter = new DeletedAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        mRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refreshDataset();
                mAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mAdapter.remove(position);
                    }
                })
        );

        return rootView;
    }

    public void openNote(NoteItem note){
        Intent i = new Intent(getActivity(), NoteActivity.class);
        i.putExtra("NOTE_ITEM", note);
        getActivity().startActivity(i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.refreshDataset();
        mAdapter.notifyDataSetChanged();
    }
}
