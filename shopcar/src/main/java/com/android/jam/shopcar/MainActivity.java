package com.android.jam.shopcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    interface ShopCartDataCallback {

        void onSuccess(List<GoodsBean> list);

        void onError(Throwable throwable);
    }

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private ShopCartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initView();

        /**
         * 模拟从服务器拿数据
         */
        getShopCartDataFromNet(mShopCartDataCallback);
    }

    private void initView() {
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new ShopCartAdapter(null, this);
        rvContent.setAdapter(cartAdapter);
    }


    private void getShopCartDataFromNet(ShopCartDataCallback shopCartDataCallback) {

        List<GoodsBean> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GoodsBean g = new GoodsBean();
            g.goodsName = "我是商品" + i;
            ls.add(g);
        }
        shopCartDataCallback.onSuccess(ls);
    }

    /**
     * 获取数据的回调
     */
    ShopCartDataCallback mShopCartDataCallback = new ShopCartDataCallback() {
        @Override
        public void onSuccess(List<GoodsBean> list) {
            Logger.d(list);
            cartAdapter.addGoods(list);
        }

        @Override
        public void onError(Throwable throwable) {

        }
    };


    @OnClick(R.id.button)
    public void onClick() {
        logAllSelectedGoods();
    }

    private void logAllSelectedGoods() {
        Observable.from(cartAdapter.getAllSelectedGoods())
                .subscribe(new Action1<GoodsBean>() {
                    @Override
                    public void call(GoodsBean goodsBean) {
                        Logger.d(goodsBean.goodsName);
                    }
                });

    }

}
