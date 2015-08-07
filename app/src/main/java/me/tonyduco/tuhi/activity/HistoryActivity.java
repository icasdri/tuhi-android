package me.tonyduco.tuhi.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.adapter.HistoryAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;
import me.tonyduco.tuhi.listener.RecyclerItemClickListener;
import me.tonyduco.tuhi.model.NoteItem;

public class HistoryActivity extends ActionBarActivity  implements RecyclerItemClickListener.OnItemClickListener {

    public static NoteItem NOTE_ITEM;
    private Toolbar mToolbar;

    private SuperRecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NOTE_ITEM = (NoteItem) getIntent().getSerializableExtra("NOTE_ITEM");

        setContentView(R.layout.fragment_history);

        mToolbar = (Toolbar) findViewById(R.id.history_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = (SuperRecyclerView) findViewById(R.id.history_view);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HistoryAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));


    }

    @Override
    public void onItemClick(View childView, int position) {
        Log.d("TUHI", "ASDFasdfdsafadsfdasfadsf");
    }

    @Override
    public void onItemLongPress(View childView, int position) {
        Log.d("TUHI", "Long press");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
