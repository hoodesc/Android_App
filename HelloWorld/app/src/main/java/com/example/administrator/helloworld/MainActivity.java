package com.example.administrator.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int money;
    private Button btnGetMoney;
    private Button btnLostMoney;
    private TextView tvGetMoney;
    private EditText edGoalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetMoney = (Button) findViewById(R.id.btnGetMoney);
        tvGetMoney = (TextView) findViewById(R.id.tvGetMoney);
        btnLostMoney = (Button) findViewById(R.id.btnLostMoney);
        edGoalMoney = (EditText) findViewById(R.id.edGoalMoney);

        btnGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strInputMoney = edGoalMoney.getText().toString().trim();
                int iMoney = Integer.parseInt(strInputMoney);
                if(iMoney == money){
                    Toast.makeText(MainActivity.this,"你已赚到目标金额了",Toast.LENGTH_SHORT).show();
                }else {
                    money++;
                    tvGetMoney.setText("哈哈，我通过点击按钮赚了"+money+"元");
                }
            }
        });

        btnLostMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(money == 0){
                    Toast.makeText(MainActivity.this,"没钱了",Toast.LENGTH_SHORT).show();
                }else{
                    money--;
                    tvGetMoney.setText("哈哈，我通过点击按钮赚了"+money+"元");
                }
            }
        });

    }
}

