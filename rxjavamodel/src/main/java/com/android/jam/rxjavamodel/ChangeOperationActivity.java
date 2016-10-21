package com.android.jam.rxjavamodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Jam on 16/10/21 上午11:04.
 * Describe:
 */

public class ChangeOperationActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.textView)
    TextView textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ChangeOperationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_act);
        ButterKnife.bind(this);


        button1.setText("Map");

        button2.setText("flatMap");
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                map();
                break;
            case R.id.button2:
                flatMap();
                break;
            case R.id.button3:
                break;
        }
    }

    private void flatMap() {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("RxJava");


        Observable.from(list)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(s.toUpperCase() + "flatMap");
                    }
                }).subscribe(action1);
    }

    private void map() {
        Observable.just(360)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer + "块钱,map";
                    }
                }).subscribe(action1);
    }


    Action1<String> action1 = new Action1<String>() {
        @Override
        public void call(String s) {
            Logger.d(s);
        }
    };
}
