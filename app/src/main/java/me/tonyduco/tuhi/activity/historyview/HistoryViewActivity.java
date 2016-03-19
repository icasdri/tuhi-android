package me.tonyduco.tuhi.activity.historyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.MainActivity;
import me.tonyduco.tuhi.activity.history.HistoryActivity;
import me.tonyduco.tuhi.activity.note.NoteActivity;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

public class HistoryViewActivity extends ActionBarActivity {

    public static NoteContentItem NOTE_CONTENT;
    public static NoteItem NOTE_ITEM;
    public static String CONTEXT;
    private Toolbar mToolbar;
    EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NOTE_CONTENT = (NoteContentItem) getIntent().getSerializableExtra("NOTE_CONTENT");
        NOTE_ITEM = (NoteItem) getIntent().getSerializableExtra("NOTE_ITEM");

        if(getIntent().getSerializableExtra("CONTEXT") != null) {
            CONTEXT = (String) getIntent().getSerializableExtra("CONTEXT");
        } else {
            CONTEXT = "HistoryActivity";
        }

        getFragmentManager().beginTransaction().replace(android.R.id.content, new HistoryViewFragment()).commit();
        setContentView(R.layout.fragment_historyview);
        mToolbar = (Toolbar) findViewById(R.id.historyview_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);


        textView = (EditText) findViewById(R.id.historyview_text_view);

        Date expiry = NOTE_CONTENT.getDateCreated();
        mToolbar.setTitle(expiry.toString());

        try {
            switch (NOTE_ITEM.getType()) {
                case PLAIN:
                    JSONObject obj = NOTE_CONTENT.getUnpackagedData();
                    textView.setText(obj.getString("text"));
                    textView.setEnabled(false);
            }
        } catch (JSONException e) {
            throw new RuntimeException("Unexpected JSON error", e);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_historyview, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(CONTEXT.equals("DeletedFragment")){
            Intent i = new Intent(getApplicationContext().getApplicationContext(), MainActivity.class);
            i.putExtra("FRAGMENT", 1);
            startActivity(i);
        }else {
            Intent i = new Intent(this, HistoryActivity.class);
            i.putExtra("NOTE_ITEM", NOTE_ITEM);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            if(CONTEXT.equals("DeletedFragment")){
                Intent i = new Intent(getApplicationContext().getApplicationContext(), MainActivity.class);
                i.putExtra("FRAGMENT", 1);
                startActivity(i);
            }else {
                Intent i = new Intent(this, HistoryActivity.class);
                i.putExtra("NOTE_ITEM", NOTE_ITEM);
                startActivity(i);
            }
            return true;
        }else if(id == R.id.action_restore){
            JSONObject obj = NOTE_CONTENT.getUnpackagedData();
            NoteContentItem newContent = NOTE_ITEM.newContent(obj, 0);
            newContent.save();

            Intent i = new Intent(getApplicationContext(), NoteActivity.class);
            i.putExtra("NOTE_ITEM", NOTE_ITEM);
            startActivity(i);
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }
}
