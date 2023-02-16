package com.example.deber_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class IngresarCliente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_cliente)

        val nombreCliente = findViewById<EditText>(R.id.input_nombre_cliente)
        val edadCliente = findViewById<EditText>(R.id.input_edad_cliente)
        val telefonoCliente = findViewById<EditText>(R.id.input_telefono_cliente)
        val correoCliente = findViewById<EditText>(R.id.input_correo_cliente)


        val anadirClienteBDD = findViewById<Button>(R.id.btn_crear_cliente)
        anadirClienteBDD
            .setOnClickListener {

                val resultado = EBaseDeDatos.tablaCliente!!.crearCliente(
                    nombreCliente.text.toString(),
                    edadCliente.text.toString().toInt(),
                    telefonoCliente.text.toString(),
                    correoCliente.text.toString()
                )

                if(resultado){
                    abrirDialogo("Ingreso correcto")
                    limpiarEntradaTexto()
                }
                }

    }

    fun abrirDialogo(Titulo:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Titulo)
        builder.setPositiveButton(
            "Aceptar",
            null
        )

        val dialog = builder.create()
        dialog.show()
    }

    private fun limpiarEntradaTexto() {

        val nombreCliente = findViewById<EditText>(R.id.input_nombre_cliente)
        val edadCliente = findViewById<EditText>(R.id.input_edad_cliente)
        val telefonoCliente = findViewById<EditText>(R.id.input_telefono_cliente)
        val correoCliente = findViewById<EditText>(R.id.input_correo_cliente)
        nombreCliente.setText("")
        edadCliente.setText("")
        telefonoCliente.setText("")
        correoCliente.setText("")
    }

}