package hali.dephant.dephanvn.myplace.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hali.dephant.dephanvn.myplace.R;
import hali.dephant.dephanvn.myplace.ActivityUtils;
import hali.dephant.dephanvn.myplace.data.PlaceRepo;
import hali.dephant.dephanvn.myplace.data.model.Category;

public class CategoryActivity extends AppCompatActivity {


    private ImageView imgCategoryMoto;
    private ImageView imgCategoryCar;
    private ImageView imgCategoryFueling;
    private ImageView imgCategoryStore;

    private ConstraintLayout layoutMoto;
    private ConstraintLayout layoutCar;
    private ConstraintLayout layoutFueling;
    private ConstraintLayout layoutStore;

    private PlaceRepo placeRepo;
    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mapping();

        click();

        init();


    }

    private void init(){
        placeRepo = PlaceRepo.getINSTANCE(this);
        categories = placeRepo.getCategories();
    }

    private void mapping(){
        imgCategoryMoto =findViewById(R.id.imgCategory_moto);
        imgCategoryCar =findViewById(R.id.imgCategory_car);
        imgCategoryFueling =findViewById(R.id.imgCategory_fueling);
        imgCategoryStore =findViewById(R.id.imgCategory_store);

        layoutMoto = findViewById(R.id.layoutCategory_moto);
        layoutCar = findViewById(R.id.layoutCategory_car);
        layoutFueling = findViewById(R.id.layoutCategory_fueling);
        layoutStore = findViewById(R.id.layoutCategory_store);
    }

    private void startPlaceAct(String categoryID){

        Intent placesActIntent = new Intent(CategoryActivity.this, PlacesActivity.class);
        placesActIntent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA, categoryID);
        startActivity(placesActIntent);


    }
    private void click(){

        layoutMoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryID = categories.get(0).getCategoryID();
                startPlaceAct(categoryID);
            }
        });

        layoutCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryID = categories.get(1).getCategoryID();
                startPlaceAct(categoryID);
            }
        });

        layoutFueling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryID = categories.get(2).getCategoryID();
                startPlaceAct(categoryID);
            }
        });

        layoutStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryID = categories.get(3).getCategoryID();
                startPlaceAct(categoryID);
            }
        });

    }



}
