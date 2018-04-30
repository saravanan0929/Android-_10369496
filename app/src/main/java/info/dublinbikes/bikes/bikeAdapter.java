package info.dublinbikes.bikes;

/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.widget.ImageView;
public class bikeAdapter extends ArrayAdapter<bikes>{
    private static final String TAG = bikeAdapter.class.getSimpleName();

    List<bikes> personList=new ArrayList<>();

    public bikeAdapter(Context context, int resource, List<bikes> objects) {
        super(context, resource, objects);

        personList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        bikes currentPerson = personList.get(position);

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.station_name);
        nameTextView.setText(currentPerson.getname());

        TextView ageTextView1 = (TextView) listItemView.findViewById(R.id.ID);
        ageTextView1.setText(currentPerson.getid());

        TextView ageTextView = (TextView) listItemView.findViewById(R.id.address);
        ageTextView.setText(currentPerson.getaddress());

        TextView free_slot = (TextView) listItemView.findViewById(R.id.free_slo);
        free_slot.setText(Integer.toString(currentPerson.getfree_bikes()));

        TextView free_bike = (TextView) listItemView.findViewById(R.id.free_bik);
        free_bike.setText(Integer.toString(currentPerson.getslots()));

        final double lat=currentPerson.getlat();
        final double lon=currentPerson.getlan();
        ImageView locate = (ImageView) listItemView.findViewById(R.id.mapping);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*
                String uri=String.format(Locale.ENGLISH,"geo:%f,%f",lat,lon);
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                view.getContext().getApplicationContext().startActivity(intent);
                */

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=loc:" + lat + "," + lon));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Only if initiating from a Broadcast Receiver
                String mapsPackageName = "com.google.android.apps.maps";
               /*
                if (Utility.isPackageExisted(context, mapsPackageName)) {
                    i.setClassName(mapsPackageName, "com.google.android.maps.MapsActivity");
                    i.setPackage(mapsPackageName);
                }
                */
                getContext().startActivity(i);

            }
        });

        return listItemView;
    }
}