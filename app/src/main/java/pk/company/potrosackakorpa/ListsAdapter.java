package pk.company.potrosackakorpa;

import android.content.Context;
import android.opengl.Visibility;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListsAdapter extends ArrayAdapter<Lists> {

    private Lists list;

    public ListsAdapter(Context context, ArrayList<Lists> list) {
        super(context, R.layout.custom_lists, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        list = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.custom_lists, parent, false);

        View listImportance = (View) customView.findViewById(R.id.listImportance);
        TextView listNameTv = (TextView) customView.findViewById(R.id.listName);
        TextView listExpectedDateTv = (TextView) customView.findViewById(R.id.listExpectedDate);

        switch(list.getImportance()){
            case "grey":
                listImportance.setBackgroundResource(R.drawable.ic_bookmark_grey);
                break;
            case "green":
                listImportance.setBackgroundResource(R.drawable.ic_bookmark_green);
                break;
            case "blue":
                listImportance.setBackgroundResource(R.drawable.ic_bookmark_blue);
                break;
            case "red":
                listImportance.setBackgroundResource(R.drawable.ic_bookmark_red);
                break;
        }
        listNameTv.setText(list.getName());
        listExpectedDateTv.setText(list.getDateTime());

        return customView;
    }
}
