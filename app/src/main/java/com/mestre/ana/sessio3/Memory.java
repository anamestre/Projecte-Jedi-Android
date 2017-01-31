package com.mestre.ana.sessio3;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ana on 31/01/2017.
 */

public class Memory extends Fragment implements View.OnClickListener{

    View v;
    ArrayList<Integer> cards_ids;
    ArrayList<Integer> new_card;    // New distribution of cards.
    Random rand;

    ImageButton b00, b01, b02, b03; // 0, 1, 2, 3
    ImageButton b10, b11, b12, b13; // 4, 5, 6, 7
    ImageButton b20, b21, b22, b23; // 8, 9, 10, 11
    ImageButton b30, b31, b32, b33; // 12, 13, 14, 15

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.memory_layout, container, false);
        getActivity().setTitle("Memory");

        iniViews();
        createSecretGrid();

        return v;
    }

    private void createSecretGrid(){
        int numberCards = 4*4/2;
        new_card = new ArrayList<>(16);

        // Initialize the array.
        for(int i = 0; i < 16; i++) new_card.add(0);

        for(int i = 0; i < numberCards; i++){
            int j = 0;
            boolean found = false;
            while(!found){
                int x = rand.nextInt(15);
                if(new_card.get(x) == 0) {
                    int id = cards_ids.get(i);
                    new_card.set(x, id);
                    j++;
                    if (j == 2) found = true; // We need 2 cards for each image.
                }
            }
        }
    }

    private void iniViews(){
        b00 = (ImageButton) v.findViewById(R.id.button_00);
        b01 = (ImageButton) v.findViewById(R.id.button_01);
        b02 = (ImageButton) v.findViewById(R.id.button_02);
        b03 = (ImageButton) v.findViewById(R.id.button_03);

        b10 = (ImageButton) v.findViewById(R.id.button_10);
        b11 = (ImageButton) v.findViewById(R.id.button_11);
        b12 = (ImageButton) v.findViewById(R.id.button_12);
        b13 = (ImageButton) v.findViewById(R.id.button_13);

        b20 = (ImageButton) v.findViewById(R.id.button_20);
        b21 = (ImageButton) v.findViewById(R.id.button_21);
        b22 = (ImageButton) v.findViewById(R.id.button_22);
        b23 = (ImageButton) v.findViewById(R.id.button_23);

        b30 = (ImageButton) v.findViewById(R.id.button_30);
        b31 = (ImageButton) v.findViewById(R.id.button_31);
        b32 = (ImageButton) v.findViewById(R.id.button_32);
        b33 = (ImageButton) v.findViewById(R.id.button_33);

        b00.setOnClickListener(this);
        b01.setOnClickListener(this);
        b02.setOnClickListener(this);
        b03.setOnClickListener(this);

        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);

        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);

        b30.setOnClickListener(this);
        b31.setOnClickListener(this);
        b32.setOnClickListener(this);
        b33.setOnClickListener(this);

        rand = new Random();
        cards_ids = new ArrayList<>(8);
        int id;
        id = R.drawable.memory_1;
        cards_ids.add(id);
        id = R.drawable.memory_2;
        cards_ids.add(id);
        id = R.drawable.memory_3;
        cards_ids.add(id);
        id = R.drawable.memory_4;
        cards_ids.add(id);
        id = R.drawable.memory_5;
        cards_ids.add(id);
        id = R.drawable.memory_6;
        cards_ids.add(id);
        id = R.drawable.memory_7;
        cards_ids.add(id);
        id = R.drawable.memory_8;
        cards_ids.add(id);

    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch(id){
            case R.id.button_00:
                b00.setImageResource(new_card.get(0));
                break;
            case R.id.button_01:
                b01.setImageResource(new_card.get(1));
                break;
            case R.id.button_02:
                b02.setImageResource(new_card.get(2));
                break;
            case R.id.button_03:
                b03.setImageResource(new_card.get(3));
                break;
            case R.id.button_10:
                b10.setImageResource(new_card.get(4));
                break;
            case R.id.button_11:
                b11.setImageResource(new_card.get(5));
                break;
            case R.id.button_12:
                b12.setImageResource(new_card.get(6));
                break;
            case R.id.button_13:
                b13.setImageResource(new_card.get(7));
                break;
            case R.id.button_20:
                b20.setImageResource(new_card.get(8));
                break;
            case R.id.button_21:
                b21.setImageResource(new_card.get(9));
                break;
            case R.id.button_22:
                b22.setImageResource(new_card.get(10));
                break;
            case R.id.button_23:
                b23.setImageResource(new_card.get(11));
                break;
            case R.id.button_30:
                b30.setImageResource(new_card.get(12));
                break;
            case R.id.button_31:
                b31.setImageResource(new_card.get(13));
                break;
            case R.id.button_32:
                b32.setImageResource(new_card.get(14));
                break;
            case R.id.button_33:
                b33.setImageResource(new_card.get(15));
                break;
        }
    }
}
