package com.bw.movie.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.R;
import com.bw.movie.adper.HomeAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.banner.BannerResult;
import com.bw.movie.bean.banner.HomeBannerShow;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonResult;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonShow;
import com.bw.movie.bean.home_hotmovie.HotResult;
import com.bw.movie.bean.home_hotmovie.HotShow;
import com.bw.movie.bean.home_release.ReleaseResult;
import com.bw.movie.bean.home_release.ReleaseShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.bean.postion.District;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseNetFragment {

    private int GPS_REQUEST_CODE = 10;

    private static final String TAG = "HomeFragment";
    private XRecyclerView rvHome;
    private HomeAdper mHomeAdper;
    private ImageView ivPositioning;
    private TextView tvCity;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    @Override
    protected int onLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {
        rvHome = (XRecyclerView) view.findViewById(R.id.rv_home);
        ivPositioning = (ImageView) view.findViewById(R.id.iv_Positioning);
        tvCity = (TextView) view.findViewById(R.id.tv_city);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvHome.setLayoutManager(linearLayoutManager);
        rvHome.setPullRefreshEnabled(true);
        rvHome.setLoadingMoreEnabled(true);
        rvHome.setRefreshProgressStyle(ProgressStyle.Pacman);
        rvHome.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

        rvHome.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvHome.setRefreshProgressStyle(ProgressStyle.Pacman);
                        rvHome.refreshComplete();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvHome.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
                        rvHome.loadMoreComplete();
                    }
                }, 2000);

            }
        });
    }

    @Override
    protected void onData() {


        mCPreantent.onBannerData();
        mCPreantent.onDreleaseData(1, 5);
        mCPreantent.onComingsoonData(1, 5);
        mCPreantent.onHotData(1, 5);
//         定位点击事件
        ivPositioning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
                    //开启定位权限,200是标识码
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                } else {
                    MyLocation(getActivity());//开始定位
                }
            }
        });
    }

    @Override
    public void onSuccess(Object o) {
//        banner
        if (o instanceof HomeBannerShow) {
            List<BannerResult> result = ((HomeBannerShow) o).getResult();
            mHomeAdper = new HomeAdper();
            mHomeAdper.onAddBannerAll(result);
            rvHome.setAdapter(mHomeAdper);
        }
//        正在热映
        if (o instanceof ReleaseShow) {
            List<ReleaseResult> result = ((ReleaseShow) o).getResult();
            if (mHomeAdper != null) {
                mHomeAdper.onAddReleaseAll(result);
                mHomeAdper.notifyDataSetChanged();
            }


        }
//        即将上映
        if (o instanceof ComingSoonShow) {
            List<ComingSoonResult> result = ((ComingSoonShow) o).getResult();
            if (mHomeAdper != null) {
                mHomeAdper.onAddComingSoonAll(result);
                mHomeAdper.notifyDataSetChanged();
            }
        }
//        热门电影
        if (o instanceof HotShow) {
            List<HotResult> result = ((HotShow) o).getResult();
            if (mHomeAdper != null) {
                mHomeAdper.onAddHotAll(result);
                mHomeAdper.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: " + mes);
    }





    public void MyLocation(Context context) {
        mlocationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                try {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息

                            String city = amapLocation.getCity();
                            String district = amapLocation.getDistrict();
                            EventBus.getDefault().postSticky(new District(district));
                            //获取当前定位结果来源，如网络定位结果，详见定位类型表
//                            Log.e("定位类型", amapLocation.getLocationType() + "");
//                            Log.e("获取精度信息", amapLocation.getAccuracy() + "");
//                            //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                            Log.e("地址", amapLocation.getAddress());
//                            Log.e("国家信息", amapLocation.getCountry());
//                            Log.e("省信息", amapLocation.getProvince());
//                            Log.e("城市信息",city );
//                            Log.e("城区信息", amapLocation.getDistrict());
//                            Log.e("街道信息", amapLocation.getStreet());
//                            Log.e("街道门牌号信息", amapLocation.getStreetNum());
//                            Log.e("城市编码", amapLocation.getCityCode());
//                            Log.e("地区编码", amapLocation.getAdCode());
//                            Log.e("获取当前定位点的AOI信息", amapLocation.getAoiName());
//                            Log.e("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
//                            Log.e("获取当前室内定位的楼层", amapLocation.getFloor());
//                            Log.e("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");
                            //获取定位时间
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            Log.i("获取定位时间", df.format(date));
                            String s = amapLocation.getStreet() + " " + amapLocation.getStreetNum();
                            tvCity.setText(city);
//                            edit.putString("district", district).commit();
                            // 停止定位
                            mlocationClient.stopLocation();
                        } else {
                            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                            Toast.makeText(getActivity(), "没有权限，请打开权限...", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("定位服务未开启")
                                    .setMessage("请在系统设置中开启定位服务\n" +
                                            "以便为您提供更好的服务")
                                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, GPS_REQUEST_CODE);
                                        }
                                    })
                                    .show();
                        }
                    }
                } catch (Exception e) {
                }

            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(5000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        //启动定位
        mlocationClient.startLocation();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    MyLocation(getActivity());//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getActivity(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);

        } else {
            MyLocation(getActivity());//开始定位
            //Toast.makeText(getActivity(),"已开启定位权限",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 停止定位
        if (null != mlocationClient) {
            mlocationClient.stopLocation();
        }

    }



}
