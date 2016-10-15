package com.example.fengxinlin.zhuaibo.view.bucket_list;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.fengxinlin.zhuaibo.view.base.SingleFragmentActivity;
import com.example.fengxinlin.zhuaibo.view.shot_list.ShotListFragment;

/**
 * Created by fengxinlin on 10/14/16.
 */
public class BucketShotListActivity extends SingleFragmentActivity {
    public static final String KEY_BUCKET_NAME = "bucketName";

    @NonNull
    @Override
    protected String getActivityTitle() {
        return getIntent().getStringExtra(KEY_BUCKET_NAME);
    }

    @NonNull
    @Override
    protected Fragment newFragment() {
        String bucketId = getIntent().getStringExtra(ShotListFragment.KEY_BUCKET_ID);
        return bucketId == null
                ? ShotListFragment.newInstance(ShotListFragment.LIST_TYPE_POPULAR)
                : ShotListFragment.newBucketListInstance(bucketId);
    }
}
