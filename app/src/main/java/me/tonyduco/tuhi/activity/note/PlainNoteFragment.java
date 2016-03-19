package me.tonyduco.tuhi.activity.note;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.model.NoteContentItem;

public class PlainNoteFragment extends NoteFragmentGeneral {

    EditText textView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = (EditText) getActivity().findViewById(R.id.note_text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == textView.getId()) {
                    textView.setCursorVisible(true);
                }
            }
        });

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);

        JSONObject data = NoteActivity.NOTE_ITEM.headContent().getUnpackagedData();
        try {
            textView.setText(data.getString("text"));
        } catch (JSONException e) {
            throw new RuntimeException("Unexpected JSON error");
        }
    }

    @Override
    boolean hasUnsavedChanges() {
        JSONObject data = NoteActivity.NOTE_ITEM.headContent().getUnpackagedData();
        try {
            return !textView.getText().toString().equals(data.getString("text"));
        } catch (JSONException e) {
            throw new RuntimeException("Unexpected JSON error");
        }
    }

    @Override
    void saveChanges() {
        JSONObject data = NoteActivity.NOTE_ITEM.headContent().getUnpackagedData();
        try {
            data.put("text", textView.getText().toString());
            NoteContentItem nc = NoteActivity.NOTE_ITEM.newContent(data, 0);
            nc.save();
        } catch (JSONException e) {
            throw new RuntimeException("Unexpected JSON error");
        }
    }
}

/*      Autosave Functionality

        textView.addTextChangedListener(new TextWatcher() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            long timeSinceLastChange = -1;
            Thread saveThread;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(timeSinceLastChange == -1){
                    if(saveThread == null){
                        saveThread = new Thread(){
                            @Override
                            public void run(){
                                try {

                                    sleep(prefs.getInt("autosave", 30)*1000);
                                    if (!NOTE_ITEM.getContent().getData().equals(textView.getText().toString())){
                                        NoteContentItem newContent = new NoteContentItem(NOTE_ITEM.getNoteId(), textView.getText().toString());
                                        newContent.save();
                                        Toast.makeText(NoteActivity.this, "Note Auto-saved!", Toast.LENGTH_SHORT).show();
                                    }
                                }catch(InterruptedException ex){
                                }
                            }
                        };
                        saveThread.run();
                        timeSinceLastChange = System.currentTimeMillis() * 1000;
                    }
                }else if((System.currentTimeMillis() * 1000) - timeSinceLastChange >= prefs.getInt("autosave", 30)){
                    if (!NOTE_ITEM.getContent().getData().equals(textView.getText().toString())){
                        saveThread = new Thread(){
                            @Override
                            public void run(){
                                try {

                                    sleep(prefs.getInt("autosave", 30)*1000);
                                    if (!NOTE_ITEM.getContent().getData().equals(textView.getText().toString())){
                                        NoteContentItem newContent = new NoteContentItem(NOTE_ITEM.getNoteId(), textView.getText().toString());
                                        newContent.save();
                                        Toast.makeText(NoteActivity.this, "Note Auto-saved!", Toast.LENGTH_SHORT).show();
                                    }
                                }catch(InterruptedException ex){
                                }
                            }
                        };
                        saveThread.run();
                        timeSinceLastChange = System.currentTimeMillis() * 1000;
                    }
                }
            }
        });
*/
