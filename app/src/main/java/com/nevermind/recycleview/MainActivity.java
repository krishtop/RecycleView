package com.nevermind.recycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static final private int CHOOSE_THIEF = 0;
    private ArrayList<Place> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataset = getDataset();



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
       // mRecyclerView.setHasFixedSize(true);
       //layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //adapter
        mAdapter = new RecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_THIEF) {
            if (resultCode == RESULT_OK) {
                Place place = (Place) data.getSerializableExtra(DetailInfo.THIEF);
                int position = data.getIntExtra("position", -1);
                myDataset.set(position, place);
                mAdapter = new RecyclerAdapter(myDataset);
                mRecyclerView.setAdapter(mAdapter);
            }else {

            }
        }

    }

    private ArrayList<Place> getDataset(){
        ArrayList <Place> mDataset = new ArrayList<Place>();


        mDataset.add(0,new Place("Ввавилова, 23", "Доп. офис №9038/01370",12,"0000000000", 0) );
        mDataset.add(1,new Place("Панфилова, 24", "Доп. офис №9138/01354",13,"1111111111", 0) );
        mDataset.add(2,new Place("Левилова, 25", "Доп. офис №9238/01312",14,"2222222222", 0) );
        mDataset.add(3,new Place("Кириллова, 26", "Доп. офис №9338/01373",15,"3333333333", 0) );
        mDataset.add(4,new Place("Педрилова, 27", "Доп. офис №9438/02958",16,"4444444444", 0) );
        return mDataset;
    }
}
