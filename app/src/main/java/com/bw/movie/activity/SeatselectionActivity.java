package com.bw.movie.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.bw.movie.R;
import com.bw.movie.adper.movie_hall_adper.MovieScheduleAdper;
import com.bw.movie.adper.seatInfo_adper.SeatInfoAdper;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.evenbean.ScheduleEvent;
import com.bw.movie.bean.evenbean.SeatseleCineamaIdShow;
import com.bw.movie.bean.movieschedule.MovieSchedule;
import com.bw.movie.bean.movieschedule.MovieScheduleShow;
import com.bw.movie.bean.order_show.OrderShow;
import com.bw.movie.bean.pay.PayShow;
import com.bw.movie.bean.seatInfo.SaetInfoShow;
import com.bw.movie.bean.seatInfo.SeatInfo;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe(描述)：选座
 * @data（日期）: 2020/5/10
 * @time（时间）: 21:57
 * @author（作者）: 于晨雷
 **/
public class SeatselectionActivity extends BaseNetActivity implements View.OnClickListener {
    private static final String TAG = "SeatselectionActivity";
    private int mMovieId;
    private PopupWindow mPopupWindow;
    private RadioGroup mRgPay;
    private RadioButton mRbWeChatPay;
    private RadioButton mRbAlipayPay;
    private androidx.recyclerview.widget.RecyclerView mSeatRv;
    private androidx.recyclerview.widget.RecyclerView mRvFindMovieSchedule;
    private android.widget.Button mButOrder;
    private android.widget.Button mButRoom;
    private int mCinemaId;
    private MovieScheduleAdper mMovieScheduleAdper;
    int scheduleId;
    private int cinemaId;
    private int movieId;
    int hall;
    double fare1;
    double money;
    private int mHallId;
    private double mFare;
    private int mId;
    private String mFormat;
    private ArrayList<String> mStrings;
    private String soft;
    private int mUserId;
    private String mSessionId;
    int paytype ;
    private android.widget.RelativeLayout mLinerLay;
    private ImageView mImagGb;
    private android.widget.TextView weChatPay;
    private RadioButton mRadioZzfb;
    private RadioButton mRadioWx;

    public static int flag = 0;

    @Override
    protected int onlayout() {
        return R.layout.activity_seatselection;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {
        initView();
        EventBus.getDefault().register(this);
//        but_order

        mImagGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinerLay.setVisibility(View.GONE);
            }
        });


    }

    @Subscribe(sticky = true)
    public void cinemaIdShow(SeatseleCineamaIdShow seatseleCineamaIdShow) {
        mCinemaId = seatseleCineamaIdShow.getCinemaId();

    }

    @Subscribe(sticky = true)
    public void ScheduleEventShow(ScheduleEvent scheduleEvent) {
        mMovieId = scheduleEvent.getMovieId();

    }

    @Override
    protected void onData() {
        //    根据电影ID 和影院ID 查询影厅
        mCPreantent.onFindMovieScheduleData(mMovieId, mCinemaId);
        mStrings = new ArrayList<>();
        //    根据影厅ID 查询座位


    }


    @Override
    public void onSuccess(Object o) {

        //    根据电影ID 和影院ID 查询影厅
        if (o instanceof MovieScheduleShow) {
            List<MovieSchedule> result = ((MovieScheduleShow) o).getResult();
            if (result == null) {
                Toast.makeText(SeatselectionActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                mMovieScheduleAdper = new MovieScheduleAdper();
                mMovieScheduleAdper.addAll(result);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SeatselectionActivity.this, RecyclerView.HORIZONTAL, false);
                mRvFindMovieSchedule.setLayoutManager(linearLayoutManager);
                mRvFindMovieSchedule.setAdapter(mMovieScheduleAdper);
                mMovieScheduleAdper.setOnClickListener(new MovieScheduleAdper.OnClickListener() {
                    @Override
                    public void onSeatClicked(int hallId, double fare, int id) {
                        hall = hallId;
                        mCPreantent.onFindSeatInfoData(hallId);
                        fare1 = fare;
                        money = 0.0;
                        mButRoom.setText("￥ " + money);
                        scheduleId = id;
                    }
                });
                for (int i = 0; i < result.size(); i++) {
                    mHallId = ((MovieScheduleShow) o).getResult().get(i).getHallId();
                    mCPreantent.onFindSeatInfoData(mMovieId);
                    mFare = ((MovieScheduleShow) o).getResult().get(i).getFare();
                    money = 0;
                    mButRoom.setText("￥ " + money);
                    mId = ((MovieScheduleShow) o).getResult().get(i).getId();
                }
            }
        }
        //    根据影厅ID 查询座位
        if (o instanceof SaetInfoShow) {
            List<SeatInfo> result = ((SaetInfoShow) o).getResult();
            if (result != null) {
                SeatInfoAdper seatInfoAdper = new SeatInfoAdper();
                seatInfoAdper.addAll(result);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SeatselectionActivity.this, RecyclerView.VISIBLE, false);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SeatselectionActivity.this, 5, RecyclerView.VERTICAL, false);
                mSeatRv.setLayoutManager(gridLayoutManager);
                mSeatRv.setAdapter(seatInfoAdper);
                seatInfoAdper.setSetOnclickListener(new SeatInfoAdper.setOnclickListener() {



                    @Override
                    public void onClicked(int row, int seat, boolean isChecked) {
                        if (isChecked == true) {
                            soft = row + "-" + seat;
                            mStrings.add(soft);
                            Toast.makeText(SeatselectionActivity.this, row + "排" + seat + "座", Toast.LENGTH_SHORT).show();
                            money = money + fare1;
                            DecimalFormat df = new DecimalFormat("0.00");
                            mFormat = df.format(money);
                            mButRoom.setText("￥ " + mFormat);
                        } else {
                            soft = row + "-" + seat;
                            mStrings.remove(soft);
                            money = money - fare1;
                            DecimalFormat df = new DecimalFormat("0.00");
                            String format = df.format(money);
                            mButRoom.setText("￥" + format);
                        }
                    }
                });
            } else {
                Toast.makeText(SeatselectionActivity.this, "无数据", Toast.LENGTH_SHORT).show();
            }
        }

        if (o instanceof OrderShow) {
            String orderId = ((OrderShow) o).getOrderId();
            mCPreantent.onPayData(mUserId,mSessionId,1,orderId);
        }
        if (o instanceof PayShow) {

            String message = ((PayShow) o).getMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            PayReq payReq = new PayReq();
            String appId = ((PayShow) o).getAppId();
            String partnerId = ((PayShow) o).getPartnerId();
            String prepayId = ((PayShow) o).getPrepayId();
            String nonceStr = ((PayShow) o).getNonceStr();
            String timeStamp = ((PayShow) o).getTimeStamp();
            String packageValue = ((PayShow) o).getPackageValue();
            String sign = ((PayShow) o).getSign();
            payReq.appId =appId;
            payReq.partnerId = partnerId;
            payReq.prepayId = prepayId;
            payReq.nonceStr = nonceStr;
            payReq.timeStamp = timeStamp;
            payReq.packageValue = packageValue;
            payReq.sign = sign;

            payReq.extData = "app data"; // optional
//            App.api.sendReq(payReq);
            App.getWxApi().sendReq(payReq);
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: " + mes);
    }

    private void initView() {
        mSeatRv = (RecyclerView) findViewById(R.id.seat_rv);
        mRvFindMovieSchedule = (RecyclerView) findViewById(R.id.rv_findMovieSchedule);
        mButOrder = (Button) findViewById(R.id.but_order);
        mButRoom = (Button) findViewById(R.id.but_room);

        mLinerLay = (RelativeLayout) findViewById(R.id.liner_lay);
        mImagGb = (ImageView) findViewById(R.id.imag_gb);
        weChatPay = (TextView) findViewById(R.id.we_chat_pay);
        mRadioZzfb = (RadioButton) findViewById(R.id.radio_zzfb);
        mRadioWx = (RadioButton) findViewById(R.id.radio_wx);
        mButRoom.setOnClickListener(this::onClick);
        mButOrder.setOnClickListener(this::onClick);
        weChatPay.setOnClickListener(this::onClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


/*//    PopWindow弹框
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pay_popw_item, null, false);
        mRgPay = view.findViewById(R.id.rg_pay);
//        支付宝支付
        mRbWeChatPay = view.findViewById(R.id.rb_weChat_Pay);
//        微信支付
        mRbAlipayPay = view.findViewById(R.id.rb_Alipay_pay);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        mPopupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        mPopupWindow.showAsDropDown(v, 50, 50);

        //设置popupWindow里的按钮的事件

    }*/





    public static void wechatPay(Activity activity, String appId) {

        if (activity == null)
            return;
        // 将该app注册到微信
        final IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, appId);

        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(activity, "您尚未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_room:
                if (flag == 0) {
                    // 第一次单击触发的事件
                    mLinerLay.setVisibility(View.VISIBLE);
                    flag = 1;

                } else {
                    // 第二次单击buttont改变触发的事件
                    SharedPreferences qw = getSharedPreferences("zw", Context.MODE_PRIVATE);
                    String seat = qw.getString("seat", null);
                    paytype =1;
                    SharedPreferences mLogin = App.mLogin;
                    mSessionId = mLogin.getString("sessionId", "");
                    mUserId = mLogin.getInt("userId", -1);
                    String signs = mUserId + mSessionId + mMovieId;
                    String sign = EncryptUtils.encryptMD5ToString(signs);
                    mCPreantent.onBuyMovieTicketsData(mUserId, mSessionId,seat,sign);
                    flag = 0;
                }
                break;
            case R.id.but_order:

                break;
            case R.id.we_chat_pay:

                break;
        }
    }
}
