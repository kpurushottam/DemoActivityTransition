package com.krp.android.demoactivitytransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void toggleOkBtn(View v) {
        View toogleView = findViewById(R.id.btn_ok);
        if(toogleView.getVisibility() == View.VISIBLE) {
            hide(toogleView);
        } else {
            reveal(toogleView);
        }
    }

    private void reveal(View myView) {
        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = null;
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        }

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        if(anim != null) {
            anim.start();
        }
    }

    private void hide(final View myView) {
        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = myView.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = null;
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        }

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        if(anim != null) {
            anim.start();
        }
    }

    //
    public void search(View btnView) {
        View toggleView = findViewById(R.id.tv_search);

        // get the center for the clipping circle
        int cx = toggleView.getRight() - btnView.getHeight()/2;
        int cy = toggleView.getTop() - toggleView.getHeight() + btnView.getHeight()/2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(toggleView.getWidth(), toggleView.getHeight());

        // get the initial radius for the clipping circle
        int initialRadius = btnView.getWidth();

        if(toggleView.getVisibility() == View.VISIBLE) {
            hide(toggleView, btnView, cx, cy, initialRadius);
        } else {
            reveal(toggleView, btnView, cx, cy, finalRadius);
        }
    }

    private void reveal(View toggleView, View btnView, int cx, int cy, int finalRadius) {
        // create the animator for this view (the start radius is zero)
        Animator anim = null;
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(toggleView, cx, cy, 0, finalRadius);
        }

        // make the view visible and start the animation
        toggleView.setVisibility(View.VISIBLE);
        if(anim != null) {
            anim.start();
        }
    }

    private void hide(final View toggleView, View btnView, int cx, int cy, int initialRadius) {
        // create the animation (the final radius is zero)
        Animator anim = null;
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(toggleView, cx, cy, initialRadius, 0);
        }

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toggleView.setVisibility(View.GONE);
            }
        });

        // start the animation
        if(anim != null) {
            anim.start();
        }
    }
}
