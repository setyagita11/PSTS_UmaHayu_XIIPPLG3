package com.octariani.psts_umahayu_xiipplg3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.octariani.psts_umahayu_xiipplg3.databinding.ActivityMainBinding
import com.octariani.psts_umahayu_xiipplg3.db.DB_TOKOBATIK
import com.octariani.psts_umahayu_xiipplg3.db.TB_PESANAN
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val db by lazy { DB_TOKOBATIK.getInstances(this) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : dataAdapter
    private lateinit var database : DB_TOKOBATIK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = dataAdapter(ArrayList(),
            object : dataAdapter.OnClickListener{
                override fun onEdit(tbPesanan: TB_PESANAN) {
                    updatePesan(tbPesanan)
                }

                override fun onHapus(tbPesanan: TB_PESANAN) {
                    deletePesanan(tbPesanan)
                }
            })
        binding.listData.adapter = adapter
        binding.listData.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        binding.listData.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this,inputData::class.java))
        }

    }

    private fun updatePesan(tbPesanan: TB_PESANAN) {
        startActivity(Intent(this,editData::class.java)
            .putExtra("kode pesanan",tbPesanan.kode_pesanan.toString()
            )
        )
    }

    private fun deletePesanan(tbPesanan: TB_PESANAN) {

        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus Data")

            setMessage("Apakah Anda yakin akan menghapus data tersebut? ${tbPesanan.nama}?")

            setNegativeButton("cancel") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }

            setPositiveButton("Hapus") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.pesanDAO().hapusData(tbPesanan)
                    finish()
                    startActivity(intent)
                }
                tampilSemuaData()
            }
            dialog.show()
        }

    }
    override fun onResume() {
        super.onResume()
        tampilSemuaData()
    }

    fun tampilSemuaData() {
        binding.listData.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.pesanDAO().getAll()
            adapter.setData(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
        binding.listData.adapter = adapter
    }
}