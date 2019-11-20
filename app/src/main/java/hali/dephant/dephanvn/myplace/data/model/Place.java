package hali.dephant.dephanvn.myplace.data.model;

import android.text.TextUtils;

public class Place {
    private String placeID;
    private String categoryID;
    private byte[] placeImage;
    private String placeName;
    private String placeAccess;
    private String placeDecription;
    private double placeLat;
    private double placeLng;

    public Place(Builder builder){
        this.placeID = builder.placeID;
        this.categoryID = builder.categoryID;
        this.placeImage = builder.placeImage;
        this.placeName = builder.placeName;
        this.placeAccess = builder.placeAccess;
        this.placeDecription = builder.placeDecription;
        this.placeLat = builder.placeLat;
        this.placeLng = builder.placeLng;
    }

    public String getPlaceID() {
        return placeID;
    }

    public String getCategoryID() { return categoryID; }

    public byte[] getPlaceImage() {
        return placeImage;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceAccess() {
        return placeAccess;
    }

    public String getPlaceDecription() {
        return placeDecription;
    }

    public double getPlaceLat() {
        return placeLat;
    }

    public double getPlaceLng() {
        return placeLng;
    }

    public static class Builder{
        private String placeID;
        private String categoryID;
        private byte[] placeImage;
        private String placeName;
        private String placeAccess;
        private String placeDecription;
        private double placeLat;
        private double placeLng;

        public Builder setPlaceID(String placeID) {
            this.placeID = placeID;
            return this;
        }

        public Builder setCategoryID(String categoryID){
            this.categoryID = categoryID;
            return this;
        }
        public Builder setPlaceImage(byte[] placeImage) {
            this.placeImage = placeImage;
            return this;
        }

        public Builder setPlaceName(String placeName) {
            this.placeName = placeName;
            return this;
        }

        public Builder setPlaceAccess(String placeAccess) {
            this.placeAccess = placeAccess;
            return this;
        }

        public Builder setPlaceDecription(String placeDecription) {
            this.placeDecription = placeDecription;
            return this;
        }

        public Builder setPlaceLat(double placeLat) {
            this.placeLat = placeLat;
            return this;
        }

        public Builder setPlaceLng(double placeLng) {
            this.placeLng = placeLng;
            return this;
        }

        public Place build(){
            return new Place(this);
        }


    }

    public static boolean validateInput(String placeName, String placeAccess, String placeDecription, String categoryID){
        return (TextUtils.isEmpty(placeName)
                || TextUtils.isEmpty(placeAccess)
                    || TextUtils.isEmpty(placeDecription)
                        || TextUtils.isEmpty(categoryID)) ? false : true;
    }
}
