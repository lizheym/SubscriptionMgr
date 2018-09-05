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

    // only applicable for annual subscriptions - otherwise will be 0
    @ColumnInfo(name = "charge_month")
    private String chargeMonth;

    // 1 to 31 (if weekly, value is 0)
    @ColumnInfo(name = "charge_day_of_month")
    private int chargeDayOfMonth;

    // Sunday .. Saturday (if annual or monthly, value is "0")
    @ColumnInfo(name = "charge_day_of_week")
    private String chargeDayOfWeek;

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

    public String getChargeMonth() {
        return chargeMonth;
    }

    public void setChargeMonth(String chargeMonth) {
        this.chargeMonth = chargeMonth;
    }

    public int getChargeDayOfMonth() {
        return chargeDayOfMonth;
    }

    public void setChargeDayOfMonth(int chargeDayOfMonth) {
        this.chargeDayOfMonth = chargeDayOfMonth;
    }

    public String getChargeDayOfWeek() {
        return chargeDayOfWeek;
    }

    public void setChargeDayOfWeek(String chargeDayOfWeek) {
        this.chargeDayOfWeek = chargeDayOfWeek;
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
