package com.example.constraintlayout_yuxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//隐藏b
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        buttonA = (Button) findViewById(R.id.buttonA);

        buttonA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonA:
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                break;
        }
    }
}
