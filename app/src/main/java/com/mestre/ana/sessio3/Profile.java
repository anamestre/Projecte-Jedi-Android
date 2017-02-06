package com.mestre.ana.sessio3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mestre.ana.sessio3.DB.User;
import com.mestre.ana.sessio3.DB.UserData;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Ana on 27/01/2017.
 */

public class Profile extends Fragment {

    View v;
    ImageView profile_image;
    FloatingActionButton gallery;
    private String username;
    private User user;
    private TextView points;
    private TextView location;
    private int SELECT_IMAGE = 100;
    private UserData db;

    public Profile(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v =  inflater.inflate(R.layout.profilee_layout, container, false);
        getActivity().setTitle("Profile");
        iniViews();

        return v;
    }

    public void iniViews(){

        username = ((BaseActivity) getActivity()).getUsername();
        db = new UserData(getActivity());
        db.open();
        user = db.getUser(username);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.main_toolbar);
        toolbar.setTitle(username); // Posar aqui el username.

        points = (TextView) v.findViewById(R.id.user_score);
        if(user.getPoints() != 0) points.setText(Integer.toString(user.getPoints()));
        else points.setText("This user hasn't played memory");

        location = (TextView) v.findViewById(R.id.location);
        //location.setText("aqui la localitzacio");


        profile_image = (ImageView) v.findViewById(R.id.profile_picture);

        String image = user.getId_photo();
        if(image == null) profile_image.setImageResource(R.drawable.avatar);
        else {
            Uri uri = Uri.parse(image);
            if(Build.VERSION.SDK_INT >= 19)
                getActivity().getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Picasso.with(getActivity()).load(uri).resize(400, 400).centerCrop().into(profile_image);
        }

        gallery = (FloatingActionButton) v.findViewById(R.id.gallery_button);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(Build.VERSION.SDK_INT < 19) intent = new Intent(Intent.ACTION_GET_CONTENT);
                else intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_IMAGE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == SELECT_IMAGE) {
                if(resultCode == RESULT_OK){
                    Uri selectedImage = intent.getData();
                    Picasso.with(getActivity()).load(selectedImage).resize(400,400).centerCrop().into(profile_image);
                    //UserData db = new UserData(getActivity());
                    //db.open();
                    db.updateImage(selectedImage.toString(), username);
                    NavigationView nav = (NavigationView) getActivity().findViewById(R.id.nav_view);
                    ImageView nav_image = (ImageView) nav.getHeaderView(0).findViewById(R.id.profile_picture);
                    Picasso.with(getActivity()).load(selectedImage).resize(400,400).centerCrop().into(nav_image);
                    //db.close();
                }
        }
    }

}
