package me.tonyduco.tuhi.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.adapter.HistoryAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;

public class HistoryFragment extends Fragment {

    private SuperRecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        mRecyclerView = (SuperRecyclerView) rootView.findViewById(R.id.history_view);



        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HistoryAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);



        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
