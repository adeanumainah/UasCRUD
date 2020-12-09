package com.dean.uascrud.remote;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL =
            "http://192.168.42.45/absensi/index.php/";
    public static ProductServices getProductServices(){
        return RetrofitClient.getClient(API_URL).create(ProductServices.class);
    }
}
