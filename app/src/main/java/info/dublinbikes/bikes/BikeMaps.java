package info.dublinbikes.bikes;

import info.dublinbikes.bikes.widget.MyBottomSheetDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;
/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.maps.model.Marker;
import java.io.IOException;
import java.io.InputStream;
import android.app.Dialog;
import android.view.Window;
import android.view.Gravity;
import android.view.ViewGroup.*;
import android.support.design.widget.Snackbar;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetBehavior;
import android.widget.Toast;
import info.dublinbikes.bikes.fragments.CustomInfoWindowGoogleMap;
/**
 * A simple {@link Fragment} subclass.
 */
public class BikeMaps extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    Dialog myDialog;
    private BottomSheetDialog mBottomSheetDialog;
SupportMapFragment mapFragment;
    public BikeMaps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_bike_maps, container, false);

        mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        if(mapFragment==null)
        {

            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            mapFragment=SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();

        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        try {
            JSONArray object = new JSONArray(loadJsonFromAsset());

            for (int i = 0; i < object.length(); i++) {
                JSONObject object1 = (JSONObject) object.get(i);
                String name1=(String)object1.get("name");
                // Toast.makeText(getActivity().getApplicationContext(),name1,Toast.LENGTH_SHORT).show();
                JSONObject object2 = (JSONObject) object1.get("extra");
                String address=(String) object2.get("address");

                String id=(String) object1.get("id");
                String status=(String) object2.get("status");
                double lat=(double)object1.get("latitude");
                double lon=(double)object1.get("longitude");
                int slots=(int)object1.get("empty_slots");
                int free_bikes=(int)object1.get("free_bikes");


                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lon))
                        .title("Station Name :"+name1)
                        .snippet("Empty Slots : "+slots+"\n"+"Bikes Available :"+free_bikes+"\n"+"Station Address : "+address+"\n"+"Station Status :"+status));
                      //  .snippet(" Empty slots :"+slots+"   "+"Free Bikes :"+free_bikes));



               googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                   @Override
                   public boolean onMarkerClick(Marker marker) {

                     String data=  marker.getSnippet();
                     String title=marker.getTitle();
                       MyBottomSheetDialog myBottomSheetDialog = MyBottomSheetDialog.getInstance(getActivity());
                       myBottomSheetDialog.setTvTitle("Station Details ");
                       myBottomSheetDialog.setTvSubTitle(title+"\n"+data+"\n");
                     //  myBottomSheetDialog.setIvAvatar("https://avatars3.githubusercontent.com/u/6635954?v=3&u=d18aab686938ecda4b96f29e4e3b776008ced91f&s=400");
                       myBottomSheetDialog.setCanceledOnTouchOutside(true);
                       myBottomSheetDialog.show();



                     return false;
                   }
               });

                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LinearLayout info = new LinearLayout(getActivity().getApplicationContext());
                        info.setOrientation(LinearLayout.VERTICAL);

                        TextView title = new TextView(getActivity().getApplicationContext());
                        title.setTextColor(Color.BLACK);
                        title.setGravity(Gravity.CENTER);
                        title.setTypeface(null, Typeface.BOLD);
                        title.setText(marker.getTitle());

                        TextView snippet = new TextView(getActivity().getApplicationContext());
                        snippet.setTextColor(Color.GRAY);
                        snippet.setText(marker.getSnippet());

                        info.addView(title);
                        info.addView(snippet);

                        return info;
                    }
                });

                /*
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat, lon))
                        .title("Station Name :"+name1)
                        .snippet("Empty slots :"+slots+"   "+"Free Bikes :"+free_bikes);


                bikes info = new bikes();
                info.setname("Free Slots : "+name1);
                info.setAddress("Free Bikes : "+address);


                CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getActivity());
                mMap.setInfoWindowAdapter(customInfoWindow);

                Marker m = mMap.addMarker(markerOptions);
                m.setTag(info);
                m.showInfoWindow();

             */
               googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
            }
          //  googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.341428, -6.24672), 10));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public String loadJsonFromAsset(){

        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("DublinBikes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;



    }
}
