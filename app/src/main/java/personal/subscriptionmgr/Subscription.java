package personal.subscriptionmgr;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Liz on 9/2/2018.
 */

@Entity
public class Subscription {
    @PrimaryKey
    @NonNull
    private String name;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "frequency")
    private String frequency;

    //only applicable for annual subscriptions - otherwise will be 0
    @ColumnInfo(name = "charge_month")
    private int chargeMonth;

    //day of the week 1-7 for weekly or day of the month for monthly/annual
    //if daily subscription, value is 0
    @ColumnInfo(name = "charge_day")
    private int chargeDay;

    @ColumnInfo(name = "cost")
    private double cost;

    @ColumnInfo(name = "notification")
    private int notification;

    @ColumnInfo(name = "email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getChargeMonth() {
        return chargeMonth;
    }

    public void setChargeMonth(int chargeMonth) {
        this.chargeMonth = chargeMonth;
    }

    public int getChargeDay() {
        return chargeDay;
    }

    public void setChargeDay(int chargeDay) {
        this.chargeDay = chargeDay;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
