package com.violet.smon.Data.DAO;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import com.violet.smon.Data.Model.AuthData;

import java.util.List;

@Dao
public interface AuthDAO {
    @Query("SELECT * FROM auth_data LIMIT 1")
    AuthData getAuth();
    @Insert
    void insert(AuthData... authData);
    @Query("DELETE FROM auth_data")
    void resetTable();
}
