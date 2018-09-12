package com.kignorchan.milesremaining;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataController {



    //Store json string to mobile local file
    public static void storeJsonToLocal(String json, String filename, Context ctx) throws JSONException {
        //String username = Information.user.getUserName();
        //String filename = "_receipts"+".txt";

        try {
            FileOutputStream outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("STOREERROR:", e.toString());
        }
    }

    //read json from local file
    public static String readJsonFile(String filename, Context ctx){
        //String username = Information.user.getUserName();
        //String filename = "_receipts"+".txt";
        String json = "";
        try{
            FileInputStream inputStream = ctx.openFileInput(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer);
            //Toast.makeText(getApplicationContext(),json,Toast.LENGTH_LONG).show();
            return json;
        }catch (Exception e){
            Log.e("FILEREADFAIL", e.toString());
            return "null";
        }
    }
}
