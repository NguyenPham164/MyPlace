package hali.dephant.dephanvn.myplace.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import hali.dephant.dephanvn.myplace.ActivityUtils;
import hali.dephant.dephanvn.myplace.R;
import hali.dephant.dephanvn.myplace.data.PlaceRepo;
import hali.dephant.dephanvn.myplace.data.model.Place;

public class AddEditActivity extends AppCompatActivity {

    ImageView imgPlacePicture;
    EditText edtPlaceName, edtPlaceAddress, edtPlaceDescription;
    Button btnSavePlace;

    private String placeID;
    private String categoryID;
    private PlaceRepo placeRepo;
    private boolean hasImage = false;
    private boolean allowSave = false;

    private static final int IMAGE_CAPTURE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        mapping();

        init();



        btnSavePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeName = edtPlaceName.getText().toString();
                String placeAddress = edtPlaceAddress.getText().toString();
                String placeDescription = edtPlaceDescription.getText().toString();

                if (Place.validateInput(placeName, placeAddress, placeDescription, categoryID)) {
                    allowSave = true;
                } else {
                    Toast.makeText(AddEditActivity.this, "Pleace fill in place's information", Toast.LENGTH_SHORT).show();
                }
                if (allowSave) {
                    if (hasImage && placeID == null ) {
                        Toast.makeText(getApplicationContext(), "Add new", Toast.LENGTH_SHORT).show();
                    }
                    if (placeID != null){
                        Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgPlacePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, IMAGE_CAPTURE_REQUEST_CODE);
            }
        });

    }

    private void mapping(){
        imgPlacePicture = findViewById(R.id.imgAddEditAct_PlacePicture);
        edtPlaceName = findViewById(R.id.edtAddEditAct_PlaceName);
        edtPlaceAddress = findViewById(R.id.edtAddEditAct_PlaceAddress);
        edtPlaceDescription = findViewById(R.id.edtAddEditAct_PlaceDescription);
        btnSavePlace = findViewById(R.id.btnAddEditAct_Save);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_CAPTURE_REQUEST_CODE && resultCode == RESULT_OK){
            if(data == null){
                if(placeID == null){
                    hasImage = false;
                    allowSave = false;
                }
            }else{
                hasImage = true;
                allowSave = true;
                Bitmap placeImage = (Bitmap) data.getExtras().get("data");
                imgPlacePicture.setImageBitmap(placeImage);
            }
        }
    }

    private void init(){
        placeID = getIntent().getStringExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA);
        categoryID = getIntent().getStringExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA);
        placeRepo = PlaceRepo.getINSTANCE(this);

        if (placeID != null){
            hasImage = true;
        }

    }

    private void OnClick() {

    }
    private void OnClick2(){

    }
}
