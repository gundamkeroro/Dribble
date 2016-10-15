package com.example.fengxinlin.zhuaibo.view.shot_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by fengxinlin on 9/23/16.
 */
public class ShotImageViewHolder extends RecyclerView.ViewHolder {
    SimpleDraweeView image;
    public ShotImageViewHolder(View itemView) {
        super(itemView);
        image = (SimpleDraweeView) itemView;
    }
}
