package me.tonyduco.tuhi.activity.history;

import android.content.Intent;
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
import me.tonyduco.tuhi.activity.MainActivity;
import me.tonyduco.tuhi.activity.historyview.HistoryViewActivity;
import me.tonyduco.tuhi.activity.note.NoteActivity;
import me.tonyduco.tuhi.adapter.HistoryAdapter;
import me.tonyduco.tuhi.decoration.DividerItemDecoration;
import me.tonyduco.tuhi.listener.RecyclerItemClickListener;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

public class HistoryActivity extends ActionBarActivity {

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

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        openHistory(mAdapter.getNoteContent(position));
                    }
                })
        );
    }



    public void openHistory(NoteContentItem noteContent){
        Intent i = new Intent(this, HistoryViewActivity.class);
        i.putExtra("NOTE_CONTENT", noteContent);
        i.putExtra("NOTE_ITEM", NOTE_ITEM);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext().getApplicationContext(), NoteActivity.class);
        i.putExtra("NOTE_ITEM", NOTE_ITEM);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(getApplicationContext().getApplicationContext(), NoteActivity.class);
            i.putExtra("NOTE_ITEM", NOTE_ITEM);
            startActivity(i);
            return true;
        }else
        return super.onOptionsItemSelected(item);
    }
}
