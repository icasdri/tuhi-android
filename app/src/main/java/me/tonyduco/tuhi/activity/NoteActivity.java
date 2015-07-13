package me.tonyduco.tuhi.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.model.NoteItem;

/**
 * Created by tony on 7/12/15.
 */
public class NoteActivity extends ActionBarActivity {

    public static NoteItem NOTE_ITEM;
    //public static String NOTE_ITEM = "";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NOTE_ITEM = (NoteItem) getIntent().getSerializableExtra("note_item");

        getFragmentManager().beginTransaction().replace(android.R.id.content, new NoteFragment()).commit();

        setContentView(R.layout.fragment_note);

        mToolbar = (Toolbar) findViewById(R.id.note_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        EditText textView = (EditText) findViewById(R.id.note_text_view);
        mToolbar.setTitle(NOTE_ITEM.getTitle());
        textView.setText("This is a sample note");

    }
}
