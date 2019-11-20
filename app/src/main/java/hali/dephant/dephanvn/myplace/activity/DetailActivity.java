package hali.dephant.dephanvn.myplace.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.UUID;

import hali.dephant.dephanvn.myplace.ActivityUtils;
import hali.dephant.dephanvn.myplace.R;
import hali.dephant.dephanvn.myplace.data.PlaceRepo;
import hali.dephant.dephanvn.myplace.data.model.Place;

public class DetailActivity extends AppCompatActivity {


    ImageView imgPlacePicture;
    ImageButton iBtnDescription, iBtnEdit, iBtnDelete;
    EditText edtPlaceName, edtPlaceAddress, edtPlaceDescription;

    private String placeID;
    private String categoryID;
    private PlaceRepo placeRepo;
    private Place place;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mapping();
        onClick();
        init();

    }

    private void init(){
        placeID = getIntent().getStringExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA);
        categoryID = getIntent().getStringExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA);
        placeRepo = PlaceRepo.getINSTANCE(this);
        initProgressDialog();
        setPlace();
    }

    private void setPlace(){

        place = placeRepo.getPlace(categoryID, placeID);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                if(place.getPlaceImage()!= null){
                    Bitmap placeBitmap = BitmapFactory.decodeByteArray(place.getPlaceImage(), 0, place.getPlaceImage().length);
                    imgPlacePicture.setImageBitmap(placeBitmap);

                }
                edtPlaceName.setText(place.getPlaceName());
                edtPlaceAddress.setText(place.getPlaceAccess());
                edtPlaceDescription.setText(place.getPlaceDecription());


            }
        }, 50);

    }
    private void initProgressDialog(){
        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.text_retrieving_data));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    private void mapping(){
        imgPlacePicture = findViewById(R.id.imgDetailAct_PlacePicture);
        iBtnDescription = findViewById(R.id.iBtnDetailAct_Description);
        iBtnEdit = findViewById(R.id.iBtnDetailAct_Edit);
        iBtnDelete = findViewById(R.id.iBtnDetailAct_Delete);
        edtPlaceName = findViewById(R.id.edtAddEditAct_PlaceName);
        edtPlaceAddress = findViewById(R.id.edtDetailAct_PlaceAddress);
        edtPlaceDescription = findViewById(R.id.edtDetailAct_PlaceDescription);
    }

    private void onClick(){

        iBtnDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        iBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addEditActIntent = new Intent(DetailActivity.this, AddEditActivity.class);
                addEditActIntent.putExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA, place.getPlaceID());
                addEditActIntent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA, place.getCategoryID());
                startActivity(addEditActIntent);
            }
        });

        iBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
                alertDialog.setTitle(getResources().getString(R.string.text_warning));
                alertDialog.setIcon(R.mipmap.warning);
                alertDialog.setMessage(getResources().getString(R.string.warning_do_you_want_to_delete)
                        + " '"
                        + place.getPlaceName()
                        + "' ?"
                        );

                alertDialog.setPositiveButton(getResources().getString(R.string.text_positive),
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
                        placeRepo.delete(placeID);
                    }
                });

                alertDialog.setNegativeButton(getResources().getString(R.string.text_negative), new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}
