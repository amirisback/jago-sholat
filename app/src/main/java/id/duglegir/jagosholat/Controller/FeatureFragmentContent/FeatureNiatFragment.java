package id.duglegir.jagosholat.Controller.FeatureFragmentContent;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import id.duglegir.jagosholat.Controller.ClassHelper.JSONHelper;
import id.duglegir.jagosholat.Controller.FeatureFragmentContent.FeatureAdapter.NiatShalatAdapter;
import id.duglegir.jagosholat.Controller.FeatureFragmentContent.FeatureObject.NiatShalat;
import id.duglegir.jagosholat.R;


public class FeatureNiatFragment extends Fragment {

    public FeatureNiatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feature_tutor_text, container, false);

        // -----------------------------------------------------------------------------------------
        ArrayList<NiatShalat> arrayWords = JSONHelper.extractNiatShalat();
              // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        ListView mListView = rootView.findViewById(R.id.listViewFeature);
        NiatShalatAdapter call = new NiatShalatAdapter(getActivity(), arrayWords);
        mListView.setAdapter(call);
        // -----------------------------------------------------------------------------------------

        return rootView;
    }


}
