package com.example.deber_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class IngresaProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_producto)

        val bundle = intent.extras
        val idCliente = intent.getStringExtra("idCliente")

        val nombreProducto =findViewById<EditText>(R.id.input_nombre_producto)
        val marcaProducto = findViewById<EditText>(R.id.input_marca_producto)
        val precioProducto = findViewById<EditText>(R.id.input_precio_producto)

        val anadirProducto = findViewById<Button>(R.id.btn_crear_producto)
        anadirProducto
            .setOnClickListener {
                EBaseDeDatos.tablaCliente!!.crearProducto(
                    idCliente.toString().toInt(),
                    nombreProducto.text.toString(),
                    marcaProducto.text.toString(),
                    precioProducto.text.toString().toInt()
                )
                abrirDialogo("Se ha ingresado correctamente")
                limpiarPantalla()
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


    private fun limpiarPantalla() {
        val nombreProducto = findViewById<EditText>(R.id.input_nombre_producto)
        val marcaProducto = findViewById<EditText>(R.id.input_marca_producto)
        val precioProducto = findViewById<EditText>(R.id.input_precio_producto)

        nombreProducto.setText("")
        marcaProducto.setText("")
        precioProducto.setText("")
    }

    override fun onResume() {
        super.onResume()

        val volverClientes=findViewById<Button>(R.id.btn_cancelar)
        volverClientes.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}