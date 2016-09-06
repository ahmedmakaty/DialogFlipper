package com.example.mindabloom.dialogflipper;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Abdelaziz on 9/6/2016.
 */
public class FlipAnimation extends Animation {
    private Camera camera;

    private View fromView;
    private View toView;

    private ImageView progressImage;

    private float centerX;
    private float centerY;

    private boolean forward = true;

    List<Integer> indeces = new ArrayList<>();

    int currentIndex=0;

    /**
     * Creates a 3D flip animation between two views.
     *
     * @param fromView First view in the transition.
     * @param toView   Second view in the transition.
     */
    public FlipAnimation(View fromView, View toView) {
        this.fromView = fromView;
        this.toView = toView;

        setDuration(2500);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public FlipAnimation(ImageView image) {
        this.progressImage = image;

        this.indeces.add(R.drawable.ic_speciality1);
        this.indeces.add(R.drawable.ic_speciality2);
        this.indeces.add(R.drawable.ic_speciality3);
        this.indeces.add(R.drawable.ic_speciality4);
        this.indeces.add(R.drawable.ic_speciality5);
        this.indeces.add(R.drawable.ic_speciality6);
        this.indeces.add(R.drawable.ic_speciality7);

        setDuration(2500);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void reverse() {
        forward = false;
        View switchView = toView;
        toView = fromView;
        fromView = switchView;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        // Angle around the y-axis of the rotation at the given time
        // calculated both in radians and degrees.
        final double radians = Math.PI * interpolatedTime;
        float degrees = (float) (180.0 * radians / Math.PI);

        // Once we reach the midpoint in the animation, we need to hide the
        // source view and show the destination view. We also need to change
        // the angle by 180 degrees so that the destination does not come in
        // flipped around
        if (interpolatedTime >= 0.5f) {
            degrees -= 180.f;
//            fromView.setVisibility(View.GONE);
//            toView.setVisibility(View.VISIBLE);
        }

        if (interpolatedTime == 0.5f) {
            progressImage.setImageResource(indeces.get(currentIndex));
            currentIndex = (currentIndex+1)%7;
        }


        if (forward)
            degrees = -degrees; //determines direction of rotation when flip begins

        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}