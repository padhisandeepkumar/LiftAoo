package com.algorithm.liftapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.algorithm.liftapp.R;

public class ExecutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execution);

        String val = getIntent().getExtras().getString(getString(R.string.MACRO_MESSAGE), "");

        TextView textExecution = findViewById(R.id.txt_execution);
        String executionText = String.format(getString(R.string.lbl_execution), val);
        textExecution.setText(executionText);
    }
}