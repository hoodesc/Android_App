package com.example.administrator.a009radiobutton;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgCCTVSuvery;
    private CheckBox cbWangZhe,cbTaoBao,cbLoL;
    private Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgCCTVSuvery = (RadioGroup) findViewById(R.id.rgCCTVSuvery);
        cbWangZhe = (CheckBox) findViewById(R.id.cbWangZhe);
        cbTaoBao = (CheckBox) findViewById(R.id.cbTaoBao);
        cbLoL = (CheckBox) findViewById(R.id.cbLoL);
        btnSwitch = (Button) findViewById(R.id.btnSwitch);

        rgCCTVSuvery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rbSoHappy:
                        Toast.makeText(MainActivity.this,"SoHappy",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbNOHappy:
                        Toast.makeText(MainActivity.this,"NoHappy",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbZeng:
                        Toast.makeText(MainActivity.this,"我行曾",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        cbWangZhe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this,"打王者",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbTaoBao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Toast.makeText(MainActivity.this,"逛淘宝",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbLoL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this,"打英雄联盟",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到第二个Activity
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
