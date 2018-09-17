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
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraAdapter.DoaShalatViewHolder;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraObject.DoaShalat;
import id.duglegir.jagosholat.R;

public class TataCaraDoaFragment extends Fragment {

    public TataCaraDoaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tatacara_text, container, false);

        // -----------------------------------------------------------------------------------------
        ArrayList<DoaShalat> arrayWords = JSONHelper.extractDoaShalat();
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
//        ListView mListView = rootView.findViewById(R.id.listViewFeature);
//        DoaShalatAdapter call = new DoaShalatAdapter(getActivity(), arrayWords);
//        mListView.setAdapter(call);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.listViewFeature);

        DoaShalatViewHolder adapter = new DoaShalatViewHolder(getContext(), arrayWords);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);

        // -----------------------------------------------------------------------------------------

        return rootView;
    }
}
