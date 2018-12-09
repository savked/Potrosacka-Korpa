package pk.company.potrosackakorpa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private ArrayList<ListModel> mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listImportance;
        TextView listItemName;
        TextView listExpectedDateTime;
        ImageButton listButton;

        ViewHolder(View itemView) {
            super(itemView);

            listImportance = itemView.findViewById(R.id.listImportance);
            listItemName = itemView.findViewById(R.id.listName);
            listExpectedDateTime = itemView.findViewById(R.id.listExpectedDate);
            listButton = itemView.findViewById(R.id.listButton);
        }
    }

    ListsAdapter(Context context, ArrayList<ListModel> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.custom_lists, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder holder, int position) {
        ListModel list = mList.get(position);

        holder.listItemName.setText(list.getName());

        switch (list.getImportance()) {
            case "grey":
                holder.listImportance.setBackgroundResource(R.drawable.ic_bookmark_grey);
                break;
            case "green":
                holder.listImportance.setBackgroundResource(R.drawable.ic_bookmark_green);
                break;
            case "blue":
                holder.listImportance.setBackgroundResource(R.drawable.ic_bookmark_blue);
                break;
            case "red":
                holder.listImportance.setBackgroundResource(R.drawable.ic_bookmark_red);
                break;
        }

        holder.listExpectedDateTime.setText(list.getDateTime());

        holder.listButton.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listButton:
                // Do something here
                Toast.makeText(mContext, "TEST", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}