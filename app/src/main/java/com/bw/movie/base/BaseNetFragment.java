package com.bw.movie.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.contract.IContract;
import com.bw.movie.preantent.CPreantent;

public abstract class BaseNetFragment extends Fragment implements IContract.IView {
    private static final String TAG = "BaseNetFragment";
    public CPreantent mCPreantent;
    private boolean mViewinflate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(onLayout(), container, false);
        Log.i(TAG, "onCreateView: ");
        mCPreantent = (CPreantent) initPreantent();
        if (mCPreantent != null) {
            mCPreantent.onAcctachView(this);
        }
        onView(view);
        mViewinflate =true;
//        onData();
        doNetWork();
        return view;
    }
    private void doNetWork(){
        if(getUserVisibleHint()){
            onData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mViewinflate&&isVisibleToUser) {
            onData();
            Log.i(TAG, "setUserVisibleHint: ");
        }

    }

    protected abstract int onLayout();

    protected abstract BasePreantent initPreantent();

    protected abstract void onView(View view);

    protected abstract void onData();

}
