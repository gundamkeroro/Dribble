package com.example.fengxinlin.zhuaibo.view.shot_list;

import android.view.View;
import android.widget.TextView;

import com.example.fengxinlin.zhuaibo.R;
import com.example.fengxinlin.zhuaibo.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

/**
 * Created by fengxinlin on 9/22/16.
 */

class ShotViewHolder extends BaseViewHolder {

    @BindView(R.id.shot_clickable_cover) public View cover;
    @BindView(R.id.shot_like_count) public TextView likeCount;
    @BindView(R.id.shot_bucket_count) public TextView bucketCount;
    @BindView(R.id.shot_view_count) public TextView viewCount;
    @BindView(R.id.shot_image) public SimpleDraweeView image;

    public ShotViewHolder(View itemView) {
        super(itemView);
    }
}
