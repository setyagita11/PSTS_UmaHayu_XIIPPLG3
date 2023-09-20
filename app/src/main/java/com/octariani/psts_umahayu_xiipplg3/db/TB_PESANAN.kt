package com.octariani.psts_umahayu_xiipplg3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_Pesanan")
data class TB_PESANAN(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "kodePesanan")
    val kode_pesanan : Int,

    @ColumnInfo (name = "Jenis")
    val jenis : String,

    @ColumnInfo (name = "Nama")
    val nama : String,

    @ColumnInfo (name = "Harga")
    val harga : Int,

    @ColumnInfo (name = "Kuantiti")
    val kuantity : Int

)
