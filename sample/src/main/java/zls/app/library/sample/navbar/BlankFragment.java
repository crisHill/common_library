package zls.app.library.sample.navbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zls.app.library.sample.R;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        int color = this.getResources().getColor(android.R.color.holo_purple);
        String txt = this.getArguments().getString("text", "fragment");
        switch (txt.charAt(txt.length() - 1)){
            case '0':
                color = this.getResources().getColor(android.R.color.holo_purple);
                break;
            case '1':
                color = this.getResources().getColor(android.R.color.holo_blue_light);
                break;
            case '2':
                color = this.getResources().getColor(android.R.color.holo_orange_dark);
                break;
            case '3':
                color = this.getResources().getColor(android.R.color.holo_green_light);
                break;
        }
        v.setBackgroundColor(color);
        ((TextView)v.findViewById(R.id.textView)).setText(txt);
        return v;
    }
}
