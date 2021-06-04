package id.duglegir.jagosholat.Controller.StatistikFragmentContent;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import id.duglegir.jagosholat.Controller.ClassHelper.FunctionHelper;
import id.duglegir.jagosholat.Controller.StatistikFragmentContent.StatistikAdapter.StatistikHarianCursorAdapter;
import id.duglegir.jagosholat.Controller.StatistikFragmentContent.StatistikObject.StatistikWord;
import id.duglegir.jagosholat.Model.DataContract.DataEntry;
import id.duglegir.jagosholat.Model.DataOperation;
import id.duglegir.jagosholat.R;

public class StatistikHarianFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan
    private static final int DATA_LOADER = 0;
    private ArrayList<StatistikWord> arrayWord = new ArrayList<>();
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Deklarasi XML Alert Dialog
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;
    private TextView txt_waktu;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class Helper yang diperlukan
    private FunctionHelper functionHelper = new FunctionHelper();
    private DataOperation crud = new DataOperation();
    private StatistikHarianCursorAdapter mCursorAdapter;
    // ---------------------------------------------------------------------------------------------

    public StatistikHarianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statistik_harian, container, false);

        // -----------------------------------------------------------------------------------------
        View empty_listView = rootView.findViewById(R.id.empty_views);
        ListView mListView = rootView.findViewById(R.id.listViewStatistik);
        ProgressBar mProgressBar = rootView.findViewById(R.id.stat_progressBar);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        mListView.setEmptyView(empty_listView);
        mCursorAdapter = new StatistikHarianCursorAdapter(getActivity(), null);
        mListView.setAdapter(mCursorAdapter);
        mProgressBar.setProgress(getProgress());
        getDataFromTable();
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ---------------------------------------------------------------------------------
                String getArrayId = arrayWord.get(position).getId();
                String getArrayWaktu = arrayWord.get(position).getWaktu();
                DialogForm(getArrayId, getArrayWaktu);
                // ---------------------------------------------------------------------------------
            }
        });

        // -----------------------------------------------------------------------------------------


        return rootView;
    }

    // ---------------------------------------------------------------------------------------------
    // Pop Up update waktu
    private void DialogForm(final String mID, String mWaktu) {

        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML Update View
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.content_statistik_update, null);
        txt_waktu = dialogView.findViewById(R.id.txt_waktu_update);
        // -----------------------------------------------------------------------------------------
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        txt_waktu.setText(mWaktu);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Set Waktu
        txt_waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog mTimePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        functionHelper.getFormatTimePicker(txt_waktu, hourOfDay, minute);
//                        txt_waktu.setText(hourOfDay + ":" + minute);
                    }
                }, functionHelper.getSystemJam(), functionHelper.getSystemMenit(), true);

                mTimePickerDialog.show();
            }
        });
        // -----------------------------------------------------------------------------------------


        dialog.setPositiveButton("CATAT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // ---------------------------------------------------------------------------------
                String tempWaktu = txt_waktu.getText().toString();
                String selection = DataEntry._ID + " = '" + mID + "'";
                // ---------------------------------------------------------------------------------

                // ---------------------------------------------------------------------------------
                boolean isUpdated = crud.updateDataWaktu(getContext(), tempWaktu, selection, null);
                if (isUpdated) {
                    Toast.makeText(getActivity(), "Waktu Telah Diubah", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Data Not Updadted", Toast.LENGTH_LONG).show();
                }
                // ---------------------------------------------------------------------------------

                dialog.dismiss(); // Keluar Dari Dialog
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Keluar Dari Dialog
            }
        });

        dialog.show();
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    public void getDataFromTable() {
        Cursor cursor = crud.getDataTanggal(getContext(), functionHelper.getDateToday());
        while (cursor.moveToNext()){
            // -------------------------------------------------------------------------------------
            int idColoumnIndex = cursor.getColumnIndex(DataEntry._ID);
            int shalatColoumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_SHALAT);
            int waktuColoumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_WAKTU);
            // -------------------------------------------------------------------------------------
            String id = cursor.getString(idColoumnIndex);
            String shalat = cursor.getString(shalatColoumnIndex);
            String waktu = cursor.getString(waktuColoumnIndex);
            // -------------------------------------------------------------------------------------
            arrayWord.add(new StatistikWord(id, waktu, shalat));
            // -------------------------------------------------------------------------------------
        }
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    public int getProgress(){
        Cursor cursor = crud.getDataTanggal(getContext(), functionHelper.getDateToday());
        int countTable = cursor.getCount();
        int progress = countTable * 20;
        return progress;
    }
    // ---------------------------------------------------------------------------------------------


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(DATA_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection = DataEntry.COLUMN_TANGGAL + " = '" + functionHelper.getDateToday() + "'";
        return new CursorLoader(getActivity(), // getActivity disini menggantikan this
                DataEntry.CONTENT_URI,
                crud.getProjection(),
                selection,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}