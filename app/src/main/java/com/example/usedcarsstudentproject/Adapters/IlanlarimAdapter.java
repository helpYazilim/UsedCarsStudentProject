package com.example.usedcarsstudentproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usedcarsstudentproject.IlanDetayActivity;
import com.example.usedcarsstudentproject.Models.IlanlarimModel;
import com.example.usedcarsstudentproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarimAdapter extends BaseAdapter {
    List<IlanlarimModel> list;
    // Context'i neden aliyorduk? Layout tanimlamasi icin aliyoruz
    Context context;
    Activity activity;
    String uye_id, ilan_id;

    public IlanlarimAdapter(List<IlanlarimModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlarim_layout,parent,false);
        ImageView resim;
        TextView baslik, fiyat;



        resim = convertView.findViewById(R.id.ilanlarimIlanResim);
        baslik = convertView.findViewById(R.id.ilanlarimIlanBaslik);
        fiyat = convertView.findViewById(R.id.ilanlarimIlanFİyat);

        ilan_id = list.get(position).getIlanid();
        uye_id = list.get(position).getUyeid();


        resim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(activity, IlanDetayActivity.class);
                ıntent.putExtra("ilanid", list.get(position).getIlanid().toString());
                activity.startActivity(ıntent);
            }
        });

        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());

        Picasso.with(context).load("https://halitpractice.com/BuySellAraba/" + list.get(position).getResim()).resize(300, 300).into(resim);


        return convertView;
    }
}
