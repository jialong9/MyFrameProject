package com.vcyber.myframe.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by zjl on 2019/5/6
 * ---- presenter基类 ----
 */
public class BasePresenter<V> {
    protected V mvpView;
    private CompositeDisposable mCompositeDisposable;

    protected void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    void detachView() {
        this.mvpView = null;
        unDisposable();
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     * CompositeDisposable的clear()方法和dispose()方法类似，clear()可以多次被调用来丢弃容器中所有的Disposable，但dispose()被调用一次后就会失效。
     */
    private void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    protected void addDisposable(Observable observable, DisposableObserver disposableObserver) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        mCompositeDisposable.add(disposableObserver);
    }
}
