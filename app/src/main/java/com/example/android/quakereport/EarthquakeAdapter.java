package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakeArrayList) {
        super(context, 0, earthquakeArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getMagnitude());
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitude.setText(output);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String currentLocation = currentEarthquake.getPlace();
        String placeString = "";
        String offsetString = "";
        TextView place = (TextView) listItemView.findViewById(R.id.place);
        TextView offset = (TextView) listItemView.findViewById(R.id.offset);
        if(currentLocation.contains(LOCATION_SEPARATOR)){
            String [] locationSplit = currentLocation.split(LOCATION_SEPARATOR);
            offsetString += locationSplit[0] + LOCATION_SEPARATOR;
            placeString += locationSplit[1];
        } else{
            placeString = currentLocation;
            offsetString = getContext().getString(R.string.near_the);
        }
        place.setText(placeString);
        offset.setText(offsetString);

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Date currentDate =currentEarthquake.getDate();
        date.setText(dateFormat.format(currentDate));

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        time.setText(timeFormat.format(currentDate));

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
