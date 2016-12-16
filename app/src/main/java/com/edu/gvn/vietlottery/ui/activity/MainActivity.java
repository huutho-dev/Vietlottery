package com.edu.gvn.vietlottery.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.adapter.LotteryPagerAdapter;
import com.edu.gvn.vietlottery.broadcast.NotifycationPublisher;
import com.edu.gvn.vietlottery.ui.fragment.Max4DFragment;
import com.edu.gvn.vietlottery.ui.fragment.Mega645Fragment;

import java.util.Calendar;

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LotteryPagerAdapter mLottAdapter;
    private Mega645Fragment megaFragment;
    private Max4DFragment maxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialog();
//        mTabLayout = (TabLayout) findViewById(R.id.tab_lottery);
//        mViewPager = (ViewPager) findViewById(R.id.viewpager_lottery);
//
//        maxFragment = new Max4DFragment();
//        megaFragment = new Mega645Fragment();
//
//        mLottAdapter = new LotteryPagerAdapter(this, getSupportFragmentManager());
//        mLottAdapter.addFragment(megaFragment, getResources().getString(R.string.tab_mega));
//        mLottAdapter.addFragment(maxFragment, getResources().getString(R.string.tab_max));
//
//        mViewPager.setAdapter(mLottAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);

        //set Push notify
        scheduleNotify(18, 0, 0);


    }

    /**
     * set push notify
     *
     * @param hour
     * @param minute
     * @param second
     */
    private void scheduleNotify(int hour, int minute, int second) {
        Intent notiIntent = new Intent(this, NotifycationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarAlarm = Calendar.getInstance();

        calendarAlarm.set(Calendar.HOUR_OF_DAY, hour);
        calendarAlarm.set(Calendar.MINUTE, minute);
        calendarAlarm.set(Calendar.SECOND, second);
        calendarAlarm.set(Calendar.MILLISECOND, 0);
        long tringgerTime = calendarAlarm.getTimeInMillis();

        if (calendarAlarm.before(calendarNow)) {
            tringgerTime += 86400000L;
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, tringgerTime, pendingIntent);
    }

    private void showDialog() {


        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .icon(getResources().getDrawable(R.drawable.ball_red))
                .threshold(3)
                .title("How was your experience with us?")
                .titleTextColor(R.color.black)
                .positiveButtonText("Not Now")
                .negativeButtonText("Never")
                .positiveButtonTextColor(R.color.white)
                .negativeButtonTextColor(R.color.grey_500)
                .formTitle("Submit Feedback")
                .formHint("Tell us where we can improve")
                .formSubmitText("Submit")
                .formCancelText("Cancel")
                .ratingBarColor(R.color.accent)
                .positiveButtonBackgroundColor(R.color.accent)
                .negativeButtonBackgroundColor(R.color.accent)
                .formCancelText("Cancel")
                .build();



        ratingDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

//        if (isNetworkConnection()) {
//            maxFragment.requestData(MainActivity.this);
//            megaFragment.requestData(MainActivity.this);
//        } else {
//            checkInternet();
//        }

    }
}
