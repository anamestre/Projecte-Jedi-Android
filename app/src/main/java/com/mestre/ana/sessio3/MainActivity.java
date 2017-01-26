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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView t;
    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;

    Button comma;
    Button parentesis;
    Button multiply;
    Button divide;
    Button plus;
    Button subs;
    Button equals;
    Button ac;
    Button del;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();

        /*t = (TextView) findViewById(R.id.text);

        Button boto = (Button) findViewById(R.id.boto);
        boto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                t.setText("hola");
            }
        });*/
    }

    public void iniViews(){
        num0 = (Button) findViewById(R.id.num0);
        num0.setOnClickListener(this);
        num1 = (Button) findViewById(R.id.num1);
        num1.setOnClickListener(this);
        num2 = (Button) findViewById(R.id.num2);
        num2.setOnClickListener(this);
        num3 = (Button) findViewById(R.id.num3);
        num3.setOnClickListener(this);
        num4 = (Button) findViewById(R.id.num4);
        num4.setOnClickListener(this);
        num5 = (Button) findViewById(R.id.num5);
        num5.setOnClickListener(this);
        num6 = (Button) findViewById(R.id.num6);
        num6.setOnClickListener(this);
        num7 = (Button) findViewById(R.id.num7);
        num7.setOnClickListener(this);
        num8 = (Button) findViewById(R.id.num8);
        num8.setOnClickListener(this);
        num9 = (Button) findViewById(R.id.num9);
        num9.setOnClickListener(this);
        comma = (Button) findViewById(R.id.comma);
        comma.setOnClickListener(this);
        parentesis = (Button) findViewById(R.id.parentesis);
        parentesis.setOnClickListener(this);
        multiply = (Button) findViewById(R.id.multiply);
        multiply.setOnClickListener(this);
        divide = (Button) findViewById(R.id.divide);
        divide.setOnClickListener(this);
        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(this);
        subs = (Button) findViewById(R.id.substract);
        subs.setOnClickListener(this);
        equals = (Button) findViewById(R.id.equals);
        equals.setOnClickListener(this);
        ac = (Button) findViewById(R.id.ac);
        ac.setOnClickListener(this);
        del = (Button) findViewById(R.id.delete);
        del.setOnClickListener(this);
        t = (TextView) findViewById(R.id.text);
    }

    @Override
    public void onClick(View v){
        String expr = (String) t.getText();
        switch (v.getId()){
            case R.id.num0:
                t.setText(expr.concat("0"));
                break;
            case R.id.num1:
                t.setText(expr.concat("1"));
                break;
            case R.id.num2:
                t.setText(expr.concat("2"));
                break;
            case R.id.num3:
                t.setText(expr.concat("3"));
                break;
            case R.id.num4:
                t.setText(expr.concat("4"));
                break;
            case R.id.num5:
                t.setText(expr.concat("5"));
                break;
            case R.id.num6:
                t.setText(expr.concat("6"));
                break;
            case R.id.num7:
                t.setText(expr.concat("7"));
                break;
            case R.id.num8:
                t.setText(expr.concat("8"));
                break;
            case R.id.num9:
                t.setText(expr.concat("9"));
                break;
            case R.id.comma:
                t.setText(expr.concat("."));
                break;
            case R.id.divide:
                String div = (String) getResources().getString(R.string.divide);
                t.setText(expr.concat(div));
                break;
            case R.id.multiply:
                String multi = (String) getResources().getString(R.string.multiply);
                t.setText(expr.concat(multi));
                break;
            case R.id.plus:
                t.setText(expr.concat("+"));
                break;
            case R.id.substract:
                t.setText(expr.concat("-"));
                break;
            case R.id.delete:
                if(t.length() > 0) t.setText(expr.substring(0, t.length() - 1));
                break;

        }


    }


    public void calcula(){

    }

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
