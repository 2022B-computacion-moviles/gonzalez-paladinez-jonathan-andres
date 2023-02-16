package com.example.deber_01
import BCliente
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelper(
    contexto: Context?,
): SQLiteOpenHelper(
    contexto,
    "base",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaCliente =
        """
               CREATE TABLE CLIENTE(
               idCliente INTEGER PRIMARY KEY AUTOINCREMENT,
               nombreCliente VARCHAR(50),
               edadCliente INTEGER,
               telefonoCliente VARCHAR(50),
               correoCliente VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaCliente)

        val scriptSQLCrearTablaProductos =
            """
               CREATE TABLE PRODUCTO(
               idProducto INTEGER PRIMARY KEY AUTOINCREMENT,
               idCliente INTEGER,
               nombreProducto VARCHAR(50),
               marcaProducto INTEGER,
               precioProducto INTEGER
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaProductos)

        val scriptAnadirCliente = """
            INSERT INTO CLIENTE (nombreCliente, edadCliente, telefonoCliente, correoCliente)
            VALUES ("Jonathan González", 22, "0990480225", "jona.andres99@gmail.com"),
            ("Andrés Paladinez", 32, "0990452201", "andres00@gmail.com"),
            ("Pepé Pérez", 50, "0983388669", "pepe@gmail.com");
        """.trimIndent()
        db?.execSQL(scriptAnadirCliente)

        val scriptProductoCliente = """
            INSERT INTO PRODUCTO (idCliente, nombreProducto, marcaProducto, precioProducto)
            VALUES (1, "Mouse", "HP", 10);
        """.trimIndent()
        db?.execSQL(scriptProductoCliente)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearCliente(
        nombreCliente: String,
        edadCliente: Int,
        telefonoCliente: String,
        correoCliente: String
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombreCliente", nombreCliente)
        valoresAGuardar.put("edadCliente", edadCliente)
        valoresAGuardar.put("telefonoCliente", telefonoCliente)
        valoresAGuardar.put("correoCliente", correoCliente)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "CLIENTE",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarClienteFormulario(idCliente: Int): Boolean {
        val conexionEscritura =writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "CLIENTE",
            "idCliente=?",
                arrayOf(
                    idCliente.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarClienteFormulario(
        nombreCliente: String,
        edadCliente: Int,
        telefonoCliente: String,
        correoCliente: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar =ContentValues()
        valoresAActualizar.put("nombreCliente", nombreCliente)
        valoresAActualizar.put("edadCliente", edadCliente)
        valoresAActualizar.put("telefonoCliente", telefonoCliente)
        valoresAActualizar.put("correoCliente", correoCliente)

        val resultadoActualizacion = conexionEscritura
            .update(
                "CLIENTE",
                valoresAActualizar,
                "idCliente=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoActualizacion == -1) false else true
    }


    fun getCliente(): ArrayList<BCliente>{
        var clienteList = ArrayList<BCliente>()
        val baseDatosLectura = readableDatabase
        var clienteEncontrado= BCliente(0,"",0,"","")
        val scriptGetCliente = "SELECT * FROM CLIENTE"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptGetCliente,
            null
        )
        if(resultadoConsultaLectura!=null && resultadoConsultaLectura.count!=0){
            resultadoConsultaLectura.moveToFirst()
            do {
                    val idCliente = resultadoConsultaLectura.getInt(0)
                    val nombreCliente = resultadoConsultaLectura.getString(1)
                    val edadCliente = resultadoConsultaLectura.getInt(2)
                    val telefonoCliente = resultadoConsultaLectura.getString(3)
                    val correoCliente = resultadoConsultaLectura.getString(4)

                if(idCliente!=null){
                    clienteEncontrado.idCliente = idCliente
                    clienteEncontrado.nombreCliente = nombreCliente
                    clienteEncontrado.edadCliente = edadCliente
                    clienteEncontrado.telefonoCliente = telefonoCliente
                    clienteEncontrado.correoCliente = correoCliente
                }

                clienteList.add(clienteEncontrado)
                clienteEncontrado = BCliente(0,"",0,"","")

            }while (resultadoConsultaLectura.moveToNext())
        }else{
            clienteList=ArrayList<BCliente>()
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return clienteList
    }

    fun crearProducto(
        idCliente: Int,
        nombreProducto: String,
        marcaProducto: String,
        precioProducto: Int
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idCliente", idCliente)
        valoresAGuardar.put("nombreProducto", nombreProducto)
        valoresAGuardar.put("marcaProducto", marcaProducto)
        valoresAGuardar.put("precioProducto", precioProducto)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "PRODUCTO",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarProducto(idProducto: Int): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "PRODUCTO",
                "idProducto=?",
                arrayOf(
                    idProducto.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarProductoFormulario(
        nombreProducto: String,
        marcaProducto: String,
        precioProducto: Int,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreProducto", nombreProducto)
        valoresAActualizar.put("marcaProducto", marcaProducto)
        valoresAActualizar.put("precioProducto", precioProducto)
        val resultadoActualizacion = conexionEscritura
            .update(
                "PRODUCTO",
                valoresAActualizar,
                "idProducto=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun getProductoIdCliente(idCliente: Int): ArrayList<BProducto>{
        var productoList = ArrayList<BProducto>()
        val baseDatosLectura = readableDatabase
        var productoEncontrado = BProducto(0,0, "","",0)
        val scriptGetProducto = "SELECT * FROM PRODUCTO WHERE idCliente=${idCliente}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptGetProducto,
            null
        )
        if(resultadoConsultaLectura!=null  && resultadoConsultaLectura.count!=0){
            resultadoConsultaLectura.moveToFirst()
            do {

                if(idCliente != null){

                    productoEncontrado.idProducto = resultadoConsultaLectura.getInt(0)
                    productoEncontrado.idCliente = resultadoConsultaLectura.getInt(1)
                    productoEncontrado.nombreProducto = resultadoConsultaLectura.getString(2)
                    productoEncontrado.marcaProducto = resultadoConsultaLectura.getString(3)
                    productoEncontrado.precioProducto = resultadoConsultaLectura.getInt(4)

                }
                productoList.add(productoEncontrado)
                productoEncontrado = BProducto(0,0, "","",0)

            }while (resultadoConsultaLectura.moveToNext())
        }else{
            productoList = ArrayList<BProducto>()
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return productoList
    }
}