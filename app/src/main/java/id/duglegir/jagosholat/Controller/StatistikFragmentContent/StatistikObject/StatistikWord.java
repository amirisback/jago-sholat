package id.duglegir.jagosholat.Controller.StatistikFragmentContent.StatistikObject;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 25/04/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : f.miir117@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class StatistikWord {
    String id, waktu, shalat;

    public StatistikWord(String id, String waktu, String shalat) {
        this.id = id;
        this.waktu = waktu;
        this.shalat = shalat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getShalat() {
        return shalat;
    }

    public void setShalat(String shalat) {
        this.shalat = shalat;
    }
}
