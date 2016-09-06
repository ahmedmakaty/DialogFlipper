package com.example.mindabloom.dialogflipper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Abdelaziz on 9/6/2016.
 */
public class CustomDialog extends ProgressDialog {

    private Context mContext;
    ImageView image;

    List<ImageView> images = new ArrayList<ImageView>();
    RelativeLayout root;
    ViewFlipper flipper;
    Animation animation;
    Animation animation2;
    boolean stop = false;
    int counter = 1;

    public CustomDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.content);

        image = (ImageView)  findViewById(R.id.progress_image);

        root = (RelativeLayout)  findViewById(R.id.root);
    }

    @Override
    public void show() {
        super.show();
        //rotate();
        //AnimationSet set = new AnimationSet(true);
        FlipAnimation flip = new FlipAnimation(image);
        flip.setRepeatMode(Animation.RESTART);
        flip.setRepeatCount(Animation.INFINITE);

        root.startAnimation(flip);
    }
}
