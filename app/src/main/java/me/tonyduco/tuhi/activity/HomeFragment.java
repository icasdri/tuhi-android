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

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.melnykov.fab.FloatingActionButton;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.adapter.NoteAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;

public class HomeFragment extends Fragment {


    private SuperRecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private NoteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (SuperRecyclerView) rootView.findViewById(R.id.note_view);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView.getRecyclerView());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewNoteActivity.class);
                startActivity(i);
            }
        });


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
            }});

        mRecyclerView.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
                // Fetch more from Api or DB
            }
        }, 10);

            return rootView;
        }

        @Override
        public void onAttach (Activity activity){
            super.onAttach(activity);
        }

        @Override
        public void onDetach () {
            super.onDetach();
        }
    }
