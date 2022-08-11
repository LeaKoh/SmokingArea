package com.example.smokingarealist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SmokeDataDao {
    @Query("SELECT * FROM CountListViewItem")
    List<CountListViewItem> getAll();

    @Insert
    void insert(CountListViewItem countListViewItem);

    @Update
    void update(CountListViewItem countListViewItem);

    @Delete
    void delete(CountListViewItem countListViewItem);

}
