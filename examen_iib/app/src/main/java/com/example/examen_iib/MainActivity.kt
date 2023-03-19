package com.example.examen_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    val clientes = db.collection("Clientes")
    var idItemSeleccionado = -1
    var adaptador: ArrayAdapter<Cliente>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        updateAnimeList()

        val btn_anadir_cliente = findViewById<Button>(R.id.btn_anadir_cliente)
        btn_anadir_cliente.setOnClickListener {
            val intent = Intent(this, IngresarClientes::class.java)
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var ClienteSelected:Cliente = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${ClienteSelected.idCliente}")
                val openEditAnime = Intent(this, EditarClientes::class.java)
                openEditAnime.putExtra("Productos",ClienteSelected)
                startActivity(openEditAnime)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                clientes.document("${ClienteSelected.idCliente}").delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-CLIENTE","Success")
                    }
                    .addOnFailureListener{
                        Log.i("DELETE-CLIENTE","Failure")
                    }
                updateAnimeList()
                return true
            }
            R.id.mi_productos -> {
                Log.i("context-menu", "Cliente: ${idItemSeleccionado}")
                val openPersonajeList = Intent(this, MainProductos::class.java)
                openPersonajeList.putExtra("Clientes",ClienteSelected)
                startActivity(openPersonajeList)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updateAnimeList(){
        val lv_cliente = findViewById<ListView>(R.id.lv_clientes)

        clientes.get().addOnSuccessListener{ result ->

            var clientesList = arrayListOf<Cliente>()

            for (document in result) {
                clientesList.add(
                    Cliente(
                        document.id.toString(),
                        document.get("cliente_nombre").toString(),
                        document.get("cliente_edad").toString().toInt(),
                        document.get("cliente_telefono").toString(),
                        document.get("cliente_correo").toString()
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                clientesList
            )

            lv_cliente.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lv_cliente)

        }.addOnFailureListener{
            Log.i("ERROR", "Faiuler retreving clientes")
        }
    }
}