package com.bw.movie.adper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.banner.BannerResult;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonResult;
import com.bw.movie.bean.home_hotmovie.HotResult;
import com.bw.movie.bean.home_release.ReleaseResult;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe(描述)：首页多条目
 * @data（日期）: 2020/4/19
 * @time（时间）: 18:54
 * @author（作者）: 于晨雷
 **/
public class HomeAdper extends RecyclerView.Adapter {
    List<BannerResult> mBannerResults = new ArrayList<>();
    List<ReleaseResult> mReleaseResults = new ArrayList<>();
    List<ComingSoonResult> mComingSoonResults = new ArrayList<>();
    List<HotResult> mHotResults = new ArrayList<>();
    //    定义条目类型
//    banner类型
    private final int BANNER_TYPE = 1;
    //    正在热映
    private final int RELEASE_TYPE = 2;
    //    即将上映
    private final int COMINGSOON_TYPE = 3;
    //    热门电影
    private final int HOT_TYPE = 4;



    public void onAddBannerAll(List<BannerResult> result) {
        this.mBannerResults.addAll(result);
    }

    public void onAddReleaseAll(List<ReleaseResult> mReleaseResults) {
        this.mReleaseResults.addAll(mReleaseResults);
    }

    public void onAddComingSoonAll(List<ComingSoonResult> mComingSoonResults) {
        this.mComingSoonResults.addAll(mComingSoonResults);
    }

    public void onAddHotAll(List<HotResult> mHotResults) {
        this.mHotResults.addAll(mHotResults);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView ;
        RecyclerView.ViewHolder holder = null;
//        banner
        if (viewType == BANNER_TYPE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner_item, parent, false);
            holder = new BannerViewHodule(itemView);
        }
//        正在热映
        if (viewType == RELEASE_TYPE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_release_item, parent, false);
            holder = new ReleaseViewHodule(itemView);
        }
//        即将上映
        if (viewType == COMINGSOON_TYPE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_comingsoon_item, parent, false);
            holder = new ComingSoonViewHodule(itemView);
        }
//        热门电影
        if (viewType == HOT_TYPE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_item, parent, false);
            holder = new HotViewHodule(itemView);


        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        //        banner
        if (itemViewType == BANNER_TYPE) {
            BannerViewHodule bannerViewHodule = (BannerViewHodule) holder;
            if (bannerViewHodule.homeBanner != null) {
                bannerViewHodule.homeBanner.removeAllViews();
            }
            bannerViewHodule.homeBanner.setData(mBannerResults, null);
            bannerViewHodule.homeBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    String imageUrl = mBannerResults.get(position).getImageUrl();
                    if (imageUrl != null) {
                        Glide.with(view.getContext()).load(imageUrl).into((ImageView) view);
                    }
                }
            });
        }
        //        正在热映

        if (itemViewType == RELEASE_TYPE) {
            ReleaseViewHodule releaseViewHodule = (ReleaseViewHodule) holder;
            ReleaseAdper releaseAdper = new ReleaseAdper();
            releaseAdper.onReleaseAddAll(mReleaseResults);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(releaseViewHodule.itemView.getContext(), RecyclerView.HORIZONTAL, false);
            ((ReleaseViewHodule) holder).rvHot.setLayoutManager(linearLayoutManager);
            ((ReleaseViewHodule) holder).rvHot.setAdapter(releaseAdper);
        }
        //        即将上映
        if (itemViewType == COMINGSOON_TYPE) {
            if (holder instanceof ComingSoonViewHodule) {
                ComingSoonAdper comingSoonAdper = new ComingSoonAdper();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.VERTICAL, false);
                comingSoonAdper.addAll(mComingSoonResults);
                ((ComingSoonViewHodule) holder).rvComingSoon.setLayoutManager(linearLayoutManager);
                ((ComingSoonViewHodule) holder).rvComingSoon.setAdapter(comingSoonAdper);
            }
        }
        //        热门电影
        if (itemViewType == HOT_TYPE) {
            if (holder instanceof HotViewHodule) {
                String horizontalImage = mHotResults.get(0).getHorizontalImage();
                ((HotViewHodule) holder).ivPic.setImageURI(horizontalImage);
                HotAdper hotAdper = new HotAdper();
                hotAdper.addAll(mHotResults);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL, false);
                ((HotViewHodule) holder).rvRelease.setLayoutManager(linearLayoutManager);
                ((HotViewHodule) holder).rvRelease.setAdapter(hotAdper);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_TYPE;
        }
        if (position == 1) {
            return RELEASE_TYPE;
        }
        if (position == 2) {
            return COMINGSOON_TYPE;
        }
        if (position == 3) {
            return HOT_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int total = 0;
        if (mBannerResults.size() > 0) {
            total++;
        }
        if (mReleaseResults.size() > 0) {
            total++;
        }
        if (mComingSoonResults.size() > 0) {
            total++;
        }
        if (mHotResults.size() > 0) {
            total++;
        }
        return total;
    }



    //banner
    public class BannerViewHodule extends RecyclerView.ViewHolder {
        XBanner homeBanner;

        public BannerViewHodule(@NonNull View itemView) {
            super(itemView);
            homeBanner = (XBanner) itemView.findViewById(R.id.home_banner);

        }
    }

    //正在热映
    public class ReleaseViewHodule extends RecyclerView.ViewHolder {
        ImageView ivXin;
        TextView reMove1;
        RecyclerView rvHot;

        public ReleaseViewHodule(@NonNull View itemView) {
            super(itemView);
            ivXin = (ImageView) itemView.findViewById(R.id.iv_xin);
            reMove1 = (TextView) itemView.findViewById(R.id.re_move_release);
            rvHot = (RecyclerView) itemView.findViewById(R.id.rv_hot);

        }
    }

    //    即将上映
    public class ComingSoonViewHodule extends RecyclerView.ViewHolder {
        private ImageView ivHot;
        private TextView reMove2;
        private RecyclerView rvComingSoon;

        public ComingSoonViewHodule(View itemView) {
            super(itemView);
            ivHot = (ImageView) itemView.findViewById(R.id.iv_hot);
            reMove2 = (TextView) itemView.findViewById(R.id.re_move_coming);
            rvComingSoon = (RecyclerView) itemView.findViewById(R.id.rv_coming_soon);

        }
    }

    //    热门电影
    public class HotViewHodule extends RecyclerView.ViewHolder {
        private ImageView ivHotre;
        private TextView reMove3;
        private SimpleDraweeView ivPic;
        private RecyclerView rvRelease;
        public HotViewHodule(View itemView) {
            super(itemView);
            ivHotre = (ImageView) itemView.findViewById(R.id.iv_hotre);
            reMove3 = (TextView) itemView.findViewById(R.id.re_move_hot);
            ivPic = (SimpleDraweeView) itemView.findViewById(R.id.iv_pic);
            rvRelease = (RecyclerView) itemView.findViewById(R.id.rv_hot);

        }
    }
}
