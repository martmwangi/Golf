package com.example.hulk.golf;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;

public class Participants extends Activity {
    private RecyclerView mRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        DB connection
        Firebase.setAndroidContext(this);
        final Firebase rootref = new Firebase("https://golfke.firebaseio.com/Members");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //Helps with performance of the recylcler view page
        mRecyclerView.setHasFixedSize(true);
//        This is for the linear manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        Specify the layout manager
        mRecyclerView.setAdapter(mAdapter);

        //Link Adapter
        Participants_adapter participants_adapter = new Participants_adapter(Regist_con.class,R.layout.card,ViewHolder.class,rootref);
        mRecyclerView.setAdapter(participants_adapter);



    }
}
