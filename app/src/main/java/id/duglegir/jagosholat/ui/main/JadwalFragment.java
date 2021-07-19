package id.duglegir.jagosholat.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.duglegir.jagosholat.util.FunctionHelper;
import id.duglegir.jagosholat.util.JadwalHelper;
import id.duglegir.jagosholat.R;


public class JadwalFragment extends Fragment {

    // Deklarasi Class Helper ----------------------------------------------------------------------
    private JadwalHelper jadwalHelper = new JadwalHelper();
    private FunctionHelper functionHelper = new FunctionHelper();
    // ---------------------------------------------------------------------------------------------

    // Deklarasi Requirement Variable --------------------------------------------------------------
    private int countTime;
    // ---------------------------------------------------------------------------------------------

    // Deklarasi Element Layout XML ----------------------------------------------------------------
    private TextView txt_coundown, txt_shalat, txt_waktu_shubuh, txt_waktu_dzuhur, txt_waktu_ashar, txt_waktu_maghrib, txt_waktu_isya;
    // ---------------------------------------------------------------------------------------------

    public JadwalFragment() {
        // Required empty public constructor
    }

    public void CekJadwal(){
        if (jadwalHelper.getMJadwalShalat().equals("Shalat Shubuh")){
            countTime = (jadwalHelper.getJmlWaktuDzuhur() - functionHelper.getSumWaktuDetik()) * functionHelper.getDetikKeMiliDetik();
            txt_shalat.setText("Dzuhur");
        } else if (jadwalHelper.getMJadwalShalat().equals("Shalat Dzuhur")){
            countTime = (jadwalHelper.getJmlWaktuAshar() - functionHelper.getSumWaktuDetik()) * functionHelper.getDetikKeMiliDetik();
            txt_shalat.setText("Ashar");
        } else if (jadwalHelper.getMJadwalShalat().equals("Shalat Ashar")){
            countTime = (jadwalHelper.getJmlWaktuMaghrib() - functionHelper.getSumWaktuDetik()) * functionHelper.getDetikKeMiliDetik();
            txt_shalat.setText("Maghrib");
        } else if (jadwalHelper.getMJadwalShalat().equals("Shalat Maghrib")){
            countTime = (jadwalHelper.getJmlWaktuIsya() - functionHelper.getSumWaktuDetik()) * functionHelper.getDetikKeMiliDetik();
            txt_shalat.setText("Isya");
        } else if (jadwalHelper.getMJadwalShalat().equals("Shalat Isya")){
            countTime = (jadwalHelper.getJmlWaktuShubuh() - functionHelper.getSumWaktuDetik()) * functionHelper.getDetikKeMiliDetik();
            txt_shalat.setText("Shubuh");
        } else {
            countTime = (jadwalHelper.getJmlWaktuDzuhur() - functionHelper.getSumWaktuDetik()) * functionHelper.getDetikKeMiliDetik();
            txt_shalat.setText("Dzuhur");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_jadwal, container, false);

        // -----------------------------------------------------------------------------------------
        // Deklarasi Elemen XML
        txt_coundown = (TextView)rootView.findViewById(R.id.countDown);
        txt_shalat = (TextView)rootView.findViewById(R.id.txt_view_sholat);
        txt_waktu_shubuh = (TextView)rootView.findViewById(R.id.txt_waktu_shubuh);
        txt_waktu_dzuhur = (TextView)rootView.findViewById(R.id.txt_waktu_dzuhur);
        txt_waktu_ashar = (TextView)rootView.findViewById(R.id.txt_waktu_ashar);
        txt_waktu_maghrib = (TextView)rootView.findViewById(R.id.txt_waktu_maghrib);
        txt_waktu_isya = (TextView)rootView.findViewById(R.id.txt_waktu_isya);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        CekJadwal();
        jadwalHelper.setTimeOnline(txt_waktu_shubuh, txt_waktu_dzuhur, txt_waktu_ashar, txt_waktu_maghrib, txt_waktu_isya);
        jadwalHelper.CoundownTime(countTime, txt_coundown);
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

}
