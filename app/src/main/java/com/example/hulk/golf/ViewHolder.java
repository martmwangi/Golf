package com.example.hulk.golf;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hulk on 5/10/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    TextView name,age,location;
    public ViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.textView3);
        age = (TextView) itemView.findViewById(R.id.textView5);
        location = (TextView) itemView.findViewById(R.id.textView4);
    }
}
