package com.octariani.psts_umahayu_xiipplg3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.octariani.psts_umahayu_xiipplg3.databinding.ActivityInputDataBinding
import com.octariani.psts_umahayu_xiipplg3.db.DB_TOKOBATIK
import com.octariani.psts_umahayu_xiipplg3.db.TB_PESANAN

class inputData : AppCompatActivity() {

    private lateinit var binding: ActivityInputDataBinding
    private lateinit var adapter: dataAdapter
    private lateinit var database: DB_TOKOBATIK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_input_data)

        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DB_TOKOBATIK.getInstances(applicationContext)
        binding.btnSimpan.setOnClickListener {

            if (binding.txtJenis.text.isNotEmpty() &&
                binding.txtNama.text.isNotEmpty() &&
                binding.txtHarga.text.isNotEmpty() &&
                binding.txtKuantiti.text.isNotEmpty()
            ){

           database.pesanDAO().insertData(
               TB_PESANAN(0,
                        binding.txtJenis.text.toString(),
                   binding.txtNama.text.toString(),
                   binding.txtHarga.text.toString().toInt(),
                   binding.txtKuantiti.text.toString().toInt()
               )
           )
                binding.txtJenis.setText("")
                binding.txtNama.setText("")
                binding.txtHarga.setText("")
                binding.txtKuantiti.setText("")

                startActivity(Intent(
                    this,MainActivity::class.java)
                )

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