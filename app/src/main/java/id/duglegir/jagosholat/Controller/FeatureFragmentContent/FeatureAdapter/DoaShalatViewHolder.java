package id.duglegir.jagosholat.Controller.FeatureFragmentContent.FeatureAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.duglegir.jagosholat.Controller.FeatureFragmentContent.FeatureObject.DoaShalat;
import id.duglegir.jagosholat.R;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 12/09/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DoaShalatViewHolder extends RecyclerView.Adapter<DoaShalatViewHolder.ViewHolder> {

    public Context context;
    public ArrayList<DoaShalat> mArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_arab_doa, txt_latin_doa, txt_terjemah_doa;

        public ViewHolder(View itemView) {
            super(itemView);

            // -------------------------------------------------------------------------------------
            txt_arab_doa = (TextView)itemView.findViewById(R.id.arabDoaSetelah);
            txt_latin_doa = (TextView)itemView.findViewById(R.id.latinDoaSetelah);
            txt_terjemah_doa = (TextView)itemView.findViewById(R.id.terjemahDoaSetelah);
            // -------------------------------------------------------------------------------------
        }
    }

    public DoaShalatViewHolder(Context context, ArrayList<DoaShalat> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_feature_doa, parent, false);
        DoaShalatViewHolder.ViewHolder ViewHolder = new DoaShalatViewHolder.ViewHolder(v);

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // -----------------------------------------------------------------------------------------
        holder.txt_arab_doa.setText(mArrayList.get(position).getArabDoa());
        holder.txt_latin_doa.setText(mArrayList.get(position).getLatinDoa());
        holder.txt_terjemah_doa.setText(mArrayList.get(position).getTerjemahDoa());
        // -----------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}