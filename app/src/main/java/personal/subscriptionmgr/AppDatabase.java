package personal.subscriptionmgr;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Liz on 9/2/2018.
 */

@Database(entities = {Subscription.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SubscriptionDao subscriptionDao();
}