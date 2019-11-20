package hali.dephant.dephanvn.myplace.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import hali.dephant.dephanvn.myplace.R;
import hali.dephant.dephanvn.myplace.data.model.Place;

public class PlacesAdapter extends BaseAdapter {


    private Context context;
    private List<Place> places;

    public PlacesAdapter(Context context, List<Place> places) {
        this.context = context;
        setPlaces(places);
    }

    private void setPlaces(List<Place> places){
        this.places = places;
    }

    public void updatePlaces(List<Place> places){
        this.places = places;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PlaceViewHolder viewHolder = new PlaceViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_place, parent, false);
            viewHolder.imgPlace = convertView.findViewById(R.id.imgItemPlace_Picture);
            viewHolder.txtPlaceName = convertView.findViewById(R.id.txtItemPlace_PlaceName);
            viewHolder.txtPlaceAddress = convertView.findViewById(R.id.txtItemPlace_PlaceAddress);
            viewHolder.txtPlaceDecription = convertView.findViewById(R.id.txtItemPlace_PlaceDecription);

            convertView.setTag(viewHolder);

        }

        viewHolder = (PlaceViewHolder) convertView.getTag();

        Place place = (Place) getItem(position);

        if(place.getPlaceImage() != null){
            Bitmap placeBitmap = BitmapFactory.decodeByteArray(place.getPlaceImage(), 0, place.getPlaceImage().length);
            viewHolder.imgPlace.setImageBitmap(placeBitmap);


        }

        viewHolder.txtPlaceName.setText(place.getPlaceName());
        viewHolder.txtPlaceAddress.setText(place.getPlaceAccess());
        viewHolder.txtPlaceDecription.setText(place.getPlaceDecription());

        return convertView;
    }


}
