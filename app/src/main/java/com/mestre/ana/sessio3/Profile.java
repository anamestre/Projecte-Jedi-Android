package com.mestre.ana.sessio3;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mestre.ana.sessio3.DB.User;
import com.squareup.picasso.Picasso;

/**
 * Created by Ana on 27/01/2017.
 */

public class Profile extends Fragment {

    View v;
    ImageView profile_image;
    FloatingActionButton gallery;
    private String username;
    private User user;

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

        user = ((BaseActivity) getActivity()).getCurrentUser();

        //Toolbar toolbar = (Toolbar) v.findViewById(R.id.main_toolbar);
        //toolbar.setTitle(user.getUsername()); // Posar aqui el username.

        profile_image = (ImageView) v.findViewById(R.id.profile_picture);

        String image = user.getId_photo();

        if(image == null) profile_image.setImageResource(R.drawable.avatar);
        else {
            Uri uri = Uri.parse(image);
            if(Build.VERSION.SDK_INT >= 19)
                getActivity().getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Picasso.with(getActivity()).load(uri).resize(400, 400).into(profile_image);
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
            }
        });

    }

}
