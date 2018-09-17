package id.duglegir.jagosholat.Controller.ViewPagerAdapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraDoaFragment;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraNiatFragment;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraShalatFragment;
import id.duglegir.jagosholat.Controller.TataCaraFragmentContent.TataCaraWudhuFragment;
import id.duglegir.jagosholat.R;

/**
 * Created by ikhsan ramadhan on 3/18/2018.
 */

public class TataCaraPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TataCaraPagerAdapter(Context context, FragmentManager fm ) {
        super(fm);
        mContext = context;
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new TataCaraWudhuFragment();
            case 1 :
                return new TataCaraNiatFragment();
            case 2 :
                return new TataCaraShalatFragment();
            case 3 :
                return new TataCaraDoaFragment();
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
