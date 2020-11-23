package com.example.envsaqapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.envsaqapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class ForureningsUdsigt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //region Instance Fields
    private static double userX;
    private static double userY;
    private static String componentExtra;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Integer item1ID;
    private Integer item2ID;
    private Integer item3ID;
    private Integer item4ID;
    private Integer item5ID;
    private Integer item6ID;
    private Integer item7ID;
    private Integer item8ID;
    LineChart linechart1;
    LineChart linechart2;
    LineChart linechart3;
    LineDataSet set1,set2;
    private String component;
    //endregion Instance Fields

    //region Methods
    //Start of Comments onCreate()
    /*
    OnCreate() is a method that runs when the activity is created. Fx. if you change activity, it will be created before it is shown.
    OnCreate() is basically just a method with logic you want to have executed when the activity starts. Every activity has a OnCreate() method.
    There are also a OnStart() method that is run when the activity starts. Do not confuse these two, they are very different.
    In OnCreate() we set the layoutfile, we set window to be fullscreen. We then create and get an Intent that gets the user coordinates from the recent activity. Then we store the coordinates in
    some instance fields. We find the TextView from the layoutfile with findViewById, and set the TextViews text attribute to show the users coordinates.
    We then do the same with the DrawerLayout but now we create the listeners for the button in the actionBar.
    We then set a navigationViewListener to the navigationview, to check for when an item is pressed.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forurenings_udsigt);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setNavigationViewListener();
        mDrawerLayout = findViewById(R.id.ForUdsigtDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        linechart1 = findViewById(R.id.LineChart);
        linechart2 = findViewById(R.id.LineChart2);
        linechart3 = findViewById(R.id.LineChart3);
        CustomizeLinechart(linechart1,"NO2","16/11/2020");
        CustomizeLinechart(linechart2,"NO2","17/11/2020");
        CustomizeLinechart(linechart3,"NO2","18/11/2020");
        Button button1 = findViewById(R.id.Udsigtbutton1);
        Button button2 = findViewById(R.id.Udsigtbutton2);
        Button button3 = findViewById(R.id.Udsigtbutton3);
        button1.bringToFront();
        button2.bringToFront();
        button3.bringToFront();

        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        userX = intent.getDoubleExtra("pointX", userX);
        userY = intent.getDoubleExtra("userY", userY);
        component = intent.getStringExtra("componentExtra");
        Toast.makeText(ForureningsUdsigt.this,component,Toast.LENGTH_LONG).show();
        PopulateCharts(component);
    }
    //Start of Comments ChangeActivity()
    /*
    ChangeActivity() is the handler for the navigationbar. So when you press an item in the navigationbar, ChangeActivity is run, with the ID you
    get from OnNavigationItemSelected(). The method then takes the ID and checks which ID it matches, and if it matches ID 1 it changes to the first Activity.
    What happens is, that it creates and Intent, which is basically a package, and this intent is labeled with what class/activity it is coming from, and where it should go.
    The method then adds the users location in "userX" and userY" and puts them into the package. The package is then sent in startActivity(), and the app now changes activity to whatever
    activity that was pressed in the navigationbar.
    overridePendingTransition is just the animation that is run when you change the activity, and in this case its a fade_in fade_out. And the finish() method is just closing down the last activity
    */
    public void ChangeActivity(Integer ID){
        if (ID == item1ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, ForureningHer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);

        }
        else if (ID == item2ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, MainActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item3ID){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, NavigationUdsigt.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item4ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, GroenRute.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    //startActivity(i);
                    Toast.makeText(ForureningsUdsigt.this, "Ikke implementeret endnu ( ͡° ͜ʖ ͡°)", Toast.LENGTH_LONG).show();
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    //finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item5ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, Notifikationer.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
        else if (ID == item6ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, Forureningskala.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item7ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, Info.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        } else if (ID == item8ID) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ForureningsUdsigt.this, webViewActivity.class);
                    i.putExtra("userX", userX);
                    i.putExtra("userY", userY);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }, TIME_OUT);
        }
    }
    //Start of Comments onOptionsItemSelected
    /*
    This method is connected to the DrawerLayout. It checks when you use the menu, which item is selected and then returns the item within the OnNavigationItemSelected() method.
    */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){

        }

        return super.onOptionsItemSelected(item);
        }
    //Start of Comments onNavigationItemSelected
    /*
    This method contains a switch case that holds different ID's, one for each item in the menu. It has an item as parameter in the method, and then is uses the ID, to check which
    Activity to navigate to when pressed. When finished, it changes to a new Activity regarding which ID is selected, and then leads the user to a new Activity.
    */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ForHerItem1:
                item1ID = item.getItemId();
                ChangeActivity(item1ID);
                return true;
            case R.id.KortItem2:
                item2ID = item.getItemId();
                ChangeActivity(item2ID);
                return true;

            case R.id.UdsigtItem3:
                item3ID = item.getItemId();
                ChangeActivity(item3ID);
                return true;/*
            case R.id.GroenItem4:
                item4ID = item.getItemId();
                ChangeActivity(item4ID);
                return true;*/
            case R.id.NotiItem5:
                item5ID = item.getItemId();
                //Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
                ChangeActivity(item5ID);
                return true;
            case R.id.SkalaItem6:
                item6ID = item.getItemId();
                //Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
                ChangeActivity(item6ID);
                return true;
            case R.id.infoItem7:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item7ID = item.getItemId();
                ChangeActivity(item7ID);
                return true;
            case R.id.KortItem8:
                //Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                item8ID = item.getItemId();
                ChangeActivity(item8ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    //Start of Comments setNavigationViewListener
    /*
    This method finds the NavigationView with the findViewById() method, and then adds a listener to the navigationView that checks if an item in the list has been pressed or not.
    If an item has been pressed, it sets the value to 'true', so the method onNavigationItemSelected() knows it should execute.
    */
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.ForUdsigtNav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void PopulateCharts(String component){
        if (component == "No2"){

        }
        else if (component == "O3"){

        }
        else if (component == "PM25"){

        }
        else if (component == "PM10"){

        }

    }

    private void CustomizeLinechart(LineChart linechart,String component,String Date){
        LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),component);
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);
        linechart.getDescription().setText(Date);
        linechart.getDescription().setTextSize(12);
        linechart.getDescription().setYOffset(-25);
        linechart.getDescription().setTextColor(Color.GRAY);
        linechart.animateX(1000);
        LineData lineData = new LineData(iLineDataSets);
        linechart.setData(lineData);
        linechart.invalidate();
        linechart.setBackgroundColor(getResources().getColor(R.color.Lightblue));
        lineDataSet.setLineWidth(3);
        lineDataSet.setColor(R.color.colorPrimaryDark);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setCircleHoleColor(Color.GRAY);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextSize(10);
        lineDataSet.setFillColor(R.color.colorPrimaryDark);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillAlpha(120);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineData.setValueTypeface(Typeface.SERIF);
        lineDataSet.setCircleColor(Color.GRAY);
        linechart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        linechart.getAxisRight().setEnabled(false);
        linechart.getXAxis().setLabelCount(8,true);
        linechart.setDoubleTapToZoomEnabled(false);
        linechart.setScaleEnabled(false);
        linechart.getAxisLeft().setLabelCount(10);
        linechart.getAxisLeft().setXOffset(12);
    }

    private ArrayList<Entry> lineChartDataSet(){
        ArrayList<Entry> dataSet = new ArrayList<Entry>();
        dataSet.add(new Entry(00f,42));
        dataSet.add(new Entry(01f,18.5f));
        dataSet.add(new Entry(02f,12.6f));
        dataSet.add(new Entry(03f,32));
        dataSet.add(new Entry(04f,58));
        dataSet.add(new Entry(05f,69));
        dataSet.add(new Entry(06f,17));
        dataSet.add(new Entry(07f,23));
        dataSet.add(new Entry(08f,36));
        dataSet.add(new Entry(09f,15));
        dataSet.add(new Entry(10f,70));
        dataSet.add(new Entry(11f,16));
        dataSet.add(new Entry(12f,30));
        dataSet.add(new Entry(13f,17));
        dataSet.add(new Entry(14f,56));
        dataSet.add(new Entry(15f,10));
        dataSet.add(new Entry(16f,19));
        dataSet.add(new Entry(17f,22));
        dataSet.add(new Entry(18f,33));
        dataSet.add(new Entry(19f,70));
        dataSet.add(new Entry(20f,80));
        dataSet.add(new Entry(21f,52));
        dataSet.add(new Entry(22f,32));
        dataSet.add(new Entry(23f,16));

        return dataSet;
    }

    public void Toast(View view) {
        Toast.makeText(ForureningsUdsigt.this,"REEEEEEEEEEEEEEE",Toast.LENGTH_LONG).show();
    }

    public void LocationPick(View view){
        Intent i = new Intent(ForureningsUdsigt.this, MapPickActivity.class);
        startActivity(i);
    }

    //endregion Methods
}