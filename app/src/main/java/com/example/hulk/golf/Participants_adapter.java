package com.example.hulk.golf;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by hulk on 5/10/16.
 */
public class Participants_adapter extends FirebaseRecyclerAdapter<Regist_con,ViewHolder> {
    Class<Regist_con> mModelClass;
    protected int mModelLayout;
    Class<ViewHolder> mViewHolderClass;
    FirebaseArray mSnapshots;

    public Participants_adapter(Class<Regist_con> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query rootref) {
        super(modelClass, modelLayout, viewHolderClass, rootref);
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Regist_con regist_con, int i) {
        viewHolder.name.setText(regist_con.getName());
        viewHolder.age.setText(regist_con.getAge());
        viewHolder.location.setText(regist_con.getLocation());

    }
}

