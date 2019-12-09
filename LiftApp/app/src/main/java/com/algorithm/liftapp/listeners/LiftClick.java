package com.algorithm.liftapp.listeners;

import com.algorithm.liftapp.models.LiftModel;

public interface LiftClick {
    void onUpClick(LiftModel pObject, int pos);
    void onDownClick(LiftModel pObject, int pos);
}
