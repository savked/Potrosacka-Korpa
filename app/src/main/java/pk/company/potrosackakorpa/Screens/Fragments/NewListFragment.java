package pk.company.potrosackakorpa.Screens.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pk.company.potrosackakorpa.R;

public class NewListFragment extends Fragment {

    private View rootView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_newlist, container, false);

        textView = rootView.findViewById(R.id.textview1);

        textView.setText("ASDASDASDADASD");

        return rootView;
    }

    public static NewListFragment newInstance() {
        NewListFragment newListFragment = new NewListFragment();
        return newListFragment;
    }
}
