package me.tonyduco.tuhi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.melnykov.fab.FloatingActionButton;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.newnote.NewNoteActivity;
import me.tonyduco.tuhi.activity.note.NoteActivity;
import me.tonyduco.tuhi.adapter.NoteAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;
import me.tonyduco.tuhi.listener.RecyclerItemClickListener;
import me.tonyduco.tuhi.model.NoteItem;

public class HomeFragment extends Fragment {


    private SuperRecyclerView mRecyclerView;
    private TextView mEmptyView;
    private NoteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View rootView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);


        mRecyclerView = (SuperRecyclerView) rootView.findViewById(R.id.note_view);
        mEmptyView = (TextView) rootView.findViewById(R.id.empty_view);



        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAdapter = new NoteAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refreshDataset();
                mAdapter.notifyDataSetChanged();
            }
        });



        //Causes lag when used incorrectly...

//        mRecyclerView.setupMoreListener(new OnMoreListener() {
//            @Override
//            public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
//                // Fetch more from Api or DB
//            }
//        }, 10);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        openNote(mAdapter.getNote(position));
                    }
                })
        );

        if(mAdapter.getItemCount() == 0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }

        return rootView;
    }

    public void openNote(NoteItem note){
        Intent i = new Intent(getActivity(), NoteActivity.class);
        i.putExtra("NOTE_ID", note.getId());
        getActivity().startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewNoteActivity.class);
                startActivity(i);
            }
        });
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
        if(mAdapter.getItemCount() == 0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }
}
