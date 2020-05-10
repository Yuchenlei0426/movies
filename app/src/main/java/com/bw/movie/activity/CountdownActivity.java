package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *@describe(描述)：倒计时
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:57
 *@author（作者）: 于晨雷
 **/
public class CountdownActivity extends AppCompatActivity {

    @BindView(R.id.iv_Guidepages)
    ImageView ivGuidepages;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;

    private int recLen = 3;

    Timer timer = new Timer();
    private Runnable runnable;
    private Handler handler;
    private boolean farg = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        ButterKnife.bind(this);
        //把图片资源文件变成数组，注意R文件中数据对应的都是int类型
        int[] mArray = {
                R.drawable.countdown_first,
                R.drawable.countdown_second,
                R.drawable.countdown_third,
                R.drawable.countdown_fourth,
                R.drawable.countdown_fifth,
                R.drawable.countdown_sixth
        };

        //生成随机数，设置为5，是[0,5)，包含0而不包含5。0,1,2,3,4 五个数
        Random random = new Random();
        int index = random.nextInt(6);


        //随机图片对应R文件的int值：mArray[index]，实例Drawable类
        Drawable drawable = getResources().getDrawable(mArray[index]);

        //设置图片
        ivGuidepages.setImageDrawable(drawable);
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (farg) {
                    //从闪屏界面跳转到首界面
                    Intent intent = new Intent(CountdownActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);

    }


    final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(runnable = new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tvIntroduction.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tvIntroduction.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };
    @OnClick(R.id.tv_introduction)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_introduction:
                farg = false;
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(CountdownActivity.this,HomeActivity.class);
//                启动activity
                startActivity(intent);
//                关闭页面
                finish();
                handler.removeCallbacks(runnable);
                break;
        }
    }
}
