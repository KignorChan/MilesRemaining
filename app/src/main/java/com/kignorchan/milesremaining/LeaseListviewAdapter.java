package com.kignorchan.milesremaining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LeaseListviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CarLease> carLeases;

    public LeaseListviewAdapter(Context context, ArrayList<CarLease> carLeases) {
        this.context = context;
        this.carLeases = carLeases;
    }

    @Override
    public int getCount() {
        return carLeases.size();
    }

    @Override
    public CarLease getItem(int i) {
        return carLeases.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate( R.layout.listview_carlease, viewGroup, false );
        final String _leaseTitle = getItem(i).getLeaseTitle();
        final String _leasePeriod = getItem(i).getPeriod();


        TextView leaseTitle = (TextView)v.findViewById(R.id.listview_carlease_title);
        TextView leasePeriod = (TextView)v.findViewById(R.id.listview_carlease_period);

        leaseTitle.setText(_leaseTitle);
        leasePeriod.setText(_leasePeriod);

        return v;
    }
}
