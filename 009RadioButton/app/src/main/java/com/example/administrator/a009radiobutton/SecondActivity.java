package com.example.administrator.a009radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-08-10.
 */

public class SecondActivity extends Activity{
    private RatingBar rtbMyScore;
    private SeekBar skbChinaScore;
    private TextView tvChinaScore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rtbMyScore = (RatingBar) findViewById(R.id.rtbMyScore);
        skbChinaScore = (SeekBar) findViewById(R.id.skbChinaScore);
        tvChinaScore = (TextView) findViewById(R.id.tvChinaScore);
        rtbMyScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(SecondActivity.this,"你给你自己的评分是"+v,Toast.LENGTH_SHORT).show();
            }
        });
        skbChinaScore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //改变函数
                tvChinaScore.setText("中华名族复兴进度是："+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //拖拽
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //拖拽完
            }
        });

    }
}
