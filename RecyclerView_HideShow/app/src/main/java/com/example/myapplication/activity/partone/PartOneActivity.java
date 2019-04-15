package com.example.myapplication.activity.partone;

import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.partone.RecyclerAdapter;
import com.example.myapplication.listener.partone.HidingScrollListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class PartOneActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mFabButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeRed);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);

        initToolbar();
        mFabButton = (Button) findViewById(R.id.fabButton);
        imageView = (ImageView) findViewById(R.id.adView);


        imageView.animate().translationY(-imageView.getHeight()).setInterpolator(new AccelerateInterpolator(2)).withEndAction(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(GONE);
            }
        });
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mFabButton.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mFabButton.animate().translationY(mFabButton.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2));

        imageView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).withStartAction(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

        imageView.animate().translationY(-imageView.getHeight()).setInterpolator(new AccelerateInterpolator(2)).withEndAction(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(GONE);
            }
        });


    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemList.add("Item " + i);
        }
        return itemList;
    }
}