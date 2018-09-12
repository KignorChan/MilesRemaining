package com.kignorchan.milesremaining;

public class CarLease {
    private String leaseId;
    private String leaseTitle;
    private String leaseDate;
    private String period;
    private String limitedMiles;
    private String overpayPerMile;
    private String distanceUnit;

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public String getLeaseTitle() {
        return leaseTitle;
    }

    public void setLeaseTitle(String leaseTitle) {
        this.leaseTitle = leaseTitle;
    }

    public String getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(String leaseDate) {
        this.leaseDate = leaseDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getLimitedMiles() {
        return limitedMiles;
    }

    public void setLimitedMiles(String limitedMiles) {
        this.limitedMiles = limitedMiles;
    }

    public String getOverpayPerMile() {
        return overpayPerMile;
    }

    public void setOverpayPerMile(String overpayPerMile) {
        this.overpayPerMile = overpayPerMile;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }
}
