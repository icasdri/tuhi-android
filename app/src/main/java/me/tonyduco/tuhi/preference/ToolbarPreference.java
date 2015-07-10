package me.tonyduco.tuhi.preference;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.tonyduco.tuhi.R;
import me.tonyduco.tuhi.activity.MainActivity;

public class ToolbarPreference extends Preference {

    public ToolbarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        parent.setPadding(0, 0, 0, 0);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.settings_toolbar, parent, false);

        Toolbar toolbar = (Toolbar) layout.findViewById(R.id.settings_toolbar);
        //toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setTitle(getTitle());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceScreen prefScreen = (PreferenceScreen) getPreferenceManager().findPreference(getKey());
                prefScreen.getDialog().dismiss();
                //finishActivity();
                //NavUtils.navigateUpFromSameTask();
            }
        });

        return layout;
    }

}
