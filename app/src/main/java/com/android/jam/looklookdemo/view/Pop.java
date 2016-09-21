package com.android.jam.looklookdemo.view;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jam on 16/9/21 下午6:03.
 * Describe:A transition that animates the alpha, scale X & Y of a view simultaneously.
 */
public class Pop extends Visibility {

    public Pop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public Animator onAppear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
        return ObjectAnimator.ofPropertyValuesHolder(
                endValues.view,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
        );
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
        return ObjectAnimator.ofPropertyValuesHolder(
                endValues.view,
                PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0)
        );
    }
}
