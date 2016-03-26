package me.tonyduco.tuhi.activity.note;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.MainActivity;
import me.tonyduco.tuhi.activity.history.HistoryActivity;
import me.tonyduco.tuhi.model.NoteContentItem;
import me.tonyduco.tuhi.model.NoteItem;

public class NoteActivity extends ActionBarActivity {

    public static NoteItem NOTE_ITEM;
    private Toolbar mToolbar;
    private NoteFragmentGeneral frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NOTE_ITEM = NoteItem.forId(getIntent().getLongExtra("NOTE_ID", -1));

        int fragLayoutId = -1;
        boolean unknownType = false;

        switch (NOTE_ITEM.getType()) {
            case PLAIN:
                frag = new PlainNoteFragment();
                fragLayoutId = R.layout.fragment_note_plain;
                break;
        }

        getFragmentManager().beginTransaction().replace(android.R.id.content, frag).commit();
        setContentView(fragLayoutId);
        mToolbar = (Toolbar) findViewById(R.id.note_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setTitle(NOTE_ITEM.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }


    void saveChangesIfNeeded() {
        if (frag.hasUnsavedChanges()) {
            frag.saveChanges();
            Toast.makeText(NoteActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    void exitToMainActivity() {
        Intent i = new Intent(getApplicationContext().getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        saveChangesIfNeeded();
        exitToMainActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {

            if (frag.hasUnsavedChanges()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.TuhiDialogStyle);
                builder.setTitle("Discard Changes");
                builder.setMessage("Unsaved changes have been made to this note! Would you like to discard them or go back?");
                builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(NoteActivity.this, "Changes Discarded!", Toast.LENGTH_SHORT).show();
                        exitToMainActivity();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }else{
                exitToMainActivity();
            }
            return true;
        }

        if(id == R.id.action_save){
            saveChangesIfNeeded();
            exitToMainActivity();
            return true;
        }

        if(id == R.id.action_delete){
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.TuhiDialogStyle);
            builder.setTitle("Delete Note");
            builder.setMessage("Are you sure you would like to delete this note?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    JSONObject data = NoteActivity.NOTE_ITEM.headContent().getUnpackagedData();
                    NoteContentItem deleteNc = NOTE_ITEM.newContent(data, 1);
                    deleteNc.save();
                    onBackPressed();
                    Toast.makeText(NoteActivity.this, "Note Deleted!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
            return true;
        }

        if(id == R.id.action_history){
            Intent i = new Intent(this, HistoryActivity.class);
            i.putExtra("NOTE_ID", NOTE_ITEM.getId());
            startActivity(i);
        }
        return false;
    }
}
