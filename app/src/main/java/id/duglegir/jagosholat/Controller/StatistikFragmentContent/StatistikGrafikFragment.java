package id.duglegir.jagosholat.Controller.StatistikFragmentContent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import id.duglegir.jagosholat.Controller.ImportantMethod.FunctionHelper;
import id.duglegir.jagosholat.Model.DataOperation;
import id.duglegir.jagosholat.R;
import id.duglegir.jagosholat.View.MainActivityChild.StatistikFragment;


public class StatistikGrafikFragment extends Fragment {

    // ---------------------------------------------------------------------------------------------
    private LineChart mChart;
    // ---------------------------------------------------------------------------------------------
    private FunctionHelper functionHelper = new FunctionHelper();
    private DataOperation crud = new DataOperation();
    // ---------------------------------------------------------------------------------------------

    public StatistikGrafikFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_statistik_grafik, container, false);

        // -----------------------------------------------------------------------------------------
        mChart = (LineChart) rootView.findViewById(R.id.stat_chart);
        // -----------------------------------------------------------------------------------------
        ShowChart();

        return rootView;
    }

    public void ShowChart(){
        // -----------------------------------------------------------------------------------------
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        // -----------------------------------------------------------------------------------------
        ArrayList<Entry> yValues = new ArrayList<>();
        // -----------------------------------------------------------------------------------------
        yValues.add(new Entry(0,4f));
        yValues.add(new Entry(1,10f));
        yValues.add(new Entry(2,2f));
        yValues.add(new Entry(3,5f));
        yValues.add(new Entry(4,2f));
        yValues.add(new Entry(5,1f));
        yValues.add(new Entry(6,0f));
        // -----------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------
        LineDataSet mLineDataSet = new LineDataSet(yValues, "Statistik");
        // -----------------------------------------------------------------------------------------
        mLineDataSet.setFillAlpha(50);
        mLineDataSet.setColor(Color.BLACK);
        mLineDataSet.setLineWidth(3f);
        mLineDataSet.setValueTextSize(10f);
        mLineDataSet.setValueTextColor(Color.BLACK);
        mChart.setBorderColor(Color.GREEN);
        // -----------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(mLineDataSet);
        // -----------------------------------------------------------------------------------------
        LineData data = new LineData(dataSets);
        mChart.setData(data);
        // -----------------------------------------------------------------------------------------
        String[] months = new String[] {"Senin", "Selasa" ,"Rabu" , "Kamis" , "Jumat" , "Sabtu", "Minggu"};
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter( new StatistikFragment.MyXAxisValueFormatter(months));
        // -----------------------------------------------------------------------------------------
    }
}