package id.duglegir.jagosholat.model;

/**
 * Created by Bryan on 3/3/2018.
 */

public class NiatShalat {

    private String rakaat, shalat, arab, latin, terjemah;

    public NiatShalat(String rakaat, String shalat, String arab, String latin, String terjemah) {
        this.rakaat = rakaat;
        this.shalat = shalat;
        this.arab = arab;
        this.latin = latin;
        this.terjemah = terjemah;
    }

    public String getRakaat() {
        return rakaat;
    }

    public void setRakaat(String rakaat) {
        this.rakaat = rakaat;
    }

    public String getShalat() {
        return shalat;
    }

    public void setShalat(String shalat) {
        this.shalat = shalat;
    }

    public String getArab() {
        return arab;
    }

    public void setArab(String arab) {
        this.arab = arab;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getTerjemah() {
        return terjemah;
    }

    public void setTerjemah(String terjemah) {
        this.terjemah = terjemah;
    }
}
