package com.example.deber_01

import android.os.Parcel
import android.os.Parcelable

class BProducto(
    var idProducto: Int,
    var idCliente: Int,
    var nombreProducto: String,
    var marcaProducto: String,
    var precioProducto: Int

):Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!
    ){

    }

    override fun toString(): String {
        return "${nombreProducto} - ${marcaProducto} - ${precioProducto}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idProducto)
        parcel.writeInt(idCliente)
        parcel.writeString(nombreProducto)
        parcel.writeString(marcaProducto)
        parcel.writeInt(precioProducto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BProducto> {
        override fun createFromParcel(parcel: Parcel): BProducto {
            return BProducto(parcel)
        }

        override fun newArray(size: Int): Array<BProducto?> {
            return arrayOfNulls(size)
        }
    }
}