package com.bw.movie.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;

public class TitleView extends LinearLayout {
    private ImageView mIvCmBuck;
    private TextView mTvTitle;
    private LinearLayout mLvBackground;

    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        if (typedArray != null) {
            String string = typedArray.getString(R.styleable.TitleView_text);
            if (string != null) {
                mTvTitle.setText(string);
            }
            if (typedArray != null) {
                Drawable drawable = typedArray.getDrawable(R.styleable.TitleView_background);
                if (drawable != null) {
                    mLvBackground.setBackground(drawable);
                }
            }
        }
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_item, this, true);
        initView(view);
        mIvCmBuck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick();
            }
        });
    }

    private void initView(View view) {
        mIvCmBuck = (ImageView) view.findViewById(R.id.iv_cm_buck);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mLvBackground = (LinearLayout) findViewById(R.id.lv_background);
    }
onClickListener mOnClickListener;

    public void setOnClickListener(onClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    interface onClickListener{
        void onClick();
    }
}
