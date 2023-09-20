package com.octariani.psts_umahayu_xiipplg3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [TB_PESANAN::class], version = 1)
abstract class DB_TOKOBATIK : RoomDatabase() {
    abstract fun pesanDAO() : pesananDao

    companion object{

        @Volatile
        private var INSTANCES : DB_TOKOBATIK? = null

        @Synchronized
        fun getInstances (context: Context) : DB_TOKOBATIK{
            if (INSTANCES==null) {
                INSTANCES = Room.databaseBuilder(context, DB_TOKOBATIK::class.java, "Toko Batik")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCES!!
        }
    }
}
