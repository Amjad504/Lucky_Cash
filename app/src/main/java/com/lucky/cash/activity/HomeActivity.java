package com.lucky.cash.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.lucky.cash.R;
import com.lucky.cash.utils.MyApp;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class HomeActivity extends AppCompatActivity {

    TextView tvPoints;
    RelativeLayout back_color;
    RelativeLayout reward_layout;

    @SuppressLint({"SetJavaScriptEnabled", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        WebView webView = findViewById(R.id.web);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})


        GifImageView coin_bags = findViewById(R.id.coin_bag);
        GifImageView progress_bar = findViewById(R.id.progress_anim);
        GifImageView coin_collect = findViewById(R.id.coin_collect);
        ImageView dollar_icon = findViewById(R.id.dollar_icon);
        back_color = findViewById(R.id.reward_backColor);
        reward_layout = findViewById(R.id.reward_layout);
        CardView double_btn = findViewById(R.id.double_value);


        GifDrawable gifDrawable = (GifDrawable) progress_bar.getDrawable();
        GifDrawable gifDrawable_coins = (GifDrawable) coin_collect.getDrawable();

//
        gifDrawable.reset();
        gifDrawable.setSpeed(0.0833f);

        gifDrawable.addAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationCompleted(int loopNumber) {
                // Hide the ImageView after the animation has completed
                gifDrawable_coins.reset();
                progress_bar.setVisibility(View.GONE);
                dollar_icon.setVisibility(View.GONE);
                coin_collect.setVisibility(View.VISIBLE);

                // Define a Handler
                Handler handler = new Handler();

// Delay the visibility changes by 4 seconds (4000 milliseconds)
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Show the progress bar and dollar icon, hide the coin collector
                        progress_bar.setVisibility(View.VISIBLE);
                        dollar_icon.setVisibility(View.VISIBLE);
                        coin_collect.setVisibility(View.GONE);
                    }
                }, 4500);

            }
        });


        AnimationSet zoomAnimation = new AnimationSet(true);
        zoomAnimation.addAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in));
        zoomAnimation.addAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_out));





        Handler handler = new Handler();

// Delay the visibility changes by 3 minutes (180000 milliseconds)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Show the progress bar and dollar icon, hide the coin collector
                reward_layout.setVisibility(View.VISIBLE);
                back_color.setVisibility(View.VISIBLE);
                double_btn.startAnimation(zoomAnimation);

                // Schedule the next animation after 3 minutes
                handler.postDelayed(this, 180000);
            }
        }, 180000);


        double_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reward_layout.setVisibility(View.GONE);
                back_color.setVisibility(View.GONE);
            }
        });

        View parentView = (View) coin_bags.getParent(); // Get the parent view of the image
        int parentWidth = parentView.getWidth(); // Get the width of the parent view

        ObjectAnimator animX = ObjectAnimator.ofFloat(coin_bags, "translationX", 0f, 650f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(coin_bags, "translationY", 0f, 400f);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.setDuration(6000); // Set the duration of the animation in milliseconds

        ObjectAnimator second_animX = ObjectAnimator.ofFloat(coin_bags, "translationX", 650f, -250f);
        ObjectAnimator second_animY = ObjectAnimator.ofFloat(coin_bags, "translationY", 400f, 800f);
        AnimatorSet second_animSetXY = new AnimatorSet();
        second_animSetXY.playTogether(second_animX, second_animY);
        second_animSetXY.setDuration(6000); // Set the duration of the animation in milliseconds




        ObjectAnimator third_animX = ObjectAnimator.ofFloat(coin_bags, "translationX", -250f, 630f);
        ObjectAnimator third_animY = ObjectAnimator.ofFloat(coin_bags, "translationY", 800f, 1200f);
        AnimatorSet third_animSetXY = new AnimatorSet();
        third_animSetXY.playTogether(third_animX, third_animY);
        third_animSetXY.setDuration(6000); // Set the duration of the animation in milliseconds


        ObjectAnimator fourth_animX = ObjectAnimator.ofFloat(coin_bags, "translationX", 650f, -250f);
        ObjectAnimator fourth_animY = ObjectAnimator.ofFloat(coin_bags, "translationY", 1200f, 1580f);
        AnimatorSet fourth_animSetXY = new AnimatorSet();
        fourth_animSetXY.playTogether(fourth_animX, fourth_animY);
        fourth_animSetXY.setDuration(6000); // Set the duration of the animation in milliseconds





        animSetXY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                second_animSetXY.start();
            }
        });


        second_animSetXY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                third_animSetXY.start();
            }
        });

        third_animSetXY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fourth_animSetXY.start();
            }
        });

        fourth_animSetXY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animSetXY.start();
            }
        });


        animSetXY.start();


//
//
//// Create the first animation that moves the image diagonally
//        Animation firstAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 0f, // start X position
//                Animation.RELATIVE_TO_SELF, 1f, // end X position
//                Animation.RELATIVE_TO_SELF, 0f, // start Y position
//                Animation.RELATIVE_TO_SELF, 1f // end Y position
//        );
//
//// Set the first animation duration and repeat count
//        firstAnimation.setDuration(6000);
////        firstAnimation.setRepeatCount(Animation.INFINITE);
//
//// Set fillAfter to true to make the image stay at the end position
//        firstAnimation.setFillAfter(true);
//
//// Apply the first animation to the image view
//        coin_bags.startAnimation(firstAnimation);
//
//// Create the second animation that moves the image in the secondary diagonal
//        Animation secondAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 1f, // start X position (set to end position of first animation)
//                Animation.RELATIVE_TO_SELF, -0.5f, // end X position
//                Animation.RELATIVE_TO_SELF, 1f, // start Y position (set to end position of first animation)
//                Animation.RELATIVE_TO_SELF, 2f // end Y position
//        );
//
//// Set the second animation duration and repeat count
//        secondAnimation.setDuration(6000);
////        secondAnimation.setRepeatCount(Animation.INFINITE);
//
//// Set fillAfter to true to make the image stay at the end position
//        secondAnimation.setFillAfter(true);
//
//// Create an animation listener to apply the second animation after the first animation ends
//        firstAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                // Do nothing
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                // Apply the second animation after the first animation ends
//                coin_bags.startAnimation(secondAnimation);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                // Do nothing
//            }
//        });
//
//        // Create the third animation that moves the image in the primary diagonal
//        Animation thirdAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, -0.5f, // start X position (set to end position of second animation)
//                Animation.RELATIVE_TO_SELF, 1f, // end X position
//                Animation.RELATIVE_TO_SELF, 2f, // start Y position (set to end position of second animation)
//                Animation.RELATIVE_TO_SELF, 2.85f // end Y position
//        );
//
//// Set the third animation duration and repeat count
//        thirdAnimation.setDuration(6000);
//// thirdAnimation.setRepeatCount(Animation.INFINITE);
//
//// Set fillAfter to true to make the image stay at the end position
//        thirdAnimation.setFillAfter(true);
//
//// Create an animation listener to apply the third animation after the second animation ends
//        secondAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                // Do nothing
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                // Apply the third animation after the second animation ends
//                coin_bags.startAnimation(thirdAnimation);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                // Do nothing
//            }
//        });
//
//        // Create a fourth animation listener to start the first animation again
//        Animation.AnimationListener fourthAnimationListener = new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                // Do nothing
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                // Start the first animation again
//                coin_bags.startAnimation(firstAnimation);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                // Do nothing
//            }
//        };
//
//
//
//// Set the fourth animation listener on the third animation
//        thirdAnimation.setAnimationListener(fourthAnimationListener);
//
//


        coin_bags.setClickable(true);
        coin_bags.setFocusable(true);
        coin_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                coin_bags.clearAnimation();
                CoinBag_Click(v);
            }
        });
        tvPoints = findViewById(R.id.tvPoints);
        ProgressBar pb = findViewById(R.id.pb);

        pb.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true); //for wa web
        webView.getSettings().setAllowContentAccess(true); // for camera
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(true); //for audio messages

        webView.getSettings().setDomStorageEnabled(true); //for html5 app
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setBlockNetworkLoads(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {

            }



            @Override
            public void onPageFinished(WebView view, String url) {
                pb.setVisibility(View.GONE);
            }
        });


        webView.loadUrl("https://www.advocaten.nl/");




    }



    @Override
    protected void onStart() {
        super.onStart();
        refreshCoin();
    }

    public void refreshCoin(){
        tvPoints.setText(MyApp.GetCoin()+"");
    }


    public void CoinBag_Click(View view) {
        Toast.makeText(HomeActivity.this, "Coin Bag Clicked!", Toast.LENGTH_LONG).show();



    }
}