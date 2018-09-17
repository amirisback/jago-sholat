package id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.duglegir.jagosholat.R;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraObject.DoaShalat;

/**
 * Created by Bryan on 3/12/2018.
 */

public class DoaShalatAdapter extends ArrayAdapter<DoaShalat> {

    public DoaShalatAdapter(Context context, ArrayList<DoaShalat> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.content_tatacara_text_doa, parent, false);
        }

        DoaShalat doaShalat = getItem(position);

        // -----------------------------------------------------------------------------------------
        TextView txt_arab_doa = (TextView)listItemView.findViewById(R.id.arabDoaSetelah);
        TextView txt_latin_doa = (TextView)listItemView.findViewById(R.id.latinDoaSetelah);
        TextView txt_terjemah_doa = (TextView)listItemView.findViewById(R.id.terjemahDoaSetelah);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        txt_arab_doa.setText(doaShalat.getArabDoa());
        txt_latin_doa.setText(doaShalat.getLatinDoa());
        txt_terjemah_doa.setText(doaShalat.getTerjemahDoa());
        // -----------------------------------------------------------------------------------------

        return listItemView;
    }
}
