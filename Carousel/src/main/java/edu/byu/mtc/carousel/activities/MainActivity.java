package edu.byu.mtc.carousel.activities;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import edu.byu.mtc.carousel.R;
import edu.byu.mtc.carousel.fragments.InformativeUpdateFragment;

public class MainActivity extends Activity {
    private int tick =0;
    private Handler mHandler = new Handler();
    private InformativeUpdateFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.container);
        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
       /* container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveFragment();
            }
        });*/
       // fragment = new InformativeUpdateFragment(50,50);
        moveFragment();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(3333);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                // Write your code here to update the UI.
                                moveFragment();
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void moveFragment(){
       FragmentTransaction ft = getFragmentManager().beginTransaction();
        /*ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);*/
        /*View view = fragment.getView().findViewById(R.id.image);
        ImageView imageView= (ImageView) view;
        if(tick%2==0)
            imageView.setImageResource(R.drawable.babygiraffe);
        else
            imageView.setImageResource(R.drawable.ic_launcher);*/
        RelativeLayout v= (RelativeLayout) findViewById(R.id.container);
        int height= v.getMeasuredHeight();
        int width= v.getMeasuredWidth();

        ArrayList<InformativeUpdateFragment> fragments = new ArrayList();
        fragments.add(new InformativeUpdateFragment(width, height, 0));
        fragments.add( new InformativeUpdateFragment(width, height, (int)(height*.66666)));
        //AnimationSet anim = new AnimationSet(true);
        //anim.addAnimation(new TranslateAnimation(width,0,height,height));
        for(InformativeUpdateFragment frag: fragments){
            ft.add(R.id.container,frag);
        }
        ft.commit();


        /*for(InformativeUpdateFragment frag: fragments){
            ft.add(R.id.container,frag);
        }
// Start the animated transition.
        ft.commit();*/
    }
    public void removeFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
}
