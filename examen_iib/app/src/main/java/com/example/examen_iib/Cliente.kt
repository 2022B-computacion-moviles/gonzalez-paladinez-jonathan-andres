package com.example.examen_iib

import android.os.Parcel
import android.os.Parcelable

class Cliente (
    var idCliente: String,
    var nombreCliente: String,
    var edadCliente: Int,
    var telefonoCliente: String,
    var correoCliente: String,
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "${nombreCliente} - ${edadCliente} - ${telefonoCliente} - ${correoCliente}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idCliente)
        parcel.writeString(nombreCliente)
        parcel.writeInt(edadCliente)
        parcel.writeString(telefonoCliente)
        parcel.writeString(correoCliente)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }

    fun getID(): String{
        return idCliente
    }

}
