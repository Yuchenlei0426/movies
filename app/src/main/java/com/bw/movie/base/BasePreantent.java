package com.bw.movie.base;

import com.bw.movie.contract.IContract;

import java.lang.ref.WeakReference;

public  abstract class BasePreantent  {

    private WeakReference<IContract.IView> mIViewWeakReference;

    public BasePreantent(){
        initModule();
    }

    protected abstract void initModule();
    public void onAcctachView(IContract.IView iView){
        mIViewWeakReference = new WeakReference<>(iView);
    }
    public void onDcttchView(){
        mIViewWeakReference.clear();
    }
    public IContract.IView getView(){
        return mIViewWeakReference.get();
    }
}
