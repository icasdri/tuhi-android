package me.tonyduco.tuhi.activity.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.MainActivity;
import me.tonyduco.tuhi.activity.history.HistoryActivity;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;
import me.tonyduco.tuhi.model.NoteType;

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
        textView.setOnClickListener(editTextClickListener);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);

        mToolbar.setTitle(NOTE_ITEM.getContent().getTitle());
        textView.setText(NOTE_ITEM.getContent().getData().toString());

    }

    View.OnClickListener editTextClickListener = new View.OnClickListener()

    {

        public void onClick(View v)
        {
            if (v.getId() == textView.getId())
            {
                textView.setCursorVisible(true);
            }

        }
    };

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
            if (!NOTE_ITEM.getContent().getData().equals(textView.getText().toString())){
                NoteContentItem newContent = new NoteContentItem(NOTE_ITEM.getNoteId(), textView.getText().toString());
            newContent.save();
            }
//            onBackPressed(); Used to emulate back button (deprecated to fix HistoryActivity)
            Intent i = new Intent(getApplicationContext().getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }
        if(id == R.id.action_delete){
            NoteContentItem newContent = new NoteContentItem(NOTE_ITEM.getNoteId(), textView.getText().toString());
            newContent.setType(NoteType.DELETED);
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
