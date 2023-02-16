package com.example.deber_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class EditarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)

        val bundle =intent.extras

        val nombreCliente= findViewById<EditText>(R.id.input_nombre_cliente)
        val edadCliente = findViewById<EditText>(R.id.input_edad_cliente)
        val telefonoCliente = findViewById<EditText>(R.id.input_telefono_cliente)
        val correoCliente = findViewById<EditText>(R.id.input_correo_cliente)

        val idCliente= bundle?.getString("idCliente")
        nombreCliente.setText(bundle?.getString("nombreCliente"))
        edadCliente.setText(bundle?.getString("edadCliente"))
        telefonoCliente.setText(bundle?.getString("telefonoCliente"))
        correoCliente.setText(bundle?.getString("correoCliente"))

        val botonEditarCliente = findViewById<Button>(R.id.btn_actualizar_cliente)
        botonEditarCliente.setOnClickListener {
            EBaseDeDatos.tablaCliente!!.actualizarClienteFormulario(
                nombreCliente.text.toString(),
                edadCliente.text.toString().toInt(),
                telefonoCliente.text.toString(),
                correoCliente.text.toString(),
                idCliente.toString().toInt()
            )
            abrirDialogo("Actualización exitosa")
        }
    }

    fun abrirDialogo(Titulo:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Titulo)
        builder.setPositiveButton(
            "Aceptar"
        ){_,_-> irActividad(MainActivity::class.java)}
        val dialog = builder.create()
        dialog.show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }
}