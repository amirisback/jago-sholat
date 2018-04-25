package id.duglegir.jagosholat.Model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import id.duglegir.jagosholat.Model.DataContract.DataEntry;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 30/03/2018.
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

public class DataProvider extends ContentProvider {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan Variable
    // Setiap Table memiliki alamat sendiri
    private static final int DATA = 100; // Projection All
    private static final int DATA_ID = 101; // Projection ID
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DatabaseHelper mDbHelper;
    // ---------------------------------------------------------------------------------------------

    public static final String LOG_TAG = DataProvider.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    static {
        sUriMatcher.addURI(DataContract.CONTENT_AUTHORITY, DataContract.PATH_DATA, DATA);
        sUriMatcher.addURI(DataContract.CONTENT_AUTHORITY, DataContract.PATH_DATA + "/#", DATA_ID);
    }
    // ---------------------------------------------------------------------------------------------

    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
            // Get readable database
            SQLiteDatabase database = mDbHelper.getReadableDatabase();

            // This cursor will hold the result of the query
            Cursor cursor;

            // Figure out if the URI matcher can match the URI to a specific code
            int match = sUriMatcher.match(uri);
            switch (match) {
                case DATA:
                    cursor = database.query(DataEntry.TABLE_NAME, projection, selection,
                            selectionArgs, null,null,sortOrder); // Select Semua Data
                    break;
                case DATA_ID:

                    selection = DataContract.DataEntry._ID + "=?"; // Where ID = "?"
                    selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                    cursor = database.query(DataContract.DataEntry.TABLE_NAME, projection, selection, selectionArgs,
                            null, null, sortOrder);
                    break;
                default:
                    throw new IllegalArgumentException("Cannot query unknown URI " + uri);
            }

            cursor.setNotificationUri(getContext().getContentResolver(), uri);

            return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA :
                return insertData(uri, values);
            default :
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }





    private Uri insertData(Uri uri, ContentValues values){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(DataEntry.TABLE_NAME, null , values);

        if (id ==-1){
            Log.e(LOG_TAG, "Failed to insert row for "+uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA :
                return updateData(uri, contentValues, selection, selectionArgs);
            case DATA_ID :
                selection = DataEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return  updateData(uri, contentValues, selection, selectionArgs);
            default :
                throw new IllegalArgumentException("Update is not Supported");
        }

    }

    private int updateData(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (values.size() == 0){
            return 0;
        }
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowUpdated = database.update(DataEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowUpdated;

    }


}
