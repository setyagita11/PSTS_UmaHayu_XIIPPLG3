package com.octariani.psts_umahayu_xiipplg3.db

import androidx.room.*

@Dao
interface pesananDao {

    @Insert
    fun insertData (tbPesanan: TB_PESANAN)

    @Delete
    fun hapusData (tbPesanan: TB_PESANAN)

    @Update
    fun ubahData (tbPesanan: TB_PESANAN)

    @Query ("SELECT * FROM 'tb_Pesanan'")
    fun getAll() :List<TB_PESANAN>

    @Query ("SELECT * FROM tb_pesanan WHERE kodePesanan=:kodepesanan")
    fun getUpdate (kodepesanan: Int) :List<TB_PESANAN>

    }