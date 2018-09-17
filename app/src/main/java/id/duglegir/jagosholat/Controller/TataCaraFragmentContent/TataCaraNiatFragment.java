package id.duglegir.jagosholat.Controller.TataCaraFragmentContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.duglegir.jagosholat.Controller.ClassHelper.JSONHelper;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraAdapter.NiatShalatViewHolder;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraObject.NiatShalat;
import id.duglegir.jagosholat.R;


public class TataCaraNiatFragment extends Fragment {

    public TataCaraNiatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tatacara_text, container, false);

        // -----------------------------------------------------------------------------------------
        ArrayList<NiatShalat> arrayWords = JSONHelper.extractNiatShalat();
              // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
//        ListView mListView = rootView.findViewById(R.id.listViewFeature);
//        NiatShalatAdapter call = new NiatShalatAdapter(getActivity(), arrayWords);
//        mListView.setAdapter(call);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.listViewFeature);

        NiatShalatViewHolder adapter = new NiatShalatViewHolder(getContext(), arrayWords);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);
        // -----------------------------------------------------------------------------------------

        return rootView;
    }


}
