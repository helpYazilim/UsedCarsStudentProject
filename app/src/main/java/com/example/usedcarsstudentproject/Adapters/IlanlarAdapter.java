package com.example.usedcarsstudentproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usedcarsstudentproject.Models.IlanlarModel;
import com.example.usedcarsstudentproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IlanlarAdapter extends BaseAdapter {

    List<IlanlarModel> ilanlarModelsList;
    Context context;

    public IlanlarAdapter(List<IlanlarModel> ilanlarModels, Context context) {
        this.ilanlarModelsList = ilanlarModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ilanlarModelsList.size();
    }

    @Override
    public Object getItem(int position) {
        return ilanlarModelsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ilanlar_layout,parent,false);
        TextView baslik,fiyat,adres;
        ImageView resim;
        baslik = convertView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat = convertView.findViewById(R.id.ilanlarIlanFİyat);
        adres = convertView.findViewById(R.id.ilanlarIlanAdres);
        resim = convertView.findViewById(R.id.ilanlarIlanResim);

        baslik.setText(ilanlarModelsList.get(position).getBaslik());
        fiyat.setText(ilanlarModelsList.get(position).getFiyat());
        adres.setText(ilanlarModelsList.get(position).getIl() + " " + ilanlarModelsList.get(position).getIlce() + " " + ilanlarModelsList.get(position).getMahalle());

        Picasso.with(context).load("https://halitpractice.com/BuySellAraba/" + ilanlarModelsList.get(position).getResim()).resize(300, 300).into(resim);

        return convertView;
    }
}
