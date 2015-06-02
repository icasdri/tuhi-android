package me.tonyduco.tuhi;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Note> notes;
    static View.OnClickListener tuhiOnClickListener;
    private static ArrayList<Integer> removedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Tuhi
        tuhiOnClickListener = new TuhiOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        notes = new ArrayList<Note>();

        for(int i=0; i<NoteData.noteArray.length; i++){
            notes.add(new Note(NoteData.noteArray[i], NoteData.infoArray[i], NoteData.id_[i]));

        }
        removedItems = new ArrayList<Integer>();
        adapter = new NoteAdapter(notes);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TuhiListener

    private static class TuhiOnClickListener implements View.OnClickListener {

        private final Context context;

        private TuhiOnClickListener(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View v){
           // removeItem(v);
        }

        private void removeItem(View v){
            int selecedItemPos = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selecedItemPos);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for(int i=0; i < NoteData.noteArray.length; i++){
                if(selectedName.equals(NoteData.noteArray[i])){
                    selectedItemId = NoteData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            notes.remove(selecedItemPos);
            adapter.notifyItemRemoved(selecedItemPos);


        }

    }
}



