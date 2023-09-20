package com.octariani.psts_umahayu_xiipplg3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.octariani.psts_umahayu_xiipplg3.db.TB_PESANAN

class dataAdapter (val list: ArrayList<TB_PESANAN>, val listener : OnClickListener): RecyclerView.Adapter<dataAdapter.ViewHolder>() {
    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {

        val kodeBrg = itemView.findViewById<TextView>(R.id.inputKode)
        val jenisBrg = itemView.findViewById<TextView>(R.id.inputJenis)
        val namaBrg = itemView.findViewById<TextView>(R.id.inputNama)
        val hargaBrg = itemView.findViewById<TextView>(R.id.inputHarga)
        val kuantitiBrg = itemView.findViewById<TextView>(R.id.inputKuantiti)

        val icDelete = itemView.findViewById<ImageView>(R.id.btnDelete)
        val icEdit = itemView.findViewById<ImageView>(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).
                inflate(R.layout.adapter_data,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.kodeBrg.text = list[position].kode_pesanan.toString()
        holder.jenisBrg.text = list[position].jenis
        holder.namaBrg.text = list[position].nama
        holder.hargaBrg.text = list[position].harga.toString()
        holder.kuantitiBrg.text = list[position].kuantity.toString()

        holder.icDelete.setOnClickListener {
            listener.onHapus(list[position])
        }
        holder.icEdit.setOnClickListener{
            listener.onEdit(list[position])
    }

    }

    fun setData(newList: List<TB_PESANAN>){
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onHapus(tbPesanan: TB_PESANAN)
        fun onEdit(tbPesanan: TB_PESANAN)
    }
}