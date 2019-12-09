package com.algorithm.liftapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.algorithm.liftapp.R;
import com.algorithm.liftapp.listeners.LiftClick;
import com.algorithm.liftapp.models.LiftModel;

import java.util.List;

@SuppressWarnings("ALL")
public class LiftAdapter extends RecyclerView.Adapter<LiftAdapter.MyViewHolder> {

    private Context context;
    private List<LiftModel> listOfFloors;
    private LiftClick objListner;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtFloor;
        Button btnUp, btnDown;

        private MyViewHolder(View itemView) {
            super(itemView);
            this.txtFloor = itemView.findViewById(R.id.txt_floor);
            this.btnDown =  itemView.findViewById(R.id.btn_down);
            this.btnUp = itemView.findViewById(R.id.btn_up);
        }
    }

    public LiftAdapter(Context context, List<LiftModel> listOfFloors) {
        this.context = context;
        this.listOfFloors = listOfFloors;
        this.objListner = (LiftClick) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_floor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int listPosition) {
        final int pos = listPosition;
        final LiftModel liftModel = listOfFloors.get(pos);
        String upString = String.format(context.getString(R.string.lbl_up), "" + liftModel.getFloorNo());
        String downString = String.format(context.getString(R.string.lbl_down), "" + liftModel.getFloorNo());
        holder.btnDown.setText(downString);
        holder.btnUp.setText(upString);

        // TO CHECK IDEAL FLOORS AND CLICKED FLOORS WITH
        if(liftModel.isCurrent()) {
            holder.txtFloor.setBackground(context.getDrawable(R.drawable.border_selected));
        } else /*if(liftModel.isClicked()) */{
            holder.txtFloor.setBackground(context.getDrawable(R.drawable.border_ideal));
        }/* else {
            holder.txtFloor.setBackground(context.getDrawable(R.drawable.border));
        }*/

        // VISIBILITY CHECK
        if (liftModel.getFloorNo() == 6) {
            holder.btnUp.setVisibility(View.INVISIBLE);
        } else {
            holder.btnUp.setVisibility(View.VISIBLE);
        }
        if (liftModel.getFloorNo() == 0) {
            holder.btnDown.setVisibility(View.INVISIBLE);
        } else {
            holder.btnDown.setVisibility(View.VISIBLE);
        }

        // CLICK EVENTS
        holder.btnUp.setOnClickListener((View v) ->
                objListner.onUpClick(liftModel, pos)
        );
        holder.btnDown.setOnClickListener((View v) ->
                objListner.onDownClick(liftModel, pos)
        );
    }

    @Override
    public int getItemCount() {
        return listOfFloors.size();
    }
}