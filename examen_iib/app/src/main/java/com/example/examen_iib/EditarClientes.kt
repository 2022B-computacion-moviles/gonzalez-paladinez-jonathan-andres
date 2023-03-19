package com.example.examen_iib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarClientes : AppCompatActivity() {
    var cliente_update = Cliente("","",0,"","")
    val db = Firebase.firestore
    val clientes = db.collection("Clientes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clientes)
    }

    override fun onStart() {
        super.onStart()

        cliente_update = intent.getParcelableExtra<Cliente>("Cliente")!!

        val in_cliente_nombre = findViewById<EditText>(R.id.input_nombre_cliente)
        in_cliente_nombre.setText(cliente_update.nombreCliente.toString())

        val in_cliente_edad = findViewById<EditText>(R.id.input_edad_cliente)
        in_cliente_edad.setText(cliente_update.edadCliente.toString().toInt())

        val in_cliente_telefono = findViewById<EditText>(R.id.input_telefono_cliente)
        in_cliente_telefono.setText(cliente_update.telefonoCliente.toString())

        val in_cliente_correo = findViewById<EditText>(R.id.input_correo_cliente)
        in_cliente_correo.setText(cliente_update.correoCliente.toString())

        val btn_confirm_update = findViewById<Button>(R.id.btn_actualizar_cliente)
        btn_confirm_update.setOnClickListener {
            clientes.document("${cliente_update.idCliente}").update(
                "cliente_nombre", in_cliente_nombre.text.toString(),
                "cliente_edad" , in_cliente_edad.text.toString().toInt(),
                "cliente_telefono", in_cliente_telefono.text.toString(),
                "cliente_correo", in_cliente_correo.text.toString()
            )
            Toast.makeText(this,"Info. actualizada exitosamente", Toast.LENGTH_SHORT).show()
        }

    }
}