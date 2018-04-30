package info.dublinbikes.bikes.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.dublinbikes.bikes.R;
import info.dublinbikes.bikes.bikeAdapter;
import info.dublinbikes.bikes.bikes;
import android.support.v7.widget.LinearLayoutManager;
/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/

public class ThreeFragment extends Fragment{
    private RecyclerView mList;
    String url = "https://quentin-dommerc.com/dublinbikes/dublin.json";
    ProgressDialog dialog;
    private List<bikes> bikeList=new ArrayList<>();
    private bikeAdapter bikeadapter;

    private LinearLayoutManager linearLayoutManager;
    public ThreeFragment() {
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
        View v1= inflater.inflate(R.layout.fragment_three, container, false);
        mList = (RecyclerView) v1.findViewById(R.id.main_list);

        return v1;
    }

}
