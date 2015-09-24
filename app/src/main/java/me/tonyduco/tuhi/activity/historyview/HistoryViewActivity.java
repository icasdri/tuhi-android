package me.tonyduco.tuhi.activity.historyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import java.util.Date;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.note.NoteActivity;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

public class HistoryViewActivity extends ActionBarActivity {

    public static NoteContentItem NOTE_CONTENT;
    private Toolbar mToolbar;
    EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NOTE_CONTENT = (NoteContentItem) getIntent().getSerializableExtra("NOTE_CONTENT");

        getFragmentManager().beginTransaction().replace(android.R.id.content, new HistoryViewFragment()).commit();
        setContentView(R.layout.fragment_historyview);
        mToolbar = (Toolbar) findViewById(R.id.historyview_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);


        textView = (EditText) findViewById(R.id.historyview_text_view);

        Date expiry = new Date(NOTE_CONTENT.getDateCreated() * 1000);
        mToolbar.setTitle(expiry.toString());
        textView.setText(NOTE_CONTENT.getData().toString());

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
//            Intent i = new Intent(getApplicationContext().getApplicationContext(), NoteActivity.class);
//            i.putExtra("NOTE_ITEM", NOTE_ITEM);
//            startActivity(i);
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }
}
