package com.mestre.ana.sessio3;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calculator extends Fragment implements View.OnClickListener {

    protected TextView t;
    protected TextView sol;
    protected View v;

    protected Button num0;
    protected Button num1;
    protected Button num2;
    protected Button num3;
    protected Button num4;
    protected Button num5;
    protected Button num6;
    protected Button num7;
    protected Button num8;
    protected Button num9;

    protected Button comma;
    protected Button lparen;
    protected Button rparen;
    protected Button multiply;
    protected Button divide;
    protected Button plus;
    protected Button subs;
    protected Button equals;
    protected Button ac;
    protected Button del;

    public boolean toasts;
    public boolean state;


    public Calculator() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v =  inflater.inflate(R.layout.activity_main, container, false);
        iniViews();
        getActivity().setTitle("Calculator");

        if (savedInstanceState != null){
            t.setText(savedInstanceState.getString("expr"));
        }
        setHasOptionsMenu(true);
        return v;
    }

    public void iniViews(){
        num0 = (Button) v.findViewById(R.id.num0);
        num0.setOnClickListener(this);
        num1 = (Button) v.findViewById(R.id.num1);
        num1.setOnClickListener(this);
        num2 = (Button) v.findViewById(R.id.num2);
        num2.setOnClickListener(this);
        num3 = (Button) v.findViewById(R.id.num3);
        num3.setOnClickListener(this);
        num4 = (Button) v.findViewById(R.id.num4);
        num4.setOnClickListener(this);
        num5 = (Button) v.findViewById(R.id.num5);
        num5.setOnClickListener(this);
        num6 = (Button) v.findViewById(R.id.num6);
        num6.setOnClickListener(this);
        num7 = (Button) v.findViewById(R.id.num7);
        num7.setOnClickListener(this);
        num8 = (Button) v.findViewById(R.id.num8);
        num8.setOnClickListener(this);
        num9 = (Button) v.findViewById(R.id.num9);
        num9.setOnClickListener(this);
        comma = (Button) v.findViewById(R.id.comma);
        comma.setOnClickListener(this);
        lparen = (Button) v.findViewById(R.id.lparen);
        lparen.setOnClickListener(this);
        rparen = (Button) v.findViewById(R.id.rparen);
        rparen.setOnClickListener(this);
        multiply = (Button) v.findViewById(R.id.multiply);
        multiply.setOnClickListener(this);
        divide = (Button) v.findViewById(R.id.divide);
        divide.setOnClickListener(this);
        plus = (Button) v.findViewById(R.id.plus);
        plus.setOnClickListener(this);
        subs = (Button) v.findViewById(R.id.substract);
        subs.setOnClickListener(this);
        equals = (Button) v.findViewById(R.id.equals);
        equals.setOnClickListener(this);
        ac = (Button) v.findViewById(R.id.ac);
        ac.setOnClickListener(this);
        del = (Button) v.findViewById(R.id.delete);
        del.setOnClickListener(this);

        t = (TextView) v.findViewById(R.id.text);
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
                t.setText(expr.concat("−"));
                break;
            case R.id.delete:
                if(t.length() > 0) t.setText(expr.substring(0, t.length() - 1));
                break;
            case R.id.ac:
                if(t.length() > 0) t.setText("");
                break;
            case R.id.lparen:
                t.setText(expr.concat("("));
                break;
            case R.id.rparen:
                t.setText(expr.concat(")"));
                break;
            case R.id.equals:
                Evalua ev = new Evalua();
                sol.setText(String.valueOf(ev.eval(t.toString())));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.phone_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String num = (String) t.getText();
                if(isPhoneNumber(num)){
                    intent.setData(Uri.parse("tel:"+num));
                    startActivity(intent);
                }
                else infoUser("Not a phone number");
                break;
            case R.id.action_toasts:
                toasts = !toasts;
                break;
            case R.id.action_notificacio:
                state = !state;
                break;
            case R.id.wolfram:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolframalpha.com/"));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void infoUser(String msg){
        if(toasts) Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isPhoneNumber(String num){
        if(num.length() != 9) return false;
        for(int i = 0; i < num.length(); i++){
            if (num.charAt(i) > '9' || num.charAt(i) < '0') return false;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString("expr", t.getText().toString());
    }
}
