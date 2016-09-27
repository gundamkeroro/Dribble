package com.example.fengxinlin.zhuaibo.view.shot_list;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fengxinlin.zhuaibo.R;
import com.example.fengxinlin.zhuaibo.model.Shot;
import com.example.fengxinlin.zhuaibo.view.base.SpaceItemDecoration;
import com.example.fengxinlin.zhuaibo.zhuaibo.zhuaibo;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fengxinlin on 9/22/16.
 */
public class ShotListFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private static int COUNT_PER_PAGE = 12;

    private ShotListAdapter adapter;

    public static ShotListFragment newInstance() {
        return new ShotListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.spacing_medium)));

        adapter = new ShotListAdapter(new ArrayList<Shot>(), new ShotListAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                AsyncTaskCompat.executeParallel(new LoadShotTask(adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private class  LoadShotTask extends AsyncTask<Void, Void, List<Shot>> {
        int page;

        public LoadShotTask(int page) {
            this.page =page;
        }
        @Override
        protected List<Shot> doInBackground(Void... params) {
            try {
                return zhuaibo.getShots(page);
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Shot> shots) {
            if (shots != null) {
                adapter.append(shots);
            } else {
                Snackbar.make(getView(), "Error!", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}


//    private List<Shot> fakeData(int page) {
//        List<Shot> shotList = new ArrayList<>();
//        Random random = new Random();
//        int count = page < 2 ? COUNT_PER_PAGE : 10;
//        for (int i = 0; i < count; ++i) {
//            Shot shot = new Shot();
//            shot.title = "shot" + i;
//            shot.views_count = random.nextInt(10000);
//            shot.likes_count = random.nextInt(200);
//            shot.buckets_count = random.nextInt(50);
//            shot.description = makeDescription();
//
//            shot.user = new User();
//            shot.user.name = shot.title + " author";
//
//            shotList.add(shot);
//        }
//        return shotList;
//    }
//
//    private static final String[] words = {
//            "bottle", "bowl", "brick", "building", "bunny", "cake", "car", "cat", "cup",
//            "desk", "dog", "duck", "elephant", "engineer", "fork", "glass", "griffon", "hat", "key",
//            "knife", "lawyer", "llama", "manual", "meat", "monitor", "mouse", "tangerine", "paper",
//            "pear", "pen", "pencil", "phone", "physicist", "planet", "potato", "road", "salad",
//            "shoe", "slipper", "soup", "spoon", "star", "steak", "table", "terminal", "treehouse",
//            "truck", "watermelon", "window"
//    };
//
//    private static String makeDescription() {
//        return TextUtils.join(" ", words);
//    }
//}