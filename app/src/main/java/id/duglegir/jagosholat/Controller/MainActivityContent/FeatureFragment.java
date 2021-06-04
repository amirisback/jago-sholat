package id.duglegir.jagosholat.Controller.MainActivityContent;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.duglegir.jagosholat.Controller.ViewPagerAdapter.FeaturePagerAdapter;
import id.duglegir.jagosholat.R;

public class FeatureFragment extends Fragment {

    public FeatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feature, container, false);

        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TabLayout tabLayout = rootView.findViewById(R.id.tablayout_feature);
        ViewPager viewPager = rootView.findViewById(R.id.viewpager_feature);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        FeaturePagerAdapter featurePagerAdapter = new FeaturePagerAdapter(getActivity(),getFragmentManager());
        viewPager.setAdapter(featurePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

}

