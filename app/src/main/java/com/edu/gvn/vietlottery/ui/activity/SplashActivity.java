package com.edu.gvn.vietlottery.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.edu.gvn.vietlottery.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int TIME_SPLASH = 1000;
    private View view;
    private Button tryAgain;
    private Dialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        view = LayoutInflater.from(this).inflate(R.layout.dialog_network_conn, null, false);

        createDialog();
        checkInternet();

    }

    private void checkInternet() {
        if (isNetworkConnection()) {
            goToHome();
        } else {
            showDialog();
        }
    }

    private void goToHome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, TIME_SPLASH);
    }

    private void createDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        tryAgain = (Button) view.findViewById(R.id.btn_try_again);
        tryAgain.setOnClickListener(this);
    }
    private void showDialog(){
        dialog.show();
    }
    private void dissmisDialog(){
        dialog.dismiss();
    }

    private boolean isNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onClick(View view) {
        dissmisDialog();
        checkInternet();
    }
}
