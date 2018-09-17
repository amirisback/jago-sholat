package id.duglegir.jagosholat.Controller.TataCaraFragmentContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import id.duglegir.jagosholat.Controller.ViewPagerAdapter.ImagePagerAdapter;
import id.duglegir.jagosholat.R;


public class TataCaraShalatFragment extends Fragment {

//    // ---------------------------------------------------------------------------------------------
//    private ImageView next, previous;
//    private String imageResId[] = {"sholat_0","sholat_1","sholat_2","sholat_3","sholat_4","sholat_5","sholat_6","sholat_7","sholat_8","sholat_9","sholat_10"};
//    private int imageRes, i=0;
//    private PhotoView imageContain;
//    // ---------------------------------------------------------------------------------------------
//
//
//
//    public TataCaraShalatFragment() {
//        // Required empty public constructor
//    }
//
//    public void setImage(int x){
//        imageRes = getResources().getIdentifier(imageResId[x],"drawable", getActivity().getPackageName());
//        imageContain.setImageResource(imageRes);
//    }
//
//    public void nextImage(){
//        if (!(i == imageResId.length-1)) {
//            i++;
//            setImage(i);
//        }
//    }
//
//    public void previousImage(){
//        if (!(i == 0)) {
//            i--;
//            setImage(i);
//            next.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View rootView = inflater.inflate(R.layout.fragment_tatacara_image, container, false);
//
//        // -----------------------------------------------------------------------------------------
//        imageContain = rootView.findViewById(R.id.photo_view);
//        next = rootView.findViewById(R.id.btn_image_next);
//        previous = rootView.findViewById(R.id.btn_image_previous);
//        // -----------------------------------------------------------------------------------------
//
//        imageContain.setImageResource(R.drawable.sholat_0);
//        imageContain.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                nextImage();
//            }
//        });
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                previousImage();
//            }
//        });
//
//        return rootView;
//    }

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private Integer imageResId[] = {R.drawable.sholat_0, R.drawable.sholat_1,R.drawable.sholat_2,
            R.drawable.sholat_3, R.drawable.sholat_4, R.drawable.sholat_5, R.drawable.sholat_6,
            R.drawable.sholat_7, R.drawable.sholat_8, R.drawable.sholat_9, R.drawable.sholat_10};

    public TataCaraShalatFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_tatacara_image, container, false);


        viewPager = (ViewPager) rootView.findViewById(R.id.SliderImage);
        sliderDotspanel = (LinearLayout) rootView.findViewById(R.id.SliderDots);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getContext(), imageResId);
        viewPager.setAdapter(imagePagerAdapter);
        dotscount = imagePagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;

    }

}
