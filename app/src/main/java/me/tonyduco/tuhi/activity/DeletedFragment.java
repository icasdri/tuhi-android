package me.tonyduco.tuhi.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.SwipeItemManagerInterface;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.adapter.DeletedAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;

public class DeletedFragment extends Fragment {

    private SuperRecyclerView mRecyclerView;
    private TextView mEmptyView;
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

        mEmptyView = (TextView) rootView.findViewById(R.id.empty_deleted_view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAdapter = new DeletedAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        if(mAdapter.getItemCount() == 0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }

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

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.refreshDataset();
        mAdapter.notifyDataSetChanged();

    }

}
