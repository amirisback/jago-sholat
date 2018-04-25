package id.duglegir.jagosholat.View.FeatureFragmentChild.FeatureObject;

/**
 * Created by Bryan on 3/12/2018.
 */

public class DoaShalat {
    private String arabDoa, latinDoa, terjemahDoa;

    public DoaShalat(String arabDoa, String latinDoa, String terjemahDoa) {
        this.arabDoa = arabDoa;
        this.latinDoa = latinDoa;
        this.terjemahDoa = terjemahDoa;
    }

    public String getArabDoa() {
        return arabDoa;
    }

    public void setArabDoa(String arabDoa) {
        this.arabDoa = arabDoa;
    }

    public String getLatinDoa() {
        return latinDoa;
    }

    public void setLatinDoa(String latinDoa) {
        this.latinDoa = latinDoa;
    }

    public String getTerjemahDoa() {
        return terjemahDoa;
    }

    public void setTerjemahDoa(String terjemahDoa) {
        this.terjemahDoa = terjemahDoa;
    }
}
