package com.example.examen_iib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainProductos : AppCompatActivity() {
    var idItemSeleccionado = 0
    var cliente_original = Cliente("","",0,"","")
    val db = Firebase.firestore
    val clientes = db.collection("Clientes")
    var adaptador: ArrayAdapter<Producto>? = null

    var resultAddNewProducto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                cliente_original = intent.getParcelableExtra<Cliente>("Clientes")!!
            }
        }
    }

    var resultEditProducto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                cliente_original = intent.getParcelableExtra<Cliente>("Clientes")!!
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_productos)
    }

    override fun onStart() {
        super.onStart()

        cliente_original = intent.getParcelableExtra<Cliente>("Clientes")!!

        updateProductoList()

        val tv_cliente_original = findViewById<TextView>(R.id.tv_clientes)
        //tv_cliente_original.setText("Cliente: ${cliente_original.nombreCliente}")

        val btn_add_producto= findViewById<Button>(R.id.btn_anadir_producto)
        btn_add_producto.setOnClickListener {
            val abrirAnadirProducto = Intent(this, IngresarProductos::class.java)
            abrirAnadirProducto.putExtra("Clientes",cliente_original)
            resultAddNewProducto.launch(abrirAnadirProducto)
        }

        val btn_volver = findViewById<Button>(R.id.btn_volver)
        btn_volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("id animeXpersonaje", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var productoSelected: Producto = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editar_producto -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                val abrirEditarProducto = Intent(this, EditarProductos::class.java)
                abrirEditarProducto.putExtra("Cliente",cliente_original)
                abrirEditarProducto.putExtra("Producto",productoSelected)
                resultEditProducto.launch(abrirEditarProducto)
                return true
            }
            R.id.mi_eliminar_producto -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val animeSubcollection = clientes.document("${cliente_original.idCliente}")
                    .collection("Clientes")
                    .document("${productoSelected.idProducto}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-Producto","Success")
                    }
                    .addOnFailureListener{
                        Log.i("DELETE-Producto","Failure")
                    }
                updateProductoList()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updateProductoList(){

        val animeSubcollection = clientes.document("${cliente_original.idCliente}")
            .collection("Productos")
            .whereEqualTo("producto_idCliente","${cliente_original.idCliente}")



        val lv_productos_list = findViewById<ListView>(R.id.lv_productos)

        animeSubcollection.get().addOnSuccessListener { result ->

            var productosList = arrayListOf<Producto>()

            for (document in result){
                productosList.add(
                    Producto(
                        document.id.toString(),
                        document.data.get("producto_idCliente").toString(),
                        document.data.get("producto_nombre").toString(),
                        document.data.get("producto_marca").toString(),
                        document.data.get("producto_precio").toString().toInt(),
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                productosList
            )

            lv_productos_list.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lv_productos_list)

        }

    }
}