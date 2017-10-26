package com.nevermind.recycleview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Dmitry on 22.10.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Place> mDataset;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // items
        public ImageView imageView;
        public TextView addressView;
        public TextView infoView;
        public TextView distanceView;
        public TextView rateView;



        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            addressView = (TextView) view.findViewById(R.id.addressView);
            infoView = (TextView) view.findViewById(R.id.infoView);
            distanceView = (TextView) view.findViewById(R.id.distanceView);
            rateView = (TextView) view.findViewById(R.id.rateV);
        }

    }

    // Конструктор
    public RecyclerAdapter(ArrayList<Place> dataset) {
        mDataset = new ArrayList<Place>(dataset);
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        final Place place = mDataset.get(position);
        final  int CHOOSE_THIEF = 0;

        holder.addressView.setText(mDataset.get(position).getAddress());
        holder.infoView.setText(mDataset.get(position).getInfo());
        holder.distanceView.setText(Long.toString(mDataset.get(position).getDistance())+" км");
        holder.rateView.setText("Оценка отделения: "+Integer.toString(mDataset.get(position).getRate()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent data = new Intent(context, DetailInfo.class);
                data.putExtra("data", place);
                data.putExtra("position", position);

                ((MainActivity) context).startActivityForResult(data,CHOOSE_THIEF);



            }
        });


    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
