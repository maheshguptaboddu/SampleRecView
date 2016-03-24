package mockapp.yashas.com.codesampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mahesh on 3/23/16.
 */
public class RecyclerAd extends RecyclerView.Adapter<RecyclerAd.ViewHolder> {

    private ArrayList<ServerResponse.Entry> mData;

    public RecyclerAd(ArrayList<ServerResponse.Entry> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_row, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (this.mData != null) {
            ServerResponse.Entry response = this.mData.get(position);
            if (response != null) {
                holder.textView.setText(response.author);
                holder.contentView.setText(response.content);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (this.mData != null) {
            return this.mData.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView, contentView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtViewTitle);
            contentView = (TextView) itemView.findViewById(R.id.txtViewContent);
        }
    }

}
