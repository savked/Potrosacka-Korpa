package pk.company.potrosackakorpa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pk.company.potrosackakorpa.Models.Lists;
import pk.company.potrosackakorpa.R;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Lists> mList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mListImportance;
        public TextView mListItemName;
        public TextView mListExpectedDateTime;

        public ViewHolder(View itemView) {
            super(itemView);

            mListImportance = itemView.findViewById(R.id.listImportance);
            mListItemName = itemView.findViewById(R.id.listName);
            mListExpectedDateTime = itemView.findViewById(R.id.listExpectedDate);
        }
    }

    public ListsAdapter(Context context, ArrayList<Lists> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.custom_lists, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder holder, int position) {
        holder.mListItemName.setText(mList.get(position).getName());

        switch (mList.get(position).getImportance()) {
            case "grey":
                holder.mListImportance.setBackgroundResource(R.drawable.ic_bookmark_grey);
                break;
            case "green":
                holder.mListImportance.setBackgroundResource(R.drawable.ic_bookmark_green);
                break;
            case "blue":
                holder.mListImportance.setBackgroundResource(R.drawable.ic_bookmark_blue);
                break;
            case "red":
                holder.mListImportance.setBackgroundResource(R.drawable.ic_bookmark_red);
                break;
        }

        holder.mListExpectedDateTime.setText(mList.get(position).getDateTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}