package com.octariani.psts_umahayu_xiipplg3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.octariani.psts_umahayu_xiipplg3.databinding.ActivityEditDataBinding
import com.octariani.psts_umahayu_xiipplg3.db.DB_TOKOBATIK
import com.octariani.psts_umahayu_xiipplg3.db.TB_PESANAN

class editData : AppCompatActivity() {

    private val db by lazy { DB_TOKOBATIK.getInstances(this) }
    private lateinit var binding: ActivityEditDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_edit_data)

        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kode = intent.getStringExtra("kode pesanan").toString().toInt()
        val data = db.pesanDAO().getUpdate(kode)

        binding.editJenis.setText(data[0].jenis)
        binding.editNama.setText(data[0].nama)
        binding.editHarga.setText(data[0].harga.toString())
        binding.editKuantiti.setText(data[0].kuantity.toString())

        binding.btnSimpan.setOnClickListener {
            if (binding.editJenis.text.isNotEmpty() &&
                binding.editNama.text.isNotEmpty() &&
                binding.editHarga.text.isNotEmpty() &&
                binding.editKuantiti.text.isNotEmpty()
            ){

                db.pesanDAO().ubahData(TB_PESANAN(
                    kode,
                    binding.editJenis.text.toString(),
                    binding.editNama.text.toString(),
                    binding.editHarga.text.toString().toInt(),
                    binding.editKuantiti.text.toString().toInt()
                 )
                )

                Toast.makeText(applicationContext,"Data berhasil diubah",
                    Toast.LENGTH_SHORT).show()

                startActivity(Intent(
                    this,MainActivity::class.java)
                )
                onBackPressed()
            }else{
                Toast.makeText(applicationContext,"Silahkan diisii terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun back (view: View){
        val pindah = Intent (this,MainActivity::class.java)
        startActivity(pindah)
    }

}