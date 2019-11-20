package hali.dephant.dephanvn.myplace.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlaceSqLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "PLACE";
    private static final int DB_VERSION = 1;


    private static final String CREATE_PLACE_TBL_SQL =
            "CREATE TABLE " + DBUtitls.PLACE_TBL_NAME + "("
                    +DBUtitls.COLUMN_PLACE_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.PRIMARY_KEY + ", "
                    +DBUtitls.COLUMN_PLACE_CATEGORY_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ", "
                    +DBUtitls.COLUMN_PLACE_NAME + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ", "
                    +DBUtitls.COLUMN_PLACE_ADDRESS + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ", "
                    +DBUtitls.COLUMN_PLACE_DESCRIPTION + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ", "
                    +DBUtitls.COLUMN_PLACE_IMAGE + " " + DBUtitls.BLOB_DATA_TYPE + " " + DBUtitls.NOT_NULL + ", "
                    +DBUtitls.COLUMN_PLACE_LAT + " " + DBUtitls.REAL_DATA_TYPE + " " + DBUtitls.NOT_NULL + " , "
                    +DBUtitls.COLUMN_PLACE_LNG + " " + DBUtitls.REAL_DATA_TYPE + " " + DBUtitls.NOT_NULL

                    + ")";

    private static final String CREATE_CATEGORY_TBL_SQL =
            "CREATE TABLE " + DBUtitls.CATEGORY_TBL_NAME + "("

                    +DBUtitls.COLUMN_CATEGORY_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.PRIMARY_KEY +  ", "
                    +DBUtitls.COLUMN_CATEGORY_NAME + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL

                    + ")";


    private static final String INSERT_CATEGORY_SQL =
            "INSERT INTO " + DBUtitls.CATEGORY_TBL_NAME + "(" + DBUtitls.COLUMN_CATEGORY_ID + ", " + DBUtitls.COLUMN_CATEGORY_NAME + ")"
                    + " VALUES "
                    + "('1', 'Moto' ), " + "('2', 'Car'), " + "('3', 'Fueling'), " + "('4' , 'Store')";


    public PlaceSqLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_PLACE_TBL_SQL);
        sqLiteDatabase.execSQL(CREATE_CATEGORY_TBL_SQL);
        sqLiteDatabase.execSQL(INSERT_CATEGORY_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
