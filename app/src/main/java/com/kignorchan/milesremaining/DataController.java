package com.kignorchan.milesremaining;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    //this method is to get two date difference
    public static Integer dateDiff(Date startDate, Date endDate){
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String resultString = ""+elapsedDays;

        Integer result = Integer.parseInt(resultString);
        return result;
    }

    //parse data from String type to Date type
    public static Date parseStringToDate(String dateInString)throws Exception{


        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        //String cDateInString = sdf.format(dateInString).toString();
        Date currentDate = sdf.parse(dateInString);
        return currentDate;
    }

    //method to get current date
    public static Date getCurrentDate()throws Exception{

        //get current date and time
        Calendar calender = Calendar.getInstance();
        int cDay = calender.get(Calendar.DAY_OF_MONTH);
        int cMonth = calender.get(Calendar.MONTH) + 1;
        int cYear = calender.get(Calendar.YEAR);
        int cHour = calender.get(Calendar.HOUR);
        int cMinute = calender.get(Calendar.MINUTE);
        int cSecond = calender.get(Calendar.SECOND);

        //String cDateTime = ""+cDay+"/"+cMonth+"/"+cYear+" "+cHour+":"+cMinute+":"+cSecond;
        //String cDateInString = ""+cYear+"-"+cMonth+"-"+cDay;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        String cDateInString = sdf.format(calender.getTime()).toString();
        Date currentDate = sdf.parse(cDateInString);

        return currentDate;
    }

    //Json->Carlease obj
    public static CarLease parseJsonToCarleaseObject(String json)throws Exception{
        JSONObject obj = new JSONObject(json);
        CarLease lease = new CarLease();

        lease.setLeaseId(obj.getString("id"));
        lease.setLeaseTitle(obj.getString("title"));
        lease.setLeaseDate(obj.getString("startDate"));
        lease.setPeriod(obj.getString("period"));
        lease.setLimitedMiles(obj.getString("limitedMiles"));
        lease.setOverpayPerMile(obj.getString("overPayPerMile"));
        lease.setDistanceUnit(obj.getString("distanceUnit"));

        return lease;
    }

    //Carlease obj -> Json
    public static JSONObject parseObjectToJsonString(CarLease lease)throws Exception{
        JSONObject obj = new JSONObject();

        obj.put("id", lease.getLeaseId());
        obj.put("title", lease.getLeaseTitle());
        obj.put("startDate", lease.getLeaseDate());
        obj.put("period", lease.getPeriod());
        obj.put("limitedMiles", lease.getLimitedMiles());
        obj.put("overPayPerMile", lease.getOverpayPerMile());
        obj.put("distanceUnit", lease.getDistanceUnit());

        return obj;
    }
}
