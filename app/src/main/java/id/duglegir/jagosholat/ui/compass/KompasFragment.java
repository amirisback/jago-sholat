package id.duglegir.jagosholat.ui.compass;

import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import id.duglegir.jagosholat.util.KompasGPSTracker;
import id.duglegir.jagosholat.util.KompasRose;
import id.duglegir.jagosholat.R;

public class KompasFragment extends Fragment implements SensorListener {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class
    private KompasRose kompasRose;
    private KompasGPSTracker gps;
    // ---------------------------------------------------------------------------------------------
    // Deklarasi XML Layout
    private RelativeLayout direcCantainer;
    private View view;
    private TextView CountryName;
    // ---------------------------------------------------------------------------------------------
    private Context context;
    private SensorManager sensorManager;
    private static final int sensor = SensorManager.SENSOR_ORIENTATION;
    // ---------------------------------------------------------------------------------------------
    private double latitude, longitude;
    private double Qlati = 21.42243;
    private double Qlongi = 39.82624;
    public static double degree;
    // ---------------------------------------------------------------------------------------------
    private String location_string;
    private String add;
    private int Distance;
    // ---------------------------------------------------------------------------------------------

    public KompasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kompas, container, false);

        // -----------------------------------------------------------------------------------------
        context = getActivity();
        direcCantainer = (RelativeLayout) rootView.findViewById(R.id.container_layout);
        kompasRose = new KompasRose(context);
        sensorManager = (SensorManager) getContext().getSystemService(context.SENSOR_SERVICE);
        AsyncCallWS2 task = new AsyncCallWS2();
        // -----------------------------------------------------------------------------------------
        IntializeView(rootView);
        getlocation();
        task.execute();
        // -----------------------------------------------------------------------------------------
        Distance = getDistanceBetween();
        degree = bearing(latitude, longitude, Qlati, Qlongi);
        direcCantainer.addView(kompasRose);
        kompasRose.invalidate();
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

    public JSONObject getLocationInfo() {
        InputStream is = null;
        JSONObject jObject = null;
        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=true");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {
        // private String s;

        final ProgressDialog dialog = new ProgressDialog(context);

        @Override
        protected Void doInBackground(String... params) {
            JSONObject ret = getLocationInfo();
            JSONObject location;

            try {
                location = ret.getJSONArray("results").getJSONObject(0);
                location_string = location.getString("formatted_address");
                Log.d("test", "formattted address:" + location_string);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            CountryName.setText(location_string);
            dialog.dismiss();

        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading...");
            dialog.show();
        }
    }

    private int getDistanceBetween() {

        // -----------------------------------------------------------------------------------------
        double latA = Qlati;
        double lngA = Qlongi;
        double latB = latitude;
        double lngB = longitude;
        // -----------------------------------------------------------------------------------------
        Location locationA = new Location("point A");
        Location locationB = new Location("point B");
        // -----------------------------------------------------------------------------------------
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);
        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);
        // -----------------------------------------------------------------------------------------
        float distance = locationA.distanceTo(locationB);
        int dis = (int) distance;
        // -----------------------------------------------------------------------------------------

        return dis;
    }


    private void IntializeView(View root) {
        CountryName = (TextView) root.findViewById(R.id.idTvCountryName);
    }

    protected double bearing(double startLat, double startLng, double endLat, double endLng) {
        double longitude1 = startLng;
        double longitude2 = endLng;
        double latitude1 = Math.toRadians(startLat);
        double latitude2 = Math.toRadians(endLat);
        double longDiff = Math.toRadians(longitude2 - longitude1);
        double y = Math.sin(longDiff) * Math.cos(latitude2);
        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            add = obj.getLocality();
            add = add + ", " + obj.getCountryName();
            add = obj.getCountryName();
            Log.v("IGA", "Address" + add);
            CountryName.setText(add);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "You have no Internet connection", Toast.LENGTH_SHORT).show();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            Toast.makeText(context, "You have no Internet connection", Toast.LENGTH_SHORT).show();
        }
    }


    private void getlocation() {
        gps = new KompasGPSTracker(context);
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (sensor != KompasFragment.sensor)
            return;
        int orientation = (int) values[0];
        kompasRose.setDirections(orientation, orientation);
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}
