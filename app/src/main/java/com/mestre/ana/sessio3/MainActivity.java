package com.mestre.ana.sessio3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ana on 25/01/2017.
 */

public class MainActivity extends AppCompatActivity {

    public TextView t;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*t = (TextView) findViewById(R.id.text);

        Button boto = (Button) findViewById(R.id.boto);
        boto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                t.setText("hola");
            }
        });*/
    }
/*
    public void onClick(View v){
        switch (v.getId()){
            case R.id.num1:
        }


    }*/
/*
    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString("result", t.getText().toString());
        Log.v("result", t.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        t.setText(savedInstanceState.getString("result"));
        Log.v("retrieving",savedInstanceState.getString("result"));
    }*/
}
