package com.starway.starrobot.VisitingDataSQL;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//singleton   只允许生成一个实例
@Database(entities = {UserData.class},version = 1,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {
    private static UserDataBase INSTABCE;//作为一个参数
    static synchronized UserDataBase getDataBase(Context context){//synchronized 保证线程能够正常运行预防碰撞，
        if(INSTABCE==null){
            INSTABCE= Room.databaseBuilder(context.getApplicationContext(),UserDataBase.class,"UserData_DataBase")
                    .allowMainThreadQueries().build();//强制在主线程运行，并展示
        }
        return INSTABCE;
    }
    public abstract UserDao getUserDao();
}
