package io.mapwize.example;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.mapwize.mapwize.*;

public class MainActivity extends AppCompatActivity implements MWZMapViewListener, SensorEventListener, SearchListener{//, FloatingSearchView.OnMenuItemClickListener{

    MWZMapView mapview;
    private SensorManager mSensorManager;
    private Sensor mCompass;
    private SearchFragment mSearchFragment;
    public static String placeid;
    public static String label;
    TextView title;
    private static SharedPreferences prefs;
    LinearLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        placeid = intent.getStringExtra("placeid");
        label = intent.getStringExtra("label");

        prefs = this.getSharedPreferences("language", 0);
        String language = prefs.getString("language", "");



        if(language.equals("EN")){
            toolbar = (LinearLayout)findViewById(R.id.toolbar_en);
            toolbar.setVisibility(View.VISIBLE);
            title = (TextView)findViewById(R.id.title_map_en);
            title.setText(label);
        }
        else
        {
            toolbar = (LinearLayout)findViewById(R.id.toolbar_ar);
            toolbar.setVisibility(View.VISIBLE);
            title = (TextView)findViewById(R.id.title_map_ar);
            title.setText(label);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
            }
        }
        mapview = (MWZMapView) this.findViewById(R.id.mwzview);
        mapview.setListener(this);
        //mSearchFragment = (SearchFragment)getSupportFragmentManager().findFragmentById(R.id.search_fragment);
        //mSearchFragment.setSearchListener(this);
        //mSearchFragment.setMenuListener(this);

    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setPreferredLanguageFR () {
        this.mapview.setPreferredLanguage("fr");
    }

    public void setPreferredLanguageEN () {
        this.mapview.setPreferredLanguage("en");
    }

    public void setZoom(){
        this.mapview.setZoom(19);
    }

    public void centerOnCoordinates() {
        MWZCoordinate coordinate = new MWZCoordinate(24.487762667889086, 54.37841061060524);
        this.mapview.centerOnCoordinates(coordinate, 19);
    }
/*
    public void centerOnCoordinatesWithFloor() {
        MWZCoordinate coordinate = new MWZCoordinate(49.74252973220731, 4.599119424819946, 2d);
        this.mapview.centerOnCoordinates(coordinate, 18);
    }

    public void setFloor() {
        this.mapview.setFloor(2);
    }*/

    public void centerOnPlace() {
        this.mapview.centerOnPlace("5d2317cab7e7d80016db9207");
    }

    /*public void centerOnUser() {
        this.mapview.centerOnUser(19);
    }

    public void loadUrl() {
        this.mapview.loadURL("http://mwz.io/aaa", new LoadURLCallbackInterface() {
            @Override
            public void onResponse(Error error) {
                if (error != null) {
                    showToast("LoadUrlError");
                }
            }
        });
    }

    public void addMarker() {
        this.mapview.addMarker(49.74278626088478, 4.598293304443359, null);
    }

    public void removeMarkers() {
        this.mapview.removeMarkers();
    }

    public void setFollowUserModeON() {
        this.mapview.setFollowUserMode(true);
    }

    public void setFollowUserModeOFF() {
        this.mapview.setFollowUserMode(false);
    }

    public void setUserPosition() {
        MWZUserPosition userPosition = new MWZUserPosition();
        userPosition.setLatitude(49.74278626088478);
        userPosition.setLongitude(4.5982933044);
        userPosition.setAccuracy(50);
        this.mapview.setUserPosition(userPosition);
    }

    public void removeUserPosition() {
        this.mapview.removeUserPosition();
    }*/

    public void unlockUserPosition() {
        this.mapview.unlockUserPosition();
    }

    public void showDirections() {
        MWZCoordinate coord = new MWZUserPosition(24.487693206031665, 54.37853149125834, 1d, 15);
        MWZPlace from = new MWZPlace();
        from.setIdentifier("5d23435996fcf8001640d79d");
        MWZPlace to = new MWZPlace();
        Log.w("place id",placeid);
        to.setIdentifier(placeid);
       /* MWZPlace wp1 = new MWZPlace();
        wp1.setIdentifier("56c34fc402275a0b00fb00f6");
        MWZPlace wp2 = new MWZPlace();
        wp2.setIdentifier("56c344a402275a0b00fb00bf");
        List<MWZDirectionPoint> arr = new ArrayList<>();
        arr.add(wp1);
        arr.add(wp2);*/
        List<MWZDirectionPoint> arr = new ArrayList<>();
        MWZDirectionOptions options = new MWZDirectionOptions();
        options.setAccessible(true);
        MWZApi.getDirection(from, to, arr, options, new MWZCallback<MWZDirection>() {
            @Override
            public void onSuccess(MWZDirection object) {
                mapview.startDirections(object);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("Failure ", " " + t);
            }
        });

    }
/*
    public void stopDirections() {
        this.mapview.stopDirections();
    }

    public void setStyle() {
        MWZStyle style = new MWZStyle();
        style.setFillColor("#FF0000");
        style.setLabelBackgroundColor("#0000FF");
        style.setStrokeColor("#AAAAAA");
        style.setMarkerUrl("https://cdn4.iconfinder.com/data/icons/medical-soft-1/512/map_marker_pin_pointer_navigation_location_point_position-128.png");
        this.mapview.setStyle("56c3426202275a0b00fb00b9", style);
    }

    public void setBottomMargin() {
        this.mapview.setBottomMargin(300);
    }

    public void setTopMargin() {
        this.mapview.setTopMargin(60);
    }

    public void resetMargin() {
        this.mapview.setBottomMargin(0);
        this.mapview.setTopMargin(0);
    }

    @SuppressWarnings("deprecation")
    public void setUserHeading() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mCompass = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mCompass, SensorManager.SENSOR_DELAY_GAME);
    }

    public void removeHeading() {
        mSensorManager.unregisterListener(this);
        this.mapview.setUserHeading(null);
    }

    public void refresh() {
        this.mapview.refresh();
    }

    public void getFloor() {
        showToast("Floor :" + this.mapview.getFloor());
    }

    public void getZoom() {
        showToast("Zoom :" + this.mapview.getZoom());
    }

    public void getUserPosition() {
        showToast("UserPosition :" + this.mapview.getUserPosition());
    }

    public void getCenter() {
        showToast("Center :" + this.mapview.getCenter());
    }

    public void startLocation() {
        this.mapview.startLocation(true);
    }

    public void stopLocation() {
        this.mapview.stopLocation();
    }
*/

    @Override
    public void onVenueSuggestionClicked(MWZVenue venue) {
        MWZCoordinate center = venue.getMarker();
        mapview.centerOnCoordinates(center, 17);
    }

    @Override
    public void onPlaceSuggestionClicked(final MWZPlace place) {
        MWZCoordinate center = place.getMarker();
        mapview.centerOnCoordinates(center, 21);
    }

    @Override
    public void onMapLoad() {
        Log.i("onMapLoad", "loaded");
        //this.centerOnPlace();
        this.setZoom();
        this.showDirections();
    }

    @Override
    public void onZoomEnd(Integer zoom) {
        if(zoom <=19)
            this.centerOnCoordinates();
        Log.i("Zoomend", "" + zoom);
    }

    @Override
    public void onClick(MWZCoordinate latlon) {
        Log.i("OnClick", "" + latlon);
    }

    @Override
    public void onContextMenu(MWZCoordinate latlon) {
        Log.i("OnContextMenu", "" + latlon);
    }

    @Override
    public void onFloorChange(Double floor) {
        Log.i("OnFloorChange", "" + floor);
    }

    @Override
    public void onFloorsChange(Double[] floors) {
        Log.i("OnFloorsChange", "floors");
    }

    @Override
    public void onPlaceClick(MWZPlace place) {
        Log.i("OnPlaceClick", "" + place);
    }

    @Override
    public void onVenueClick(MWZVenue venue) {
        Log.i("OnVenueClick", "" + venue);
    }

    @Override
    public void onMarkerClick(MWZCoordinate position) {
        Log.i("OnMarkerClick", "" + position);
    }

    @Override
    public void onMoveEnd(MWZCoordinate latlon) {
        Log.i("OnMoveEnd", "" + latlon);
    }

    @Override
    public void onUserPositionChange(MWZUserPosition coordinate) {
        Log.i("OnUserPositionChange", "" + coordinate);
    }

    @Override
    public void onFollowUserModeChange(boolean followUserMode) {
        Log.i("OnFollowUserModeChange", "" + followUserMode);
    }

    @Override
    public void onDirectionsStart(String info) {
        Log.i("OnDirectionsStart", "" + info);
    }

    @Override
    public void onDirectionsStop(String info) {
        Log.i("OnDirectionsStop", "" + info);
    }

    @Override
    public void onMonitoredUuidsChange(String[] uuids) {
        Log.i("OnMonitoredUuidsChange", "" + uuids);
    }

    @Override
    public void onMissingPermission(String accessFineLocation) {
        Log.i("onMissingPermission", "" + accessFineLocation);
    }

    @Override
    public void onMapLoaded() {
        Log.i("onMapLoaded", "MapWize loaded !");
        //mapview.refresh();
    }

    @Override
    public void onVenueEnter(MWZVenue venue) {

        Log.i("onVenueEnter", "" + venue.toString());
    }

    @Override
    public void onVenueExit(MWZVenue venue) {
        Log.i("onVenueExit", "done");
    }


    @Override
    public void onReceivedError(String error) {
        Log.i("OnReceivedError", error);
    }

    @Override
    @SuppressWarnings("deprecation")
    public final void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_ORIENTATION:
                this.mapview.setUserHeading((new Double(event.values[0])));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    /*
        @Override
        public void onActionMenuItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_setPreferredLanguageFR:
                    this.setPreferredLanguageFR();
                    break;
                case R.id.action_setPreferredLanguageEN:
                    this.setPreferredLanguageEN();
                    break;
                case R.id.action_setZoom:
                    this.setZoom();
                    break;
                case R.id.action_centerOnCoordinates:
                    this.centerOnCoordinates();
                    break;
                case R.id.action_centerOnCoordinatesWithFloor:
                    this.centerOnCoordinatesWithFloor();
                    break;
                case R.id.action_setFloor:
                    this.setFloor();
                    break;
                case R.id.action_centerOnPlace:
                    this.centerOnPlace();
                    break;
                case R.id.action_centerOnUser:
                    this.centerOnUser();
                    break;
                case R.id.action_loadUrl:
                    this.loadUrl();
                    break;
                case R.id.action_addMarker:
                    this.addMarker();
                    break;
                case R.id.action_removeMarkers:
                    this.removeMarkers();
                    break;
                case R.id.action_setFollowUserModeON:
                    this.setFollowUserModeON();
                    break;
                case R.id.action_setFollowUserModeOFF:
                    this.setFollowUserModeOFF();
                    break;
                case R.id.action_setUserPosition:
                    this.setUserPosition();
                    break;
                case R.id.action_unlockUserPosition:
                    this.unlockUserPosition();
                    break;
                case R.id.action_showDirections:
                    this.showDirections();
                    break;
                case R.id.action_stopDirections:
                    this.stopDirections();
                    break;
                case R.id.action_setStyle:
                    this.setStyle();
                    break;
                case R.id.action_setBottomMargin:
                    this.setBottomMargin();
                    break;
                case R.id.action_setTopMargin:
                    this.setTopMargin();
                    break;
                case R.id.action_resetMargin:
                    this.resetMargin();
                    break;
                case R.id.action_setUserHeading:
                    this.setUserHeading();
                    break;
                case R.id.action_removeHeading:
                    this.removeHeading();
                    break;
                case R.id.action_getFloor:
                    this.getFloor();
                    break;
                case R.id.action_getZoom:
                    this.getZoom();
                    break;
                case R.id.action_getUserPosition:
                    this.getUserPosition();
                    break;
                case R.id.action_getCenter:
                    this.getCenter();
                    break;
                case R.id.action_removeUserPosition:
                    this.removeUserPosition();
                    break;
                case R.id.action_startLocation:
                    this.startLocation();
                    break;
                case R.id.action_stopLocation:
                    this.stopLocation();
                    break;
                case R.id.action_refresh:
                    this.refresh();
                    break;
                }
        }
    */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void backButtonToolbar(View view) {
        onBackPressed();
    }
}
