package com.example.fengxinlin.zhuaibo.view.shot_detail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.fengxinlin.zhuaibo.view.base.SingleFragmentActivity;

/**
 * Created by fengxinlin on 9/23/16.
 */
public class ShotActivity extends SingleFragmentActivity {

    public static final String KEY_SHOT_TITLE = "shot_title";

    @NonNull
    @Override
    protected Fragment newFragment() {
        return ShotFragment.newInstance(getIntent().getExtras());
    }

    @NonNull
    @Override
    protected String getActivityTitle() {
        return getIntent().getStringExtra(KEY_SHOT_TITLE);
    }
}