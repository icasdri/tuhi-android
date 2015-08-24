package me.tonyduco.tuhi.activity.historyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.note.NoteActivity;
import me.tonyduco.tuhi.model.NoteItem;

public class HistoryViewActivity extends ActionBarActivity {

    public static NoteItem NOTE_ITEM;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NOTE_ITEM = (NoteItem) getIntent().getSerializableExtra("NOTE_ITEM");

        setContentView(R.layout.fragment_historyview);

        mToolbar = (Toolbar) findViewById(R.id.historyview_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_historyview, menu);
        return true;
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
