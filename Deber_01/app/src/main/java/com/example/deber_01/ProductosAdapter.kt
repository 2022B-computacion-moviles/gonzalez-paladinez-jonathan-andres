package com.example.deber_01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ProductosAdapter(
    contexto:Context,
    datos:ArrayList<BProducto>
): BaseAdapter() {
    val datosProductos = datos
    val productos = contexto

    inner class ViewHolder(){
        var nombre: TextView?=null
    }

    override fun getView(position: Int, row: View?, parent: ViewGroup?): View? {

        var rowview = row
        var viewHolder: ViewHolder

        if (rowview == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(productos)
            rowview = inflater.inflate(R.layout.item_cliente, parent, false)

            viewHolder.nombre = rowview.findViewById(R.id.row_nombre) as TextView
            rowview.tag = viewHolder
        } else {
            viewHolder = rowview.tag as ViewHolder
        }
        viewHolder.nombre!!.setText(datosProductos.get(position).nombreProducto)

        return rowview
    }


    override fun getCount(): Int {
        return datosProductos.size
    }

    override fun getItem(p0: Int): Any {
        return datosProductos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
}