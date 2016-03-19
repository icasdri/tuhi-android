package me.tonyduco.tuhi.activity.note;

import android.app.Fragment;

public abstract class NoteFragmentGeneral extends Fragment {
    abstract boolean hasUnsavedChanges();
    abstract void saveChanges();
}
