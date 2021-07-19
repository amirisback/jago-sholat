package id.duglegir.jagosholat.ui.tutorial;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import id.duglegir.jagosholat.util.JSONHelper;
import id.duglegir.jagosholat.model.DoaShalat;
import id.duglegir.jagosholat.R;

public class FeatureDoaFragment extends Fragment {

    public FeatureDoaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feature_tutor_text, container, false);

        // -----------------------------------------------------------------------------------------
        ArrayList<DoaShalat> arrayWords = JSONHelper.extractDoaShalat();
                // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        ListView mListView = rootView.findViewById(R.id.listViewFeature);
        DoaShalatAdapter call = new DoaShalatAdapter(getActivity(), arrayWords);
        mListView.setAdapter(call);
        // -----------------------------------------------------------------------------------------

        return rootView;
    }
}
