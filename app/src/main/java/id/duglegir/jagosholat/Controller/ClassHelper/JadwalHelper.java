package id.duglegir.jagosholat.Controller.ClassHelper;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import id.duglegir.jagosholat.R;

/**
 * Created by Faisal Amir on 18/03/2018.
 */

public class JadwalHelper {

    // Deklarasi Method Helper ---------------------------------------------------------------------
    private FunctionHelper functionHelper = new FunctionHelper();
    private WaktuShalatHelper prayers = new WaktuShalatHelper();
    private Date now = new Date();
    private Calendar cal = Calendar.getInstance();
    // ---------------------------------------------------------------------------------------------

    // Deklarasi Requirement Kebutuhan -------------------------------------------------------------
    private int jmlWaktuShubuh, jmlWaktuTerbit, jmlWaktuTerbenam, jmlWaktuDzuhur,
            jmlWaktuAshar, jmlWaktuMaghrib, jmlWaktuIsya, jmlBeMidnight, jmlAftMidnight;
    private int waktuSaatIni;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Konstanta
    private final int JAM_KE_DETIK = 3600;
    private final int MENIT_KE_DETIK = 60;
    private static final String FORMAT_COUNTDOWN = "%02d : %02d : %02d";
    private final String BUKAN_WAKTU_SHOLAT = "Belum Masuk Waktu Sholat";
    private final String SHUBUH = "Shalat Shubuh";
    private final String DZUHUR = "Shalat Dzuhur";
    private final String ASHAR = "Shalat Ashar";
    private final String MAGHRIB = "Shalat Maghrib";
    private final String ISYA = "Shalat Isya";
    private final String MATAHARI_TERBIT = "Matahari Terbit";
    private final String MATAHARI_TERBENAM = "Matahari Terbenam";
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private double latitude = -6.1744;
    private double longitude = 106.8294;
    private double timezone = (Calendar.getInstance().getTimeZone().getOffset(Calendar.getInstance().getTimeInMillis())) / (1000 * 60 * 60);
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private int offsets [] = { 0, 0, 0, 0, 0, 0, 0 }; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
    private ArrayList prayerTimes, prayerNames;
    // ---------------------------------------------------------------------------------------------

    public JadwalHelper() {
        this.jmlBeMidnight = (23 * JAM_KE_DETIK) + (59 * MENIT_KE_DETIK); // 86.340
        this.jmlAftMidnight = (0 * JAM_KE_DETIK) + (0 * MENIT_KE_DETIK); // 0
        setJmlWaktu();
        functionHelper.getSystemRealTime();
        this.waktuSaatIni = functionHelper.getSumWaktuDetik();
    }
    // ---------------------------------------------------------------------------------------------
    public int getJmlWaktuShubuh() {
        return jmlWaktuShubuh;
    }

    public int getJmlWaktuTerbit() {
        return jmlWaktuTerbit;
    }

    public int getJmlWaktuDzuhur() {
        return jmlWaktuDzuhur;
    }

    public int getJmlWaktuAshar() {
        return jmlWaktuAshar;
    }

    public int getJmlWaktuMaghrib() {
        return jmlWaktuMaghrib;
    }

    public int getJmlWaktuIsya() {
        return jmlWaktuIsya;
    }

    public int getJmlBeMidnight() {
        return jmlBeMidnight;
    }

    public int getJmlAftMidnight() {
        return jmlAftMidnight;
    }

    public int getJmlWaktuTerbenam() {
        return jmlWaktuTerbenam;
    }


    // ---------------------------------------------------------------------------------------------


    public void setJadwalShalat(TextView txt){
        if ((waktuSaatIni == jmlAftMidnight) || (waktuSaatIni < jmlWaktuShubuh) ) {
            txt.setText(ISYA); // isya
        } else if ((waktuSaatIni == jmlWaktuShubuh) || (waktuSaatIni < jmlWaktuTerbit)) {
            txt.setText(SHUBUH); // shubuh
        } else if (waktuSaatIni == jmlWaktuTerbit || waktuSaatIni < jmlWaktuDzuhur) {
            txt.setText(BUKAN_WAKTU_SHOLAT); // bukan waktu shalat
            txt.setTextSize(20);
        } else if ((waktuSaatIni == jmlWaktuDzuhur) || (waktuSaatIni < jmlWaktuAshar)) {
            txt.setText(DZUHUR); // dzuhur
        } else if ((waktuSaatIni == jmlWaktuAshar) || (waktuSaatIni < jmlWaktuMaghrib)) {
            txt.setText(ASHAR); // ashar
        } else if ((waktuSaatIni == jmlWaktuMaghrib) || (waktuSaatIni < jmlWaktuIsya)) {
            txt.setText(MAGHRIB); // maghrib
        } else if ((waktuSaatIni == jmlWaktuIsya) || (waktuSaatIni <= jmlBeMidnight)) {
            txt.setText(ISYA); // isya
        }  else {
            txt.setText(BUKAN_WAKTU_SHOLAT);
            txt.setTextSize(20);
        }
    }

    public String getMJadwalShalat() {
        if ((waktuSaatIni == jmlAftMidnight) || (waktuSaatIni < jmlWaktuShubuh)){
            return ISYA; // isya
        }else if ((waktuSaatIni == jmlWaktuShubuh) || (waktuSaatIni < jmlWaktuTerbit)) {
            return SHUBUH; // shubuh
        }else if (waktuSaatIni == jmlWaktuTerbit || waktuSaatIni < jmlWaktuDzuhur) {
            return BUKAN_WAKTU_SHOLAT; // bukan waktu shalat
        } else if ((waktuSaatIni == jmlWaktuDzuhur) || (waktuSaatIni < jmlWaktuAshar)) {
            return DZUHUR; // dzuhur
        } else if ((waktuSaatIni == jmlWaktuAshar) || (waktuSaatIni < jmlWaktuMaghrib)) {
            return ASHAR; // ashar
        } else if ((waktuSaatIni == jmlWaktuMaghrib) || (waktuSaatIni < jmlWaktuIsya)) {
            return MAGHRIB; // maghrib
        } else if ((waktuSaatIni == jmlWaktuIsya) || (waktuSaatIni <= jmlBeMidnight)) {
            return ISYA; // isya
        }  else {
            return BUKAN_WAKTU_SHOLAT; // bukan waktu shalat
        }
    }


    // ---------------------------------------------------------------------------------------------
    public void CoundownTime(int waktu, final TextView mTextView){
        new CountDownTimer(waktu, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                mTextView.setText("- "+ String.format(FORMAT_COUNTDOWN,
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            public void onFinish() {
                mTextView.setText(R.string.jadwal_helper_saatnya_shalat);
            }

        }.start();
    }
    // ---------------------------------------------------------------------------------------------


    public void MethodWaktuShalatHelper(){
        // -----------------------------------------------------------------------------------------
        prayers.setTimeFormat(prayers.Time24);
        prayers.setCalcMethod(prayers.MWL);
        prayers.setAsrJuristic(prayers.Shafii);
        prayers.setAdjustHighLats(prayers.MidNight);
        prayers.tune(offsets);
        // -----------------------------------------------------------------------------------------
        cal.setTime(now);
        prayerTimes = prayers.getPrayerTimes(cal, latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();
        // -----------------------------------------------------------------------------------------

    }



    public void setJmlWaktu(){
        // -----------------------------------------------------------------------------------------
        MethodWaktuShalatHelper();
        // -----------------------------------------------------------------------------------------

        for (int i = 0; i < prayerTimes.size(); i++) {
            // -------------------------------------------------------------------------------------
            String tempWaktu = prayerTimes.get(i).toString();
            String hours = tempWaktu.substring(0, 2);
            String minutes = tempWaktu.substring(5, tempWaktu.length());
            // -------------------------------------------------------------------------------------

            // -------------------------------------------------------------------------------------
            int jam = Integer.parseInt(hours);
            int menit = Integer.parseInt(minutes);
            // -------------------------------------------------------------------------------------

            // -------------------------------------------------------------------------------------
            int total = (jam * JAM_KE_DETIK) + (menit * MENIT_KE_DETIK);
            // -------------------------------------------------------------------------------------

            // -------------------------------------------------------------------------------------
            if (prayerNames.get(i).equals(SHUBUH.substring(7))) {
                this.jmlWaktuShubuh = total;
            } else if (prayerNames.get(i).equals(DZUHUR.substring(7))){
                this.jmlWaktuDzuhur = total;
            } else if (prayerNames.get(i).equals(ASHAR.substring(7))) {
                this.jmlWaktuAshar = total;
            } else if (prayerNames.get(i).equals(MAGHRIB.substring(7))) {
                this.jmlWaktuMaghrib = total;
            } else if (prayerNames.get(i).equals(ISYA.substring(7))) {
                this.jmlWaktuIsya = total;
            }  else if (prayerNames.get(i).equals(MATAHARI_TERBIT)){
                this.jmlWaktuTerbit = total;
            } else if (prayerNames.get(i).equals(MATAHARI_TERBENAM)) {
                this.jmlWaktuTerbenam = total;
            }
            // -------------------------------------------------------------------------------------

        }

    }


    public void setTimeOnline(TextView txt_shubuh, TextView txt_dzuhur , TextView txt_ashar, TextView txt_maghrib, TextView txt_isya) {
        // -----------------------------------------------------------------------------------------
        MethodWaktuShalatHelper();
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Menset Waktu Sholat
        for (int i = 0; i < prayerTimes.size(); i++) {
            if (prayerNames.get(i).equals(SHUBUH.substring(7))) {
                txt_shubuh.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals(DZUHUR.substring(7))){
                txt_dzuhur.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals(ASHAR.substring(7))) {
                txt_ashar.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals(MAGHRIB.substring(7))) {
                txt_maghrib.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals(ISYA.substring(7))) {
                txt_isya.setText(prayerTimes.get(i).toString());
            }
        }
        // -----------------------------------------------------------------------------------------

    }


}
