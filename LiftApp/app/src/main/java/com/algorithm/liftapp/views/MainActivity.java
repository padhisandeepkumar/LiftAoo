package com.algorithm.liftapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.algorithm.liftapp.adapter.LiftAdapter;
import com.algorithm.liftapp.listeners.LiftClick;
import com.algorithm.liftapp.models.LiftModel;
import com.algorithm.liftapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LiftClick, View.OnClickListener {

    private List<LiftModel> listOfFloors;
    private List<LiftModel> listOfUpClickEvents;
    private List<LiftModel> listOfDownClickEvents;
    private RecyclerView liftList;
    private LiftAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        UiInitalisation();
    }

    private void UiInitalisation() {
        listOfUpClickEvents = new ArrayList<>();
        listOfDownClickEvents = new ArrayList<>();

        // Initialization of UI elements
        liftList = findViewById(R.id.list_lift);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        liftList.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        liftList.addItemDecoration(itemDecor);

        // ON CLICK EVENT REGISTRATIONS
        findViewById(R.id.btn_start).setOnClickListener(this);

        // STATIC SIX FLOORS ADDED AS PART OF REQUIREMENT
        sixFloors();
    }

    private void sixFloors() {
        listOfFloors = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            LiftModel lift = new LiftModel();
            lift.setFloorNo(i + 1);
            listOfFloors.add(lift);
        }
        LiftModel lift = new LiftModel();
        lift.setFloorNo(0);
        lift.setCurrent(true);
        listOfFloors.add(lift);

        // Loading on Adapter
        adapter = new LiftAdapter(this, listOfFloors);
        liftList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDownClick(LiftModel pObject, int pos) {
        // logic to get sort and store on list
        listOfDownClickEvents.add(pObject);
        listOfDownClickEvents.sort(Comparator.comparing(LiftModel::getFloorNo));
        Collections.reverse(listOfDownClickEvents);
        current(pos);
    }

    @Override
    public void onUpClick(LiftModel pObject, int pos) {
        // logic to get sort and store on list
        listOfUpClickEvents.add(pObject);
        listOfUpClickEvents.sort(Comparator.comparing(LiftModel::getFloorNo));
        current(pos);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            trasnmitToNextScreen();
        }
    }

    private void current(int pos) {
        // logic to get notified Current Lift
        for (int i = 0; i < listOfFloors.size(); i++) {
            listOfFloors.get(i).setCurrent(pos == i);
        }
        adapter.notifyDataSetChanged();
    }

    private void trasnmitToNextScreen() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < listOfUpClickEvents.size(); i++) {
            Log.e("String UP", "" + listOfUpClickEvents.get(i).getFloorNo());
            if (builder.length() > 0) {
                builder.append("->");
            }
            builder.append(listOfUpClickEvents.get(i).getFloorNo());
        }
        for (int i = 0; i < listOfDownClickEvents.size(); i++) {
            Log.e("String DOWN", "" + listOfDownClickEvents.get(i).getFloorNo());
            if (builder.length() > 0) {
                builder.append("->");
            }
            builder.append(listOfDownClickEvents.get(i).getFloorNo());
        }

        // CALLING EXECUTION ACTIVITY
        Intent intent = new Intent(this, ExecutionActivity.class);
        intent.putExtra(getString(R.string.MACRO_MESSAGE), builder.toString());
        startActivity(intent);
    }
}
