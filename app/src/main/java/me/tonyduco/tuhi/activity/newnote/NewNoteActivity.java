package me.tonyduco.tuhi.activity.newnote;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;
import me.tonyduco.tuhi.model.NoteType;
import me.tonyduco.tuhi.model.PackagingMethod;

public class NewNoteActivity extends ActionBarActivity {

    EditText data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new NewNoteFragment()).commit();

        setContentView(R.layout.fragment_new_note);

        data = (EditText) findViewById(R.id.newnote_content);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(R.id.newnote_content).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.newnote_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newnote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){
            NoteItem note = new NoteItem(PackagingMethod.NONE, NoteType.PLAIN);
            note.save();

            String text = data.getText().toString();
            int index = text.indexOf('\n');
            String title = text.substring(0, index >= 0 ? index : text.length());

            try {
                JSONObject obj = new JSONObject();
                obj.put("title", title);
                obj.put("text", text);
                obj.put("word_wrap", "normal");
                obj.put("spell_check", "off");

                NoteContentItem noteContent = note.newContent(obj, 0);
                noteContent.save();
            } catch (JSONException e) {
                throw new RuntimeException("Unexpected JSON error", e);
            }

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
