package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.my.sadebuser.R;
import com.squareup.picasso.Picasso;

public class FullScreenActivity extends AppCompatActivity {

    ImageView iv_Back,imageViewMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        iv_Back=findViewById(R.id.iv_Back);
        imageViewMain=findViewById(R.id.imageViewMain);

        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(!getIntent().getStringExtra("image").equals("")){
            Picasso.get().load(getIntent().getStringExtra("image")).into(imageViewMain);
        }



    }
}