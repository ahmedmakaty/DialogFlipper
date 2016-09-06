package com.example.mindabloom.dialogflipper;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlipperActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        CustomDialog pDialog = new CustomDialog(FlipperActivity.this);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setIndeterminate(true);
        pDialog.show();
        //pDialog.setContentView(R.layout.content);
    }
}
