package com.mestre.ana.sessio3;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mestre.ana.sessio3.DB.UserData;

import org.w3c.dom.Text;

import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Ana on 31/01/2017.
 */

public class Memory extends Fragment implements View.OnClickListener{

    View v;
    TextView points;
    ArrayList<Integer> cards_ids;
    ArrayList<Integer> new_card;    // New distribution of cards.
    Random rand;
    ImageView b00, b01, b02, b03; // 0, 1, 2, 3
    ImageView b10, b11, b12, b13; // 4, 5, 6, 7
    ImageView b20, b21, b22, b23; // 8, 9, 10, 11
    ImageView b30, b31, b32, b33; // 12, 13, 14, 15

    public int tried;
    public ImageView currentCard;
    public int numCards;
    public int currentId;
    public Map<Integer, Boolean> foundIds;
    public Boolean flip;
    public int found;
    public String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.memory_layout, container, false);
        getActivity().setTitle("Memory");

        iniViews();
        createSecretGrid();
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu){
        MenuItem restart = menu.findItem(R.id.restart);
        restart.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.restart:
                iniViews();
                createSecretGrid();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSecretGrid(){
        int numberCards = 4*4/2;
        new_card = new ArrayList<>(16);

        ArrayList<Integer> index = new ArrayList<>(16);
        for(int i = 0; i < 16; i++) {
            index.add(i);
            new_card.add(0);
        }

        for(int i = 0; i < numberCards; i++) {
            int id = cards_ids.get(i);

            int ran = rand.nextInt(index.size());
            int num = index.get(ran);
            new_card.set(num, id);
            index.remove(ran);

            ran = rand.nextInt(index.size());
            num = index.get(ran);
            new_card.set(num, id);
            index.remove(ran);
        }
    }

    private void iniViews(){
        b00 = (ImageView) v.findViewById(R.id.button_00);
        b01 = (ImageView) v.findViewById(R.id.button_01);
        b02 = (ImageView) v.findViewById(R.id.button_02);
        b03 = (ImageView) v.findViewById(R.id.button_03);

        b10 = (ImageView) v.findViewById(R.id.button_10);
        b11 = (ImageView) v.findViewById(R.id.button_11);
        b12 = (ImageView) v.findViewById(R.id.button_12);
        b13 = (ImageView) v.findViewById(R.id.button_13);

        b20 = (ImageView) v.findViewById(R.id.button_20);
        b21 = (ImageView) v.findViewById(R.id.button_21);
        b22 = (ImageView) v.findViewById(R.id.button_22);
        b23 = (ImageView) v.findViewById(R.id.button_23);

        b30 = (ImageView) v.findViewById(R.id.button_30);
        b31 = (ImageView) v.findViewById(R.id.button_31);
        b32 = (ImageView) v.findViewById(R.id.button_32);
        b33 = (ImageView) v.findViewById(R.id.button_33);

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

        b00.setImageResource(R.drawable.mouths);
        b01.setImageResource(R.drawable.mouths);
        b02.setImageResource(R.drawable.mouths);
        b03.setImageResource(R.drawable.mouths);

        b10.setImageResource(R.drawable.mouths);
        b11.setImageResource(R.drawable.mouths);
        b12.setImageResource(R.drawable.mouths);
        b13.setImageResource(R.drawable.mouths);

        b20.setImageResource(R.drawable.mouths);
        b21.setImageResource(R.drawable.mouths);
        b22.setImageResource(R.drawable.mouths);
        b23.setImageResource(R.drawable.mouths);

        b30.setImageResource(R.drawable.mouths);
        b31.setImageResource(R.drawable.mouths);
        b32.setImageResource(R.drawable.mouths);
        b33.setImageResource(R.drawable.mouths);

        points = (TextView) v.findViewById(R.id.tried);
        points.setText("");

        rand = new Random();
        int numberIds = 8;
        foundIds = new HashMap<>();
        cards_ids = new ArrayList<>(numberIds);

        int id;
        id = R.drawable.memory_1;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_2;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_3;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_4;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_5;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_6;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_7;
        cards_ids.add(id);
        foundIds.put(id, false);

        id = R.drawable.memory_8;
        cards_ids.add(id);
        foundIds.put(id, false);

        tried = 0;
        numCards = 0;
        currentId = 0;
        flip = true;
        found = 0;

        username = ((BaseActivity) getActivity()).getCurrentUser().getUsername();

    }



    private void refresh(final ImageView image, int id){
        if(flip) {
            image.setImageResource(id);
            if (numCards == 0) {
                currentCard = image;
                currentId = id;
                numCards++;
                currentCard.setOnClickListener(null);
            } else if (numCards == 1) {
                tried++;
                points.setText(Integer.toString(tried));

                if (currentCard != image && id != currentId) {
                    flip = false;
                    android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentCard.setImageResource(R.drawable.mouths);
                            image.setImageResource(R.drawable.mouths);
                            flip = true;
                        }
                    }, 500);

                    currentCard.setOnClickListener(this);

                } else {
                    foundIds.put(id, true);
                    image.setOnClickListener(null);
                    currentCard.setOnClickListener(null);
                    found++;
                }
                numCards = 0;
            }
        }
        int numberImages = 8;
        if (found == numberImages) {
            // Guardar a la base de dades
            UserData db = new UserData(getActivity());
            db.open();
            int old_points = db.getUser(username).getPoints();
            if(tried < old_points || old_points == 0) db.updatePointsUser(tried, username);
            db.close();
        }
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        int id_image;
        switch(id){
            case R.id.button_00:
                id_image = new_card.get(0);
                refresh(b00, id_image);
                break;
            case R.id.button_01:
                id_image = new_card.get(1);
                refresh(b01, id_image);
                break;
            case R.id.button_02:
                id_image = new_card.get(2);
                refresh(b02, id_image);
                break;
            case R.id.button_03:
                id_image = new_card.get(3);
                refresh(b03, id_image);
                break;
            case R.id.button_10:
                id_image = new_card.get(4);
                refresh(b10, id_image);
                break;
            case R.id.button_11:
                id_image = new_card.get(5);
                refresh(b11, id_image);
                break;
            case R.id.button_12:
                id_image = new_card.get(6);
                refresh(b12, id_image);
                break;
            case R.id.button_13:
                id_image = new_card.get(7);
                refresh(b13, id_image);
                break;
            case R.id.button_20:
                id_image = new_card.get(8);;
                refresh(b20, id_image);
                break;
            case R.id.button_21:
                id_image = new_card.get(9);
                refresh(b21, id_image);
                break;
            case R.id.button_22:
                id_image = new_card.get(10);
                refresh(b22, id_image);
                break;
            case R.id.button_23:
                id_image = new_card.get(11);
                refresh(b23, id_image);
                break;
            case R.id.button_30:
                id_image = new_card.get(12);
                refresh(b30, id_image);
                break;
            case R.id.button_31:
                id_image = new_card.get(13);
                refresh(b31, id_image);
                break;
            case R.id.button_32:
                id_image = new_card.get(14);
                refresh(b32, id_image);
                break;
            case R.id.button_33:
                id_image = new_card.get(15);
                refresh(b33, id_image);
                break;
        }
    }
}
