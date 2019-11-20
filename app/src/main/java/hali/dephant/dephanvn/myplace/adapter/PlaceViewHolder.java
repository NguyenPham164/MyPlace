package hali.dephant.dephanvn.myplace.adapter;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import hali.dephant.dephanvn.myplace.R;

public class PlaceViewHolder extends AppCompatActivity {

    ImageView imgPlace;
    TextView txtPlaceName;
    TextView txtPlaceAddress;
    TextView txtPlaceDecription;

    public void mapping(){
        imgPlace = findViewById(R.id.imgItemPlace_Picture);
        txtPlaceName = findViewById(R.id.txtItemPlace_PlaceName);
        txtPlaceAddress = findViewById(R.id.txtItemPlace_PlaceAddress);
        txtPlaceDecription = findViewById(R.id.txtItemPlace_PlaceDecription);

    }


}
