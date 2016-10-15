package com.example.fengxinlin.zhuaibo.view.base;

import android.os.AsyncTask;

import com.example.fengxinlin.zhuaibo.zhuaibo.zhuaiboException;

/**
 * Created by fengxinlin on 9/29/16.
 */
public abstract class zhuaiboTask <Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    private zhuaiboException exception;

    protected abstract Result doJob(Params... params) throws zhuaiboException;

    protected abstract void onSuccess(Result result);

    protected abstract void onFailed(zhuaiboException e);

    @Override
    protected Result doInBackground(Params... params) {
        try {
            return doJob(params);
        } catch (zhuaiboException e) {
            e.printStackTrace();
            exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        if (exception != null) {
            onFailed(exception);
        } else {
            onSuccess(result);
        }
    }
}

