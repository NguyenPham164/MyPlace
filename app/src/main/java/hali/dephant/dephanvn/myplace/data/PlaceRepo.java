package hali.dephant.dephanvn.myplace.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hali.dephant.dephanvn.myplace.data.model.Category;
import hali.dephant.dephanvn.myplace.data.model.Place;

public class PlaceRepo {
    private PlaceSqLiteHelper placeSqliteHelper;
    private static PlaceRepo INSTANCE;

    public PlaceRepo(Context context){
        placeSqliteHelper = new PlaceSqLiteHelper(context);

    }
    public static PlaceRepo getINSTANCE(Context context){
        return (INSTANCE == null) ? new PlaceRepo(context) : INSTANCE;
    }

    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();

        SQLiteDatabase database = placeSqliteHelper.getReadableDatabase();

        String[] columns = {
                DBUtitls.COLUMN_CATEGORY_ID,
                DBUtitls.COLUMN_CATEGORY_NAME
        };

        Cursor cursor = database.query(DBUtitls.CATEGORY_TBL_NAME, columns, null, null, null, null, null);
        if(cursor != null && cursor.getCount()>0){
            while(cursor.moveToNext()){
                String categoryID = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_CATEGORY_ID));
                String categoryName = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_CATEGORY_NAME));

                categories.add(new Category(categoryID, categoryName));
            }
        }

        if(cursor != null){
            cursor.close();
        }

        database.close();

        return categories;
    }

    public List<Place> getPlaces(String cateID){
        List<Place> places = new ArrayList<>();

        SQLiteDatabase database = placeSqliteHelper.getReadableDatabase();

        String columns[] = {
                DBUtitls.COLUMN_PLACE_ID,
                DBUtitls.COLUMN_PLACE_CATEGORY_ID,
                DBUtitls.COLUMN_PLACE_NAME,
                DBUtitls.COLUMN_PLACE_ADDRESS,
                DBUtitls.COLUMN_PLACE_DESCRIPTION,
                DBUtitls.COLUMN_PLACE_IMAGE,
                DBUtitls.COLUMN_PLACE_LAT,
                DBUtitls.COLUMN_PLACE_LNG,
        };

        String selection = DBUtitls.COLUMN_PLACE_CATEGORY_ID + " = ?";
        String[] selectionArgs = {cateID};

        Cursor cursor = database.query(DBUtitls.PLACE_TBL_NAME, columns, selection, selectionArgs, null, null, null);

        if(cursor != null && cursor.getCount()>0){
            while(cursor.moveToNext()){

                String placeID = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_ID));
                String categoryID = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_CATEGORY_ID));
                String placeName = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_NAME));
                String placeAddress = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_ADDRESS));
                String placeDescription = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_DESCRIPTION));
                byte[] placeImage = cursor.getBlob(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_IMAGE));
                double placeLat = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_LAT));
                double placeLng = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_LNG));

                Place place = new Place.Builder()
                        .setPlaceID(placeID)
                        .setCategoryID(categoryID)
                        .setPlaceName(placeName)
                        .setPlaceAccess(placeAddress)
                        .setPlaceDecription(placeDescription)
                        .setPlaceImage(placeImage)
                        .setPlaceLat(placeLat)
                        .setPlaceLng(placeLng)
                        .build();
                places.add(place);
            }
        }

        if(cursor != null){
            cursor.close();
        }

        database.close();

        return places;
    }

    public Place getPlace(String cateID, String plID){
        Place place = null;

        SQLiteDatabase database = placeSqliteHelper.getReadableDatabase();

        String columns[] = {
                DBUtitls.COLUMN_PLACE_ID,
                DBUtitls.COLUMN_PLACE_CATEGORY_ID,
                DBUtitls.COLUMN_PLACE_NAME,
                DBUtitls.COLUMN_PLACE_ADDRESS,
                DBUtitls.COLUMN_PLACE_DESCRIPTION,
                DBUtitls.COLUMN_PLACE_IMAGE,
                DBUtitls.COLUMN_PLACE_LAT,
                DBUtitls.COLUMN_PLACE_LNG,
        };

        String selection = DBUtitls.COLUMN_PLACE_ID + " = ?" + "AND" + DBUtitls.COLUMN_PLACE_CATEGORY_ID + " = ?";
        String[] selectionArgs = {plID, cateID};

        Cursor cursor = database.query(DBUtitls.PLACE_TBL_NAME, columns, selection, selectionArgs, null, null, null);

        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();

            String placeID = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_ID));
            String categoryID = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_CATEGORY_ID));
            String placeName = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_NAME));
            String placeAddress = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_ADDRESS));
            String placeDescription = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_DESCRIPTION));
            byte[] placeImage = cursor.getBlob(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_IMAGE));
            double placeLat = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_LAT));
            double placeLng = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUtitls.COLUMN_PLACE_LNG));

            place = new Place.Builder()
                    .setPlaceID(placeID)
                    .setCategoryID(categoryID)
                    .setPlaceName(placeName)
                    .setPlaceAccess(placeAddress)
                    .setPlaceDecription(placeDescription)
                    .setPlaceImage(placeImage)
                    .setPlaceLat(placeLat)
                    .setPlaceLng(placeLng)
                    .build();

        }

        if(cursor != null){
            cursor.close();
        }

        database.close();


        return place;
    }

    public void insert(Place place){
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtitls.COLUMN_PLACE_ID, place.getPlaceID());
        contentValues.put(DBUtitls.COLUMN_PLACE_CATEGORY_ID, place.getCategoryID());
        contentValues.put(DBUtitls.COLUMN_PLACE_NAME, place.getPlaceName());
        contentValues.put(DBUtitls.COLUMN_PLACE_ADDRESS, place.getPlaceAccess());
        contentValues.put(DBUtitls.COLUMN_PLACE_DESCRIPTION, place.getPlaceDecription());
        contentValues.put(DBUtitls.COLUMN_PLACE_IMAGE, place.getPlaceImage());
        contentValues.put(DBUtitls.COLUMN_PLACE_LAT, place.getPlaceLat());
        contentValues.put(DBUtitls.COLUMN_PLACE_LNG, place.getPlaceLng());

        database.insert(DBUtitls.PLACE_TBL_NAME, null, contentValues);
        database.close();
    }

    public void update(Place place){
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtitls.COLUMN_PLACE_ID, place.getPlaceID());
        contentValues.put(DBUtitls.COLUMN_PLACE_CATEGORY_ID, place.getCategoryID());
        contentValues.put(DBUtitls.COLUMN_PLACE_NAME, place.getPlaceName());
        contentValues.put(DBUtitls.COLUMN_PLACE_ADDRESS, place.getPlaceAccess());
        contentValues.put(DBUtitls.COLUMN_PLACE_DESCRIPTION, place.getPlaceDecription());
        contentValues.put(DBUtitls.COLUMN_PLACE_IMAGE, place.getPlaceImage());
        contentValues.put(DBUtitls.COLUMN_PLACE_LAT, place.getPlaceLat());
        contentValues.put(DBUtitls.COLUMN_PLACE_LNG, place.getPlaceLng());

        String selection = DBUtitls.COLUMN_PLACE_ID + " = ?";
        String[] selectionArgs = {place.getPlaceID()};

        database.update(DBUtitls.PLACE_TBL_NAME, contentValues, selection, selectionArgs);
        database.close();
    }

    public void delete(String plID){
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();

        String selection = DBUtitls.COLUMN_PLACE_ID + " = ?";
        String[] selectionArgs ={plID};

        database.delete(DBUtitls.PLACE_TBL_NAME, selection, selectionArgs);
        database.close();
    }

}
