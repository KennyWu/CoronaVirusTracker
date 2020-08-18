package com.wu.coronavirustracker;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    public static final String COUNTRY_KEYWORD = "\"Country\":\"";
    public static final String COMFIRMED_KEYWORD = "\"TotalConfirmed\":";
    public static final String ENDING_KEYWORD = ",";
    public static final String NAME_ENDING_KEYWORKD = "\"";
    private static ArrayList<String> CountryData;


    static {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url("https://api.covid19api.com/summary")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            CountryData = new ArrayList<>(
                    Arrays.asList(response.body().string().split("}"))
            );
            removeWhiteSpace();
        } catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    private static void removeWhiteSpace() {
        for(int i = 2; i < CountryData.size(); i++) {
            if(CountryData.get(i).isEmpty()) {
                CountryData.remove(i);
            }
        }
    }


    public static int getCountryNumbers(int index) {
        String data = CountryData.get(index);
        data = data.substring(data.indexOf(COMFIRMED_KEYWORD)
                + COMFIRMED_KEYWORD.length());
        return Integer.parseInt(
                data.substring(0, data.indexOf(ENDING_KEYWORD)));
    }

    public static String getCountryName(int index) {
        String data = CountryData.get(index);
        data = data.substring(data.indexOf(COUNTRY_KEYWORD)
                + COUNTRY_KEYWORD.length());
        return data.substring(0, data.indexOf(NAME_ENDING_KEYWORKD));
    }



    public static ArrayList<String> getCountryData() {
        return CountryData;
    }
}
