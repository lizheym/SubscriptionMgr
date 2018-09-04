package personal.subscriptionmgr;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Liz on 9/2/2018.
 */

@Dao
public interface SubscriptionDao {
    @Query("SELECT * FROM subscription")
    List<Subscription> getAll();

    @Query("SELECT * FROM subscription WHERE name LIKE :nameProvided")
    Subscription findByName(String nameProvided);

    @Insert
    void insertAll(Subscription... subs);

    @Delete
    void delete(Subscription subs);
}