package com.example.examen_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IngresarProductos : AppCompatActivity() {
    var cliente_original = Cliente("","",0,"","")
    val db = Firebase.firestore
    val clientes = db.collection("Clientes")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_productos)
    }

    override fun onStart() {
        super.onStart()

        cliente_original = intent.getParcelableExtra<Cliente>("Clientes")!!
        val clienteSubcollection = clientes.document("${cliente_original.idCliente}").collection("Productos")

        val in_producto_nombre = findViewById<EditText>(R.id.input_nombre_producto)
        val in_producto_marca = findViewById<EditText>(R.id.input_marca_producto)
        val in_producto_precio = findViewById<EditText>(R.id.input_precio_producto)

        val btn_confirm_add_producto = findViewById<Button>(R.id.btn_crear_producto)
        val btn_volver = findViewById<Button>(R.id.btn_cancelar)
        btn_volver.setOnClickListener {
            devolverRespuesta()
        }

        btn_confirm_add_producto.setOnClickListener {

            var producto = hashMapOf(
                "producto_idCliente" to cliente_original.idCliente.toString(),
                "producto_nombre" to in_producto_nombre.text.toString(),
                "producto_marca" to in_producto_marca.text.toString(),
                "producto_precio" to in_producto_precio.text.toString().toInt()
            )
            clienteSubcollection.add(producto).addOnSuccessListener {
                in_producto_nombre.text.clear()
                in_producto_marca.text.clear()
                in_producto_precio.text.clear()
                Toast.makeText(this,"Producto registrado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Add-Producto","Success")
            }.addOnFailureListener{
                Log.i("Add-Producto","Failed")
            }
        }

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("Cliente",cliente_original)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}