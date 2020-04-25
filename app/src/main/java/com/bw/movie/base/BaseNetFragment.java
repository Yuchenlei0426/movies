package com.bw.movie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.contract.IContract;
import com.bw.movie.preantent.CPreantent;

public abstract class BaseNetFragment extends Fragment implements IContract.IView {
    public CPreantent mCPreantent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(onLayout(), container, false);
        mCPreantent = (CPreantent) initPreantent();
        if (mCPreantent != null) {
            mCPreantent.onAcctachView(this);
        }
        onView(view);
        onData();
        return view;
    }

    protected abstract int onLayout();

    protected abstract BasePreantent initPreantent();

    protected abstract void onView(View view);

    protected abstract void onData();
}
