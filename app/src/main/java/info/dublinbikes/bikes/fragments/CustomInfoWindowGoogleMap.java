package info.androidhive.materialtabs.fragments;

/**
 * Created by Ananth on 27-04-2018.
 */
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.bikes;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.map_custom_infowindow, null);

        TextView name_tv = (TextView) view.findViewById(R.id.name);
        TextView details_tv = (TextView)view.findViewById(R.id.details);


        TextView hotel_tv = (TextView)view.findViewById(R.id.hotels);
        TextView food_tv = (TextView)view.findViewById(R.id.food);
        TextView transport_tv =(TextView) view.findViewById(R.id.transport);

        name_tv.setText(marker.getTitle());
        details_tv.setText(marker.getSnippet());

        bikes infoWindowData = (bikes) marker.getTag();
       hotel_tv.setText(infoWindowData.getslots());
        food_tv.setText(infoWindowData.getfree_bikes());
        transport_tv.setText(infoWindowData.getaddress());

        return view;
    }
}

