package com.example.drivingtest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.drivingtest.models.Result;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Result> results;

    public ResultAdapter(Context context, int layout, ArrayList<Result> results) {
        this.context = context;
        this.layout = layout;
        this.results = results;
    }



    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.result_item, null);

        TextView txtRs = (TextView) convertView.findViewById(R.id.txtRsItem);
        TextView txtCount = (TextView) convertView.findViewById(R.id.txtCount);
        ConstraintLayout rsItem = (ConstraintLayout) convertView.findViewById(R.id.resultItem);

        Result result = results.get(position);
        if(result.getResult()>=17){
            rsItem.setBackgroundColor(Color.GREEN);
        }else{
            if(result.getResult()>=15){
                rsItem.setBackgroundColor(Color.YELLOW);
            }
            else{
                rsItem.setBackgroundColor(Color.RED);
            }
        }
        txtRs.setText(""+ result.getResult());
        txtCount.setText("Lần "+(position+1));

        return convertView;
    }
}
