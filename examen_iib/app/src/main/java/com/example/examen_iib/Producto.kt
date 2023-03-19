package com.example.examen_iib

import android.os.Parcel
import android.os.Parcelable

class Producto (
    var idProducto: String,
    var idCliente: String,
    var nombreProducto: String,
    var marcaProducto: String,
    var precioProducto: Int

): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!
    ){

    }

    override fun toString(): String {
        return "${nombreProducto} - ${marcaProducto} - ${precioProducto}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idProducto)
        parcel.writeString(idCliente)
        parcel.writeString(nombreProducto)
        parcel.writeString(marcaProducto)
        parcel.writeInt(precioProducto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}