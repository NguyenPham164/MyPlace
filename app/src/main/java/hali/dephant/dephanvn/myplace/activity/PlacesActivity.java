package hali.dephant.dephanvn.myplace.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hali.dephant.dephanvn.myplace.ActivityUtils;
import hali.dephant.dephanvn.myplace.R;
import hali.dephant.dephanvn.myplace.adapter.PlacesAdapter;
import hali.dephant.dephanvn.myplace.data.PlaceRepo;
import hali.dephant.dephanvn.myplace.data.model.Place;

public class PlacesActivity extends AppCompatActivity {

    ListView lvPlaces;
    TextView txtNoData;
    FloatingActionButton fabAddNewPlace;
    Button btnShowPlacesOnMap;

    private String categoryID;
    private PlaceRepo placeRepo;
    private List<Place> places = new ArrayList<>();
    private PlacesAdapter placesAdapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        mapping();
        init();
    }

    private void mapping(){
        lvPlaces = findViewById(R.id.lvPlacesAct);
        txtNoData = findViewById(R.id.txtPlacesAct_NoData);
        btnShowPlacesOnMap = findViewById(R.id.btnPlaceAct_ShowAllOnMap);
        fabAddNewPlace = findViewById(R.id.fabPlaceAct_AddNewPlace);
    }

    private void init(){
        categoryID = getIntent().getStringExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA);
        placeRepo = PlaceRepo.getINSTANCE(this);
        placesAdapter = new PlacesAdapter(this, places);
        progressDialog = new ProgressDialog(PlacesActivity.this);

        initProgressDialog();

        getPlaces();
        onPlaceClick();

        fabAddNewPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addEditActIntent = new Intent(PlacesActivity.this, AddEditActivity.class);
                addEditActIntent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA, categoryID);
                startActivity(addEditActIntent);
            }
        });
        btnShowPlacesOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Show all Place", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPlaces(){
        places = placeRepo.getPlaces(categoryID);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

                if(!places.isEmpty()){
                    txtNoData.setVisibility(View.GONE);

                }
                lvPlaces.setAdapter(placesAdapter);
                placesAdapter.updatePlaces(places);

            }
        }, 50);

    }

    private void initProgressDialog(){
        progressDialog = new ProgressDialog(PlacesActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.text_retrieving_data));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


    }


    private void onPlaceClick(){
        lvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = places.get(position);
                Intent detailActIntent = new Intent(PlacesActivity.this, DetailActivity.class);
                detailActIntent.putExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA, place.getPlaceID());
                detailActIntent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA, place.getCategoryID());
                startActivity(detailActIntent);
            }
        });
    }
}
