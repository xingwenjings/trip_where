package com.example.constraintlayout_yuxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        buttonZ = (Button) findViewById(R.id.buttonZ);

        buttonZ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonZ:
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                startActivity(intent);
                break;
        }
    }
}
