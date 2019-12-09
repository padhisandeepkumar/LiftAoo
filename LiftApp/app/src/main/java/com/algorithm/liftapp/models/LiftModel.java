package com.algorithm.liftapp.models;

public class LiftModel {

    private int FloorNo;
    private boolean Current;

    public int getFloorNo() {
        return FloorNo;
    }

    public void setFloorNo(int floorNo) {
        FloorNo = floorNo;
    }

    public boolean isCurrent() {
        return Current;
    }

    public void setCurrent(boolean current) {
        Current = current;
    }
}