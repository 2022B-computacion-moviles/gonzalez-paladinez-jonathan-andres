package com.example.examen_iib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IngresarClientes : AppCompatActivity() {
    val db= Firebase.firestore
    val clientes = db.collection("Clientes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_clientes)
    }

    override fun onStart() {
        super.onStart()
        val in_cliente_nombre = findViewById<EditText>(R.id.input_nombre_cliente)
        val in_cliente_edad = findViewById<EditText>(R.id.input_edad_cliente)
        val in_cliente_telefono = findViewById<EditText>(R.id.input_telefono_cliente)
        val in_cliente_correo = findViewById<EditText>(R.id.input_correo_cliente)

        val btn_confirm_add_cliente= findViewById<Button>(R.id.btn_crear_cliente)
        btn_confirm_add_cliente.setOnClickListener {
            var cliente = hashMapOf(
                "cliente_nombre" to in_cliente_nombre.text.toString(),
                "cliente_edad" to in_cliente_edad.text.toString().toInt(),
                "cliente_telefono" to in_cliente_telefono.text.toString(),
                "cliente_correo" to in_cliente_correo.text.toString()
            )
            clientes.add(cliente).addOnSuccessListener {
                in_cliente_nombre.text.clear()
                in_cliente_edad.text.clear()
                in_cliente_telefono.text.clear()
                in_cliente_correo.text.clear()
                Toast.makeText(this,"Cliente registrado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Add-Cliente","Success")
            }.addOnFailureListener{
                Log.i("Add-Cliente","Failed")
            }

        }

    }
}