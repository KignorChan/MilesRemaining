package com.kignorchan.milesremaining;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddNewLeaseActivity extends AppCompatActivity {

    private EditText leaseTitle;
    private EditText leaseDate;
    private EditText period;
    private EditText limitedMiles;
    private EditText overPayPerMile;
    private String _unitChoosed;

    private ImageButton calendarImageBtn;
    private Spinner unitSpinner;

    private ArrayList<String> unitChoices = new ArrayList<String>();

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lease);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setTitle("New car lease");
        _unitChoosed = "km";
        initCustomSpinner();

        calendarImageBtn = (ImageButton)findViewById(R.id.new_lease_calendar_image_btn);
        calendarImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // To show current date in the datepicker

                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddNewLeaseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
                        leaseDate.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        leaseDate = (EditText)findViewById(R.id.new_lease_start_date) ;
        leaseDate.setText(getCurrentDate());
        leaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // To show current date in the datepicker

                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddNewLeaseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
                        leaseDate.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_lease, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }else if(item.getItemId()==R.id.action_comfirm_add_lease){
            //Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();

            //get info
            leaseTitle = (EditText) findViewById(R.id.new_lease_title);
            leaseDate = (EditText) findViewById(R.id.new_lease_start_date);
            period = (EditText) findViewById(R.id.new_lease_period);
            limitedMiles = (EditText) findViewById(R.id.new_lease_limit_miles);
            overPayPerMile = (EditText) findViewById(R.id.new_lease_overpay_per_mile);

            String _title = leaseTitle.getText().toString();
            //_title = _title.substring(0, 1).toUpperCase() + _title.substring(1);
            _title = toTitleCase(_title);
            String _date = leaseDate.getText().toString();
            String _period = period.getText().toString();
            String _limitedMiles = limitedMiles.getText().toString();
            String _overPayPerMile = overPayPerMile.getText().toString();

            if(TextUtils.isEmpty(_title)){
                leaseTitle.setHint("Please fill here!");
                leaseTitle.setHintTextColor(getColor(R.color.colorRed));
                leaseTitle.requestFocus();
            }else if(TextUtils.isEmpty(_date)){
                leaseDate.setHint("Please fill here!");
                leaseDate.setHintTextColor(getColor(R.color.colorRed));
                leaseDate.requestFocus();
            }else if(TextUtils.isEmpty(_period)){
                period.setHint("Please fill here!");
                period.setHintTextColor(getColor(R.color.colorRed));
                period.requestFocus();
            }else if(TextUtils.isEmpty(_limitedMiles)){
                limitedMiles.setHint("Please fill here!");
                limitedMiles.setHintTextColor(getColor(R.color.colorRed));
                limitedMiles.requestFocus();
            }else if(TextUtils.isEmpty(_overPayPerMile)){
                overPayPerMile.setHint("Please fill here!");
                overPayPerMile.setHintTextColor(getColor(R.color.colorRed));
                overPayPerMile.requestFocus();
            }else{
                CarLease newLease = new CarLease();
                newLease.setLeaseId("001");
                newLease.setLeaseTitle(_title);
                newLease.setLeaseDate(_date);
                newLease.setPeriod(_period);
                newLease.setLimitedMiles(_limitedMiles);
                newLease.setOverpayPerMile(_overPayPerMile);
                newLease.setDistanceUnit(_unitChoosed);

                Information.carLeases.add(newLease);

                JSONArray jsonArray = new JSONArray();

                for(int i=0 ; i<Information.carLeases.size(); i++){
                    try{
                        jsonArray.put(DataController.parseObjectToJsonString(Information.carLeases.get(i)));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                Log.i("JSONARRAY", jsonArray.toString());

                try{
                    DataController.storeJsonToLocal(jsonArray.toString(), Information.FILENAME, AddNewLeaseActivity.this);
                }catch (Exception e){
                    e.printStackTrace();
                }

//                Bundle bundle = new Bundle();
//                bundle.putAll(newLease);

                finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    String getCurrentDate(){
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        String cDateInString = sdf.format(calender.getTime()).toString();

        return cDateInString;
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
        private final Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }

        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(AddNewLeaseActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.LEFT);
            txt.setText(asr.get(position));
            return  txt;
        }



        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(AddNewLeaseActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            //txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(i));
            return  txt;
        }
    }

    private void initCustomSpinner() {
        //Set spinner for day number select
        Spinner spinner = (Spinner) findViewById(R.id.new_lease_unit_spinner);
        unitChoices.add("km");
        unitChoices.add("miles");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(AddNewLeaseActivity.this,unitChoices);
        spinner.setAdapter(customSpinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"You choose: "+unitChoices.get(i),Toast.LENGTH_LONG).show();
                _unitChoosed = unitChoices.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
