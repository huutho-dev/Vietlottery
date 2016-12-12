package com.edu.gvn.vietlottery.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.utils.LogUtils;

/**
 * Created by hnc on 06/12/2016.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener
{


    private View view;
    private Button tryAgain;
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = LayoutInflater.from(this).inflate(R.layout.dialog_network_conn, null, false);
        createDialog();
        checkInternet();
    }

    public void checkInternet() {
        if (isNetworkConnection()) {
            LogUtils.i("vietlott","Network avaiable");
        } else {
            showDialog();
        }
    }

    private void createDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        tryAgain = (Button) view.findViewById(R.id.btn_try_again);
        tryAgain.setOnClickListener(this);
    }

    private void showDialog() {
        dialog.show();
    }

    private void dissmisDialog() {
        dialog.dismiss();
    }

    public boolean isNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onClick(View view) {
        dissmisDialog();
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
    }
}
