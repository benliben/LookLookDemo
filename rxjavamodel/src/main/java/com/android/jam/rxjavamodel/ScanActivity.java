package com.android.jam.rxjavamodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by Jam on 16/10/21 上午11:31.
 * Describe:
 */

public class ScanActivity extends AppCompatActivity {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button5)
    Button button5;

    public static void start(Context context) {
        Intent starter = new Intent(context, ScanActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                scan();
                break;
            case R.id.button5:
                break;
        }
    }

    private void scan() {
        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Observable.from(ints)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        Logger.d("integer:" + integer);
                        Logger.d("integer2:" + integer2);
                        Logger.d("integerScan:" + (integer + integer2));
                        return integer + integer2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.d("integerAction:" + integer);
            }
        });

    }
}
