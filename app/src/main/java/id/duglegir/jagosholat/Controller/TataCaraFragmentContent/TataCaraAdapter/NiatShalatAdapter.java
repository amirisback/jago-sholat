package id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.duglegir.jagosholat.R;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraObject.NiatShalat;

/**
 * Created by Bryan on 3/3/2018.
 */

public class NiatShalatAdapter extends ArrayAdapter<NiatShalat> {

    public NiatShalatAdapter(Context context, ArrayList<NiatShalat> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.content_tatacara_text_niat, parent, false);
        }


        NiatShalat niat = getItem(position);
        // -----------------------------------------------------------------------------------------
        TextView txt_rakaat = (TextView)listItemView.findViewById(R.id.text_rakaat);
        TextView txt_shalat = (TextView)listItemView.findViewById(R.id.text_shalat);
        TextView txt_arab = (TextView)listItemView.findViewById(R.id.text_arab);
        TextView txt_latin = (TextView)listItemView.findViewById(R.id.text_latin_arab);
        TextView txt_terjemah = (TextView)listItemView.findViewById(R.id.text_artinya);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        txt_rakaat.setText(niat.getRakaat());
        txt_shalat.setText(niat.getShalat());
        txt_arab.setText(niat.getArab());
        txt_latin.setText(niat.getLatin());
        txt_terjemah.setText(niat.getTerjemah());
        // -----------------------------------------------------------------------------------------

        return listItemView;
    }
}
