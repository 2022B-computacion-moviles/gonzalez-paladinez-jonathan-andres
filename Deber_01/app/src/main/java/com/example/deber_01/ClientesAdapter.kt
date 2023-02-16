package com.example.deber_01

import BCliente
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ClientesAdapter(
    contexto: Context,
    datos: ArrayList<BCliente>
):BaseAdapter() {
    val datosCliente = datos
    val clientes = contexto

    inner class ViewHolder(){
        var nombre: TextView?= null
    }

    override fun getView(position: Int, row: View?, parent: ViewGroup?): View? {
        var rowview = row
        var viewHolder: ViewHolder

        if(rowview==null){
            viewHolder=ViewHolder()
            val inflater=LayoutInflater.from(clientes)
            rowview=inflater.inflate(R.layout.item_cliente,parent,false)

            viewHolder.nombre=rowview.findViewById(R.id.row_nombre) as TextView
            rowview.tag=viewHolder
        }else{
            viewHolder=rowview.tag as ViewHolder
        }
        viewHolder.nombre!!.setText(datosCliente.get(position).nombreCliente)

        return rowview
    }

    override fun getCount(): Int {
        return datosCliente.size
    }

    override fun getItem(p0: Int): Any {
        return datosCliente[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
}