package com.example.deber_01

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class EditarProducto : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        val bundle=intent.extras

        val idProducto =bundle?.getString("idProducto")

        val nombreProducto = findViewById<EditText>(R.id.input_nombre_producto)
        val marcaProducto = findViewById<EditText>(R.id.input_marca_producto)
        val precioProducto = findViewById<EditText>(R.id.input_precio_producto)

        nombreProducto.setText(bundle?.getString("nombreProducto"))
        marcaProducto.setText(bundle?.getString("marcaProducto"))
        precioProducto.setText(bundle?.getString("precioProducto"))

        val botonEditarProducto =findViewById<Button>(R.id.btn_actualizar_producto)
        botonEditarProducto
            .setOnClickListener {
                var res= EBaseDeDatos.tablaCliente!!.actualizarProductoFormulario(
                    nombreProducto.text.toString(),
                    marcaProducto.text.toString(),
                    precioProducto.text.toString().toInt(),
                    idProducto.toString().toInt()
                )
                abrirDialogo("Actualización exitosa")
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

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

}