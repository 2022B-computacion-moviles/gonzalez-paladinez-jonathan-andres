import android.os.Parcel
import android.os.Parcelable

class BCliente (
    var idCliente: Int,
    var nombreCliente: String,
    var edadCliente: Int,
    var telefonoCliente: String,
    var correoCliente: String,
): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
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
        parcel.writeInt(idCliente)
        parcel.writeString(nombreCliente)
        parcel.writeInt(edadCliente)
        parcel.writeString(telefonoCliente)
        parcel.writeString(correoCliente)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BCliente> {
        override fun createFromParcel(parcel: Parcel): BCliente {
            return BCliente(parcel)
        }

        override fun newArray(size: Int): Array<BCliente?> {
            return arrayOfNulls(size)
        }
    }

    fun getID(): Int{
        return idCliente
    }

}
