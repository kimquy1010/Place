package com.example.quynh.place.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapUtils {
    public static LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }


    //Address and title only
    public static void addMarkersToMap(Context context, String address, String title, GoogleMap map) {
        LatLng locationFromAddress = getLocationFromAddress(context, address);
        addMarkersToMap(locationFromAddress, title, map);
    }

    public static void addMarkersToMap(LatLng addressCoord, String title, GoogleMap map) {
        if (addressCoord != null)
            map.addMarker(new MarkerOptions().position(addressCoord).title(title));
    }

    //Address, title and snippet
    public static void addMarkersToMap(Context context, String address, String title, String snippet, GoogleMap map) {
        LatLng locationFromAddress = getLocationFromAddress(context, address);
        addMarkersToMap(locationFromAddress, title, snippet, map);
    }

    public static void addMarkersToMap(LatLng addressCoord, String title, String snipper, GoogleMap map) {
        if (addressCoord != null)
            map.addMarker(new MarkerOptions().position(addressCoord).snippet(snipper).title(title));
    }


    //Address, title, snippet, icon
    public static void addMarkersToMap(Context context, String address, String title, String snippet, int bitmapResource, GoogleMap map) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(bitmapResource);
        addMarkersToMap(context, address, title, snippet, icon, map);
    }

    public static void addMarkersToMap(Context context, String address, String title, String snippet, Bitmap bitmapIcon, GoogleMap map) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bitmapIcon);
        addMarkersToMap(context, address, title, snippet, icon, map);
    }

    public static void addMarkersToMap(Context context, String address, String title, String snippet, BitmapDescriptor icon, GoogleMap map) {
        LatLng locationFromAddress = getLocationFromAddress(context, address);
        if (locationFromAddress != null)
            addMarkersToMap(locationFromAddress, title, snippet, icon, map);
    }

    public static void addMarkersToMap(LatLng addressCoord, String title, String snippet, BitmapDescriptor icon, GoogleMap map) {
        if (addressCoord != null)
            map.addMarker(new MarkerOptions().position(addressCoord).title(title).snippet(snippet).icon(icon));
    }


    //Address, title, bitmap
    public static void addMarkersToMap(Context context, String address, String title, int bitmapResourceId, GoogleMap map) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(bitmapResourceId);
        addMarkersToMap(context, address, title, icon, map);
    }

    public static void addMarkersToMap(Context context, String address, String title, BitmapDescriptor icon, GoogleMap map) {
        LatLng locationFromAddress = getLocationFromAddress(context, address);
        if (locationFromAddress != null)
            map.addMarker(new MarkerOptions().position(locationFromAddress).title(title).icon(icon));
    }


    public static void moveCameraToAddress(Context context, String address, GoogleMap map) {
        LatLng locationFromAddress = getLocationFromAddress(context, address);
        CameraPosition newLocation = new CameraPosition.Builder().target(locationFromAddress).zoom(15.5f)
                .bearing(0)
                .tilt(25)
                .build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(newLocation));
    }
}
