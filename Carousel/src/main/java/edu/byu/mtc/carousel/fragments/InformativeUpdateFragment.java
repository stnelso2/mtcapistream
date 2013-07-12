package edu.byu.mtc.carousel.fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import edu.byu.mtc.carousel.R;
import edu.byu.mtc.carousel.activities.MainActivity;

/**
 * Created by sam on 7/11/13.
 */
public class InformativeUpdateFragment extends Fragment {
    private int pWidth;
    private int pHeight;
    private int width;
    private int y;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment/*
        view= inflater.inflate(R.layout.fragment_informative_update, container, false);
        View fragment= view.findViewById(R.id.fragment);
        width=(int)(pWidth*.666);
        fragment.setLayoutParams(new ViewGroup.LayoutParams(width,(int)(pHeight*.6666)));
       // width= fragment.getMeasuredWidth();
       // int height = view.getMeasuredHeight();
        view.setX(pWidth);
        view.setY(y);
        animate();

        return view;
    }
    public InformativeUpdateFragment(int width, int height,int y){
        this.pWidth=width;
        this.pHeight=height;
        this.y=y;
    }

    public void animate(){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view,
                "x", -width);
        animation.setDuration(10000);
        animation.setInterpolator(new LinearInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ((MainActivity)getActivity()).removeFragment(InformativeUpdateFragment.this);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animation.start();
    }

}
