package com.example.fengxinlin.zhuaibo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.TextView;

import com.example.fengxinlin.zhuaibo.R;
import com.example.fengxinlin.zhuaibo.zhuaibo.auth.Auth;
import com.example.fengxinlin.zhuaibo.zhuaibo.auth.AuthActivity;
import com.example.fengxinlin.zhuaibo.zhuaibo.zhuaibo;
import com.example.fengxinlin.zhuaibo.zhuaibo.zhuaiboException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fengxinlin on 9/22/16.
 */
public class LoginActivity extends AppCompatActivity{
    @BindView(R.id.activity_login_btn) TextView loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        zhuaibo.init(this);

        if (!zhuaibo.isLoggedIn()) {
            CookieSyncManager cookieSyncMngr =
                    CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Auth.openAuthActivity(LoginActivity.this);
                }
            });
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Auth.REQ_CODE && resultCode == RESULT_OK) {
            final String authCode = data.getStringExtra(AuthActivity.KEY_CODE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String token = Auth.fetchAccessToken(authCode);
                        zhuaibo.login(LoginActivity.this, token);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException | JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (zhuaiboException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
