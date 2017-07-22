package com.example.joe.ui.presenter;


import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.example.joe.api.BaseObserver;
import com.example.joe.api.RequestCallBack;
import com.example.joe.api.RxSchedulers;
import com.example.joe.bean.JsonBean;
import com.example.joe.bean.UserInfoBean;
import com.example.joe.ui.view.HomeView;
import com.example.joe.ui.base.BasePresenter;
import com.example.joe.utils.CommonUtils;
import com.example.joe.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页
 * Created by qiaobing on 2017/6/9.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private Context context;
    private HomeView homeView;

    private JsonBean jsonBean;

    public HomePresenter(Context context){
        this.context=context;
    }

    public void getHomeDataList(ArrayMap<String, String> params){
        homeView=getView();
        if (homeView!=null){
            homeService.getHomeDataListServer(CommonUtils.orgParams(params))
                    .compose(RxSchedulers.compose())
                    .subscribe(new BaseObserver<UserInfoBean>(new RequestCallBack<UserInfoBean>() {
                                   @Override
                                   public void onCompleted() {

                                   }

                                   @Override
                                   public void onFailure(String message) {

                                   }

                                   @Override
                                   public void onSuccess(UserInfoBean value) {
                                       homeView.getDataListSuccess();
                                   }
                               })

                    );
        }
    }
}
