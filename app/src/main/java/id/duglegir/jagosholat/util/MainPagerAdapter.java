package id.duglegir.jagosholat.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.duglegir.jagosholat.ui.main.JadwalFragment;
import id.duglegir.jagosholat.ui.main.CatatanFragment;
import id.duglegir.jagosholat.ui.tutorial.FeatureFragment;
import id.duglegir.jagosholat.ui.compass.KompasFragment;
import id.duglegir.jagosholat.ui.statistic.StatistikFragment;

/**
 * Created by Faisal Amir on 25/02/2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int NumberTab;

    public MainPagerAdapter(FragmentManager fm, int numberTab) {
        super(fm);
        NumberTab = numberTab;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                return new CatatanFragment();
            case 1 :
                return new JadwalFragment();
            case 2 :
                return new StatistikFragment();
            case 3 :
                return new FeatureFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NumberTab;
    }
}
