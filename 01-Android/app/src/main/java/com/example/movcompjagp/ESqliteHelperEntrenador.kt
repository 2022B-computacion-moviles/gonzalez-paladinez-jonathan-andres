package com.example.movcompjagp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador (
    contexto: Context?,
) :SQLiteOpenHelper(
    contexto,
    "moviles", //Nombre de nuestra BDD SQLite (moviles.sqlite)
null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean {
        //this.readableDataBase Lectura
        //this.writeableDatabase Escritura
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "ENTRENADOR", //Tabla
            null,
                valoresAGuardar //Valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarEntrenadorFormulario (id:Int) :Boolean {
        //val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        //"SELECT * FROM ENTRENADOR WHERE ID = ?"
        //arrayOf(
        //  id.toString()
        // )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR", //tabla
            "id=?", //id=? and nombre=? (podemos mandar parámetros en orden)
            arrayOf(  // Arreglo de parámetros en orden [1, "Adrian"]
                id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR", //Nombre de la Tabla
            valoresAActualizar, // Valores a actualizar
            "id=?", //Claúsula Where
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador{
        //val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            arrayOf(
                id.toString()
            )
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "", "")
        //LÓGICA OBTENER USUARIO
        do {
            val id = resultadoConsultaLectura.getInt(0) //Columna índice 0 --> ID
            val nombre = resultadoConsultaLectura.getString(1) //Columna índice 1 --> NOMBRE
            val descripcion =
                resultadoConsultaLectura.getString(2) //Columna índice 2 --> DESCRIPCION
            if(id != null){
                usuarioEncontrado.id = id
                usuarioEncontrado.nombre = nombre
                usuarioEncontrado.descripcion = descripcion
            }

        }while (resultadoConsultaLectura.moveToNext())
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}

