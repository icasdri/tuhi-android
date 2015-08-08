package me.tonyduco.tuhi.activity.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.history.HistoryActivity;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteEnum;
import me.tonyduco.tuhi.model.NoteItem;

/**
 * Created by tony on 7/12/15.
 */
public class NoteActivity extends ActionBarActivity {

    public static NoteItem NOTE_ITEM;
    private Toolbar mToolbar;
    EditText textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NOTE_ITEM = (NoteItem) getIntent().getSerializableExtra("NOTE_ITEM");

        getFragmentManager().beginTransaction().replace(android.R.id.content, new NoteFragment()).commit();

        setContentView(R.layout.fragment_note);

        mToolbar = (Toolbar) findViewById(R.id.note_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView = (EditText) findViewById(R.id.note_text_view);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);

        mToolbar.setTitle(NOTE_ITEM.getContent().getTitle());
        textView.setText(NOTE_ITEM.getContent().getData().toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            NoteContentItem newContent = new NoteContentItem(NOTE_ITEM.getNoteId(), textView.getText().toString());
            newContent.save();
            onBackPressed();
            return true;
        }
        if(id == R.id.action_delete){
            List<NoteItem> noteDataset = NoteItem.find(NoteItem.class, "noteid = ?", NOTE_ITEM.getNoteId());
            noteDataset.get(0).setType("1");
            noteDataset.get(0).save();
            NoteContentItem newContent = new NoteContentItem(NOTE_ITEM.getNoteId(), textView.getText().toString());
            newContent.save();
            onBackPressed();
            return true;
        }
        if(id == R.id.action_history){
            Intent i = new Intent(this, HistoryActivity.class);
            i.putExtra("NOTE_ITEM", NOTE_ITEM);
            startActivity(i);
        }
        return false;
    }
}
