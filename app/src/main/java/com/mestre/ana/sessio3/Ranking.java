package com.mestre.ana.sessio3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mestre.ana.sessio3.DB.UserData;

/**
 * Created by Ana on 30/01/2017.
 */

public class Ranking extends Fragment {

    private View v;
    private RecyclerView rv;
    private RankingAdapter adapter;

    public Ranking(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v =  inflater.inflate(R.layout.ranking_layout, container, false);
        getActivity().setTitle("Users ranking");
        UserData usdata = new UserData(getActivity());
        usdata.createHardcodedUsers();

        rv = (RecyclerView) v.findViewById(R.id.ranking_rv);
        adapter = new RankingAdapter(getActivity(), usdata.getUsersOrderByRanking());
        rv.setAdapter(adapter);

        return v;
    }

}
