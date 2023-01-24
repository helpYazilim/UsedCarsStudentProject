package com.example.usedcarsstudentproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.usedcarsstudentproject.IlanDetayActivity;
import com.example.usedcarsstudentproject.Models.FavoriSliderModel;
import com.example.usedcarsstudentproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriSliderAdapter extends PagerAdapter {

    List<FavoriSliderModel> list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public FavoriSliderAdapter(List<FavoriSliderModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);
        ImageView imageView = view.findViewById(R.id.sliderImageView);

        Picasso.with(context).load("https://halitpractice.com/BuySellAraba/" + list.get(position).getResimyolu()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getIlanid()!=null) // Favori resmi bos oldugunda yada , favori resmi olmadiginda , tiklanmayi engelleyen if condition
                {  // Favori resmi bos oldugunda yada , favori resmi olmadiginda , tiklanmayi engelleyen if condition

                    Intent ıntent = new Intent(activity, IlanDetayActivity.class);
                    ıntent.putExtra("ilanid", list.get(position).getIlanid().toString());
                    activity.startActivity(ıntent);

                } // Favori resmi bos oldugunda yada , favori resmi olmadiginda , tiklanmayi engelleyen if condition

            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);
    }

}
