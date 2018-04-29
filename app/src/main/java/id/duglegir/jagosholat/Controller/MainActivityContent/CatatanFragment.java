package id.duglegir.jagosholat.Controller.MainActivityContent;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import id.duglegir.jagosholat.Controller.ClassHelper.FunctionHelper;
import id.duglegir.jagosholat.Controller.ClassHelper.JadwalHelper;
import id.duglegir.jagosholat.Model.DataOperation;
import id.duglegir.jagosholat.R;


public class CatatanFragment extends Fragment {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class Helper Buatan Sendiri
    private FunctionHelper functionHelper = new FunctionHelper();
    private JadwalHelper jadwalHelper = new JadwalHelper();
    private DataOperation crud = new DataOperation();
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Requirement Variable
    private String cekid;
    private final String bukanWaktuSholat = "Belum Masuk Waktu Sholat";
    private String[] mHadistArab = {"hadis_arab_0","hadis_arab_1","hadis_arab_2","hadis_arab_3","hadis_arab_4","hadis_arab_5"};
    private String[] mHadistText = {"hadis_text_0","hadis_text_1","hadis_text_2","hadis_text_3","hadis_text_4","hadis_text_5"};
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Deklarasi element layout
    private Button btn_simpan;
    private TextView txt_tanggal, txt_sholat, txt_hadist_arab, txt_hadist_text;
    private String isi_tanggal, isi_sholat, isi_waktu, isi_status;
    private String id_ibadah;
    // ---------------------------------------------------------------------------------------------

    public CatatanFragment() {
        // Required empty public constructor
    }

    // Cek di dalam table belum ada data sama sekali -----------------------------------------------
    public boolean isEmptyRowTable() {
        Cursor res = null;
        try {
            res = crud.getDataTanggalJenis(getContext(), isi_tanggal, isi_sholat);
            int cek = res.getCount();
            return cek == 0;
        } finally {
            res.close();
        }
        
    }
    // ---------------------------------------------------------------------------------------------

    // Method untuk mengecek apakah data sudah terisi / dengan cara search di database -------------
    public String cekDataSudahAda() {
        Cursor res = crud.getDataTanggalJenis(getContext(), isi_tanggal, isi_sholat);
        try{
            while (res.moveToNext()) {
                cekid = res.getString(2);
            }
        } finally {
            res.close();
        }
        return cekid;
    }
    // ---------------------------------------------------------------------------------------------

    // Method langsung isi dalam database ----------------------------------------------------------
    public void insertDataToDatabase() {
        boolean isInserted = crud.insertData(getContext(), id_ibadah, isi_tanggal, isi_sholat, isi_waktu, isi_status);
        if (isInserted) {
            Toast.makeText(getActivity(), "Alhamdullilah " + isi_sholat, Toast.LENGTH_LONG).show();
            btn_simpan.setVisibility(View.GONE);
        } else {
            Toast.makeText(getActivity(), "Data Not Inserted", Toast.LENGTH_LONG).show();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Method untuk menyimpan data ketika button "Simpan" di tekan ---------------------------------
    public void addData(String mShalat) {
        try {
            if (isEmptyRowTable()) {
                if (mShalat.equals(bukanWaktuSholat)) {
                    Toast.makeText(getActivity(), bukanWaktuSholat, Toast.LENGTH_LONG).show();
                } else {
                    insertDataToDatabase();
                }
            } else {
                if (cekDataSudahAda().equals(mShalat)) {
                    Toast.makeText(getActivity(), "Data Sudah Tercatat", Toast.LENGTH_LONG).show();
                } else if (mShalat.equals(bukanWaktuSholat)) {
                    Toast.makeText(getActivity(), bukanWaktuSholat, Toast.LENGTH_LONG).show();
                } else {
                    insertDataToDatabase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Mengatur Tombol
    public void tampilanButtonSimpan(String mShalat){
        if (mShalat.equalsIgnoreCase(bukanWaktuSholat)){
            btn_simpan.setVisibility(View.GONE);
        } else {
            if (isEmptyRowTable()){
                btn_simpan.setVisibility(View.VISIBLE);
            } else {
                if (!cekDataSudahAda().equals(mShalat)){
                    btn_simpan.setVisibility(View.VISIBLE);
                } else {
                    btn_simpan.setVisibility(View.GONE);
                }
            }

        }
    }
    // ---------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_catatan, container, false);

        // Inisiasi element XML layout -------------------------------------------------------------
        txt_tanggal = rootView.findViewById(R.id.txt_tanggal);
        txt_sholat = rootView.findViewById(R.id.txt_sholat);
        txt_hadist_arab = rootView.findViewById(R.id.txt_hadist_arab);
        txt_hadist_text = rootView.findViewById(R.id.txt_hadist_text);
        btn_simpan = rootView.findViewById(R.id.btn_simpan);
        // -----------------------------------------------------------------------------------------

        // Set tampilan tanggal dan waktu ----------------------------------------------------------
        functionHelper.getSystemTime();
        functionHelper.getSystemRealTime();
        functionHelper.getSumRealTime();
        jadwalHelper.setJadwalShalat(txt_sholat);
        txt_tanggal.setText(functionHelper.getDateToday());
        // -----------------------------------------------------------------------------------------

        // Get Data dari XML Layout ----------------------------------------------------------------
        isi_sholat = txt_sholat.getText().toString();
        isi_waktu = functionHelper.getOutputStringTime();
        isi_tanggal = functionHelper.getDateToday();
        isi_status = "Shalat";
        // -----------------------------------------------------------------------------------------

        // Set Data Random Hadist untuk XML Layout -------------------------------------------------
        Random randomInt = new Random();
        int maxRandom = mHadistArab.length - 1;
        int minRandom = 0;
        int getIndexArrayHadis = randomInt.nextInt(maxRandom - minRandom + 1) + minRandom;
        int mResIdHadistArab = getResources().getIdentifier(mHadistArab[getIndexArrayHadis],"string", getActivity().getPackageName());
        int mResIdHadistText = getResources().getIdentifier(mHadistText[getIndexArrayHadis],"string", getActivity().getPackageName());
        txt_hadist_arab.setText(mResIdHadistArab);
        txt_hadist_text.setText(mResIdHadistText);
        // -----------------------------------------------------------------------------------------

        // Panggil method untuk mencatat -----------------------------------------------------------
        id_ibadah = "IDS" + functionHelper.getRandomChar();

        tampilanButtonSimpan(isi_sholat);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(isi_sholat);
            }
        });
        // -----------------------------------------------------------------------------------------
        return rootView;
        }

}