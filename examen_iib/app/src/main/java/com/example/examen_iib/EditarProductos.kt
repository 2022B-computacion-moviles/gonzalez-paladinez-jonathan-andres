package com.example.examen_iib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarProductos : AppCompatActivity() {
    var cliente_original = Cliente("","",0,"","")
    var producto_actual = Producto("","","","",0)
    val db = Firebase.firestore
    val clientes = db.collection("Clientes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_productos)
    }

    override fun onStart() {
        super.onStart()

        cliente_original = intent.getParcelableExtra<Cliente>("Cliente")!!
        producto_actual = intent.getParcelableExtra<Producto>("Producto")!!

        val in_producto_nombre = findViewById<EditText>(R.id.input_nombre_producto)
        in_producto_nombre.setText("${producto_actual.nombreProducto}")

        val in_producto_marca = findViewById<EditText>(R.id.input_marca_producto)
        in_producto_marca.setText("${producto_actual.marcaProducto}")

        val in_producto_precio = findViewById<EditText>(R.id.input_precio_producto)
        in_producto_precio.setText("${producto_actual.precioProducto}")


        val btn_confirm_update_personaje = findViewById<Button>(R.id.btn_actualizar_producto)
        btn_confirm_update_personaje.setOnClickListener {
            clientes.document("${cliente_original.idCliente}")
                .collection("Productos")
                .document("${producto_actual.idProducto}")
                .update(
                    "producto_nombre" ,in_producto_nombre.text.toString(),
                    "producto_marca" ,in_producto_marca.text.toString(),
                    "prducto_precio" ,in_producto_precio.text.toString().toInt()
                )
            Toast.makeText(this,"Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
        }

    }
}