package info.dublinbikes.bikes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.dublinbikes.bikes.R;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.widget.Toast;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.io.*;
import java.net.*;
import com.android.volley.toolbox.*;
import com.android.volley.RequestQueue;
import java.util.ArrayList;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import info.dublinbikes.bikes.bikeAdapter;
import info.dublinbikes.bikes.bikes;
import java.util.List;

/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/
public class TwoFragment extends Fragment{

    ListView fruitsList;
    private RecyclerView mList;
    String url = "https://quentin-dommerc.com/dublinbikes/dublin.json";
    ProgressDialog dialog;
    private List<bikes> bikeList=new ArrayList<>();
    private bikeAdapter bikeadapter;

  public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1= inflater.inflate(R.layout.fragment_two, container, false);
        //bikeList = new ArrayList<>();

        String output=loadJsonFromAsset();
       // Toast.makeText(getActivity().getApplicationContext(),output,Toast.LENGTH_SHORT).show();

        try {
            JSONArray object = new JSONArray(loadJsonFromAsset());

            for (int i = 0; i < object.length(); i++) {
                JSONObject object1 = (JSONObject) object.get(i);
                String name1=(String)object1.get("name");
                // Toast.makeText(getActivity().getApplicationContext(),name1,Toast.LENGTH_SHORT).show();
                JSONObject object2 = (JSONObject) object1.get("extra");
                String address=(String) object2.get("address");

                String id=(String) object1.get("id");
                bikes bike = new bikes();
                bike.setname(name1);
                bike.setid("ID : "+id);
                bike.setAddress("Location : "+address);
                int slots=(int)object1.get("empty_slots");
                int free_bikes=(int)object1.get("free_bikes");


                double lat=(double)object1.get("latitude");
                double lon=(double)object1.get("longitude");
                bike.setslots(slots);
                bike.setfree_bikes(free_bikes);
                bike.setlat(lat);
                bike.setlan(lon);
                bikeList.add(bike);

            } } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    JSONArray object = new JSONArray(loadJsonFromAsset());

                    for (int i = 0; i < object.length(); i++) {
                        JSONObject object1 = (JSONObject) object.get(i);
                        String name1=(String)object1.get("name");
                      // Toast.makeText(getActivity().getApplicationContext(),name1,Toast.LENGTH_SHORT).show();
                        JSONObject object2 = (JSONObject) object1.get("extra");
                       String address=(String) object2.get("address");

                       String id=(String) object1.get("id");
                        bikes bike = new bikes();
                        bike.setname(name1);
                        bike.setid(id);
                        bike.setAddress(address);
                        bikeList.add(bike);

                    } } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity().getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
        getData();

        */

        bikeadapter = new bikeAdapter(getActivity(), 0, bikeList);
        ListView listView = (ListView) v1.findViewById(R.id.names_list_view);
        listView.setAdapter(bikeadapter);
        return v1;
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {

                    // JSONObject object = new JSONObject(jsonString);

                    // JSONArray fruitsArray = object.getJSONArray("");

                    JSONArray object = new JSONArray(string);

                    for (int i = 0; i < object.length(); i++) {
                        JSONObject object1 = (JSONObject) object.get(i);
                       String station_name=(String) object1.get("name");

                        JSONObject object2 = (JSONObject) object1.get("extra");
                        String address=(String)object2.get("address");

                       String ID=(String) object1.get("id");
                        bikes bike = new bikes();
                        bike.setname(station_name);
                        bike.setid(ID);
                        bike.setAddress(address);
                        bikeList.add(bike);
                    }

                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity().getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    public String loadJsonFromAsset(){

        String json = "";
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





