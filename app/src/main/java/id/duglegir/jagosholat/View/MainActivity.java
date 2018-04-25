package id.duglegir.jagosholat.View;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import id.duglegir.jagosholat.R;
import id.duglegir.jagosholat.View.ViewPagerController.MainPagerAdapter;


public class MainActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan
    private TabLayout tab_layout;
    private ViewPager v_pager;
    private int resID;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Judul
    private String[] pageTitle = {"Catatan", "Jadwal", "Statistik", "Kiblat", "Tata Cara"};
    private String[] pageIcon = {"catat","jadwal","statistik","kompas","more"};
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -----------------------------------------------------------------------------------------
        // Deklarasi element XML
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Menjalankan Fungsi
        SlideView();// Fungsi Slide di gunakan untuk ViewPager sliding Fragments
        // -----------------------------------------------------------------------------------------

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Membuat Tombol Menu di Ujung Kanan Aplikasi
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Melihat Info Apps dengan cara Toolbar
        switch (item.getItemId()) {
            case R.id.info_apps:
                Intent i = new Intent(MainActivity.this, TentangKamiActivity.class);
                startActivity(i);
                return true;
        }
        //noinspection SimplifiableIfStatemen
        return super.onOptionsItemSelected(item);
    }

    public void SlideView(){

        // -----------------------------------------------------------------------------------------
        tab_layout = (TabLayout) findViewById(R.id.tablayout_main);
        v_pager = (ViewPager)findViewById(R.id.viewpager_main);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        for (int i = 0 ; i < pageTitle.length; i ++){
            resID = getResources().getIdentifier("ic_"+pageIcon[i]+"_24px" , "drawable", getPackageName());
            tab_layout.addTab(tab_layout.newTab().setIcon(resID));
        }
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        MainPagerAdapter Adatapters = new MainPagerAdapter(getSupportFragmentManager(), tab_layout.getTabCount());
        v_pager.setAdapter(Adatapters);
        // -----------------------------------------------------------------------------------------


        v_pager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                v_pager.setCurrentItem(tab.getPosition());
                setTitle(pageTitle[tab.getPosition()]);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.IconSelect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.IconUnselect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
