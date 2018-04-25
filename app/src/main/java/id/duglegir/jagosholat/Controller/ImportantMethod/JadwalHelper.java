package id.duglegir.jagosholat.Controller.ImportantMethod;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Faisal Amir on 18/03/2018.
 */

public class JadwalHelper {

    // Deklarasi Method Helper ---------------------------------------------------------------------
    private FunctionHelper functionHelper = new FunctionHelper();
    private WaktuShalat prayers = new WaktuShalat();
    private Date now = new Date();
    private Calendar cal = Calendar.getInstance();
    // ---------------------------------------------------------------------------------------------

    // Deklarasi Requirement Kebutuhan -------------------------------------------------------------
    private int jmlWaktuShubuh, jmlWaktuTerbit, jmlWaktuDzuhur, jmlWaktuAshar;
    private int jmlWaktuMaghrib, jmlWaktuIsya, jmlBeMidnight, jmlAftMidnight;
    private int realTime;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Konstanta
    private final int JAM_KE_DETIK = 3600;
    private final int MENIT_KE_DETIK = 60;
    private final String BUKAN_WAKTU_SHOLAT = "Belum Masuk Waktu Sholat";
    private static final String FORMAT_COUNTDOWN = "%02d : %02d : %02d";
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private double latitude = -6.1744;
    private double longitude = 106.8294;
    private double timezone = (Calendar.getInstance().getTimeZone().getOffset(Calendar.getInstance().getTimeInMillis())) / (1000 * 60 * 60);
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private String arrayTemp[] = {"Shalat Shubuh", "Shalat Dzuhur", "Shalat Ashar","Shalat Maghrib", "Shalat Isya", BUKAN_WAKTU_SHOLAT};
    private int offsets [] = { 0, 0, 0, 0, 0, 0, 0 }; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
    private ArrayList prayerTimes, prayerNames;
    // ---------------------------------------------------------------------------------------------

    public JadwalHelper() {
        this.jmlBeMidnight = (23 * JAM_KE_DETIK) + (59 * MENIT_KE_DETIK); // 86.340
        this.jmlAftMidnight = (0 * JAM_KE_DETIK) + (0 * MENIT_KE_DETIK);
        setJmlWaktu();
        functionHelper.getSystemRealTime();
        this.realTime = functionHelper.getSumWaktuDetik();
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
    // ---------------------------------------------------------------------------------------------


    public void setJadwalShalat(TextView txt){
        if ((realTime == jmlAftMidnight) || (realTime < jmlWaktuShubuh) ) {
            txt.setText(arrayTemp[4]); // isya
        } else if ((realTime == jmlWaktuShubuh) || (realTime < jmlWaktuTerbit)) {
            txt.setText(arrayTemp[0]); // shubuh
        } else if (realTime == jmlWaktuTerbit || realTime < jmlWaktuDzuhur) {
            txt.setText(arrayTemp[5]); // bukan waktu shalat
            txt.setTextSize(20);
        } else if ((realTime == jmlWaktuDzuhur) || (realTime < jmlWaktuAshar)) {
            txt.setText(arrayTemp[1]); // dzuhur
        } else if ((realTime == jmlWaktuAshar) || (realTime < jmlWaktuMaghrib)) {
            txt.setText(arrayTemp[2]); // ashar
        } else if ((realTime == jmlWaktuMaghrib) || (realTime < jmlWaktuIsya)) {
            txt.setText(arrayTemp[3]); // maghrib
        } else if ((realTime == jmlWaktuIsya) || (realTime <= jmlBeMidnight)) {
            txt.setText(arrayTemp[4]); // isya
        }  else {
            txt.setText(arrayTemp[5]);
            txt.setTextSize(20);
        }
    }

    public String getMJadwalShalat() {
        if ((realTime == jmlAftMidnight) || (realTime < jmlWaktuShubuh)){
            return arrayTemp[4]; // isya
        }else if ((realTime == jmlWaktuShubuh) || (realTime < jmlWaktuTerbit)) {
            return arrayTemp[0]; // shubuh
        }else if (realTime == jmlWaktuTerbit || realTime < jmlWaktuDzuhur) {
            return arrayTemp[5]; // bukan waktu shalat
        } else if ((realTime == jmlWaktuDzuhur) || (realTime < jmlWaktuAshar)) {
            return arrayTemp[1]; // dzuhur
        } else if ((realTime == jmlWaktuAshar) || (realTime < jmlWaktuMaghrib)) {
            return arrayTemp[2]; // ashar
        } else if ((realTime == jmlWaktuMaghrib) || (realTime < jmlWaktuIsya)) {
            return arrayTemp[3]; // maghrib
        } else if ((realTime == jmlWaktuIsya) || (realTime <= jmlBeMidnight)) {
            return arrayTemp[4]; // isya
        }  else {
            return arrayTemp[5]; // bukan waktu shalat
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
                mTextView.setText("Saatnya Shalat");
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
            if (prayerNames.get(i).equals("Shubuh")) {
                this.jmlWaktuShubuh = total;
            } else if (prayerNames.get(i).equals("Dzuhur")){
                this.jmlWaktuDzuhur = total;
            } else if (prayerNames.get(i).equals("Ashar")) {
                this.jmlWaktuAshar = total;
            } else if (prayerNames.get(i).equals("Maghrib")) {
                this.jmlWaktuMaghrib = total;
            } else if (prayerNames.get(i).equals("Isya")) {
                this.jmlWaktuIsya = total;
            }  else if (prayerNames.get(i).equals("Matahari Terbit")){
                this.jmlWaktuTerbit = total;
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
            if (prayerNames.get(i).equals("Shubuh")) {
                txt_shubuh.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals("Dzuhur")){
                txt_dzuhur.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals("Ashar")) {
                txt_ashar.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals("Maghrib")) {
                txt_maghrib.setText(prayerTimes.get(i).toString());
            } else if (prayerNames.get(i).equals("Isya")) {
                txt_isya.setText(prayerTimes.get(i).toString());
            }
        }
        // -----------------------------------------------------------------------------------------

    }


}
