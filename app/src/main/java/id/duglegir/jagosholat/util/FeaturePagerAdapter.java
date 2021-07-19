package id.duglegir.jagosholat.util;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.duglegir.jagosholat.ui.tutorial.FeatureDoaFragment;
import id.duglegir.jagosholat.ui.tutorial.FeatureNiatFragment;
import id.duglegir.jagosholat.ui.tutorial.FeatureShalatFragment;
import id.duglegir.jagosholat.ui.tutorial.FeatureWudhuFragment;
import id.duglegir.jagosholat.R;

/**
 * Created by ikhsan ramadhan on 3/18/2018.
 */

public class FeaturePagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FeaturePagerAdapter(Context context, FragmentManager fm ) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new FeatureWudhuFragment();
            case 1 :
                return new FeatureNiatFragment();
            case 2 :
                return new FeatureShalatFragment();
            case 3 :
                return new FeatureDoaFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return mContext.getString(R.string.btn_tutor_wudhu);
            case 1 :
                return mContext.getString(R.string.btn_niat_sholat);
            case 2 :
                return mContext.getString(R.string.btn_tutor_sholat);
            case 3 :
                return mContext.getString(R.string.btn_doa);
            default:
                return null;
        }

    }
}
