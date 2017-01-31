package com.mestre.ana.sessio3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mestre.ana.sessio3.DB.User;
import com.mestre.ana.sessio3.DB.UserData;

import java.util.List;

/**
 * Created by Ana on 30/01/2017.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<User> items;
    private View.OnClickListener listener;
    private UserData userData;
    private Context context;

    @Override
    public RankingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_ranking, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView points;

        public ViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            points = (TextView) view.findViewById(R.id.points);
        }

        public void bindUser(User user) {
            username.setText(user.getUsername());
            points.setText(Integer.toString(user.getPoints()));
        }

    }

    public RankingAdapter(Context context, List<User> items){
        this.context = context;
        this.items = items;
        userData = new UserData(context);
        userData.open();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (items.get(position) != null) {
            holder.bindUser(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
