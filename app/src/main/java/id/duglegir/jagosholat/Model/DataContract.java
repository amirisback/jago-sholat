package id.duglegir.jagosholat.Model;

import android.net.Uri;
import android.provider.BaseColumns;

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

public class DataContract {
    // Class ini tujuannya untuk menjadi object table

    private DataContract() {
    }

    // Content URI ---------------------------------------------------------------------------------
    public static final String CONTENT_AUTHORITY = "id.duglegir.jagosholat"; // Nama Domain Aplikasi
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_DATA = "data_ibadah"; // Isinya sama dengan Table Name
    // ---------------------------------------------------------------------------------------------

    // Deklarasi Elemen Table ----------------------------------------------------------------------
    public static final class DataEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_DATA);
        public static final String TABLE_NAME = "data_ibadah"; // Deklarasi Nama Table
        public static final String _ID  = BaseColumns._ID; // Dekalarasi ID
        public static final String COLUMN_TANGGAL = "tanggal";
        public static final String COLUMN_SHALAT = "shalat";
        public static final String COLUMN_WAKTU = "waktu";
        public static final String COLUMN_STATUS = "status";
    }
    // ---------------------------------------------------------------------------------------------

}
