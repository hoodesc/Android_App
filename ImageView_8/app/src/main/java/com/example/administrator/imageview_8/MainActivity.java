package com.example.administrator.imageview_8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnPartMH370;
    private ImageView ivParyMH370;
    private int[] images = {R.drawable.a,
                            R.drawable.b,
                            R.drawable.c};

    private int imageIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivParyMH370 = (ImageView) findViewById(R.id.ivParyMH370);
        btnPartMH370 = (Button) findViewById(R.id.btnParyMH370);

        btnPartMH370.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageIndex > 2){
                    imageIndex = 0;
               }
                ivParyMH370.setImageResource(images[imageIndex]);
                imageIndex++;
            }
        });
    }
}
