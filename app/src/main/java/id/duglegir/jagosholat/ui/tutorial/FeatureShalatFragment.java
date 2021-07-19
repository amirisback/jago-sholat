package id.duglegir.jagosholat.ui.tutorial;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

import id.duglegir.jagosholat.R;


public class FeatureShalatFragment extends Fragment {

    // ---------------------------------------------------------------------------------------------
    private ImageView next, previous;
    private String imageResId[] = {"sholat_0","sholat_1","sholat_2","sholat_3","sholat_4","sholat_5","sholat_6","sholat_7","sholat_8","sholat_9","sholat_10"};
    private int imageRes, i=0;
    private PhotoView imageContain;
    // ---------------------------------------------------------------------------------------------



    public FeatureShalatFragment() {
        // Required empty public constructor
    }

    public void setImage(int x){
        imageRes = getResources().getIdentifier(imageResId[x],"drawable", getActivity().getPackageName());
        imageContain.setImageResource(imageRes);
    }

    public void nextImage(){
        if (!(i == imageResId.length-1)) {
            i++;
            setImage(i);
        }
    }

    public void previousImage(){
        if (!(i == 0)) {
            i--;
            setImage(i);
            next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_feature_tutor_image, container, false);

        // -----------------------------------------------------------------------------------------
        imageContain = rootView.findViewById(R.id.photo_view);
        next = rootView.findViewById(R.id.btn_image_next);
        previous = rootView.findViewById(R.id.btn_image_previous);
        // -----------------------------------------------------------------------------------------

        imageContain.setImageResource(R.drawable.sholat_0);
        imageContain.setScaleType(ImageView.ScaleType.FIT_XY);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousImage();
            }
        });

        return rootView;
    }

}
