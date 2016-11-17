package adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hash.gossipgirl.R;
import com.example.hash.gossipgirl.activity.GameDetailActivity;

import java.util.ArrayList;
import java.util.List;

import model.GameModel;

/**
 * Created by HaSh on 11/17/2016.
 */

public class GameAdapter extends BaseAdapter {

    Context context;
    ViewHolder viewHolder;
    ArrayList<GameModel> data;

    public GameAdapter(Context context, ArrayList<GameModel> arrayList) {
        this.context = context;
        this.data = arrayList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final GameModel model = (GameModel) getItem(position);
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(
                    R.layout.list_view, null);
            viewHolder = new ViewHolder();
            initializeViews(viewHolder, v);

            v.setTag(viewHolder);
//            LayoutInflater vi;
//            vi = LayoutInflater.from(getContext());
//            v = vi.inflate(R.layout.list_view, null);
        }
        else {
            viewHolder = (ViewHolder) v.getTag();
        }


        if (model != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.name);
            TextView tt2 = (TextView) v.findViewById(R.id.rating);
            TextView tt3 = (TextView) v.findViewById(R.id.release);

            if (tt1 != null) {
                tt1.setText(model.getName());
            }

            if (tt2 != null) {
                tt2.setText(model.getRating());
            }

            if (tt3 != null) {
                tt3.setText(model.getRelease());
            }
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GameDetailActivity.class);
                i.putExtra("desc",model.getDesc());
                i.putExtra("name", model.getName());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        return v;
    }

    private void initializeViews(ViewHolder viewhold, View convertView) {
        viewhold.name = (TextView) convertView.findViewById(R.id.name);
        viewhold.rating = (TextView) convertView.findViewById(R.id.rating);
        viewhold.release = (TextView) convertView.findViewById(R.id.release);
    }

    static class ViewHolder {
        int position;

        TextView name, rating, release;
    }
}
