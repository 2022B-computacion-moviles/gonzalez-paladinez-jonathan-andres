package com.example.deber_01

import BCliente
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    var idItemSeleccionado = 0
    var arreglo = ArrayList<BCliente>()

    var idCliente = ""
    var nombreCliente = " "
    var edadCliente = ""
    var telefonoCliente = ""
    var correoCliente = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EBaseDeDatos.tablaCliente = ESqliteHelper(this)

        val btnAnadirCliente = findViewById<Button>(R.id.btn_anadir_cliente)
        btnAnadirCliente.setOnClickListener {
            irActividad(IngresarCliente::class.java)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    var clienteSeleccionado=BCliente(0,"",0,"","")

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_productos -> {
                val listViewCliente=findViewById<ListView>(R.id.lv_clientes)
                var itemselect= listViewCliente.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as BCliente
                clienteSeleccionado=objetoSeleccionado
                idCliente=clienteSeleccionado.idCliente.toString()
                nombreCliente= clienteSeleccionado.nombreCliente
                parametroID(MainProducto::class.java)
                return true
            }
            R.id.mi_editar -> {
                val listViewCliente=findViewById<ListView>(R.id.lv_clientes)
                var itemselect = listViewCliente.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselect as BCliente

                clienteSeleccionado = objetoSeleccionado

                idCliente = clienteSeleccionado.idCliente.toString()
                nombreCliente = clienteSeleccionado.nombreCliente
                edadCliente = clienteSeleccionado.edadCliente.toString()
                telefonoCliente = clienteSeleccionado.telefonoCliente
                correoCliente = clienteSeleccionado.correoCliente

                parametrosClienteSeleccionado(EditarCliente::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                val lvClientes = findViewById<ListView>(R.id.lv_clientes)
                var itemselect =lvClientes.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselect as BCliente

                clienteSeleccionado = objetoSeleccionado
                EBaseDeDatos.tablaCliente!!.eliminarClienteFormulario(objetoSeleccionado.idCliente)
                onResume()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        arreglo = EBaseDeDatos.tablaCliente!!.getCliente()

        val listView=findViewById<ListView>(R.id.lv_clientes)

        val adaptador=ClientesAdapter(this,arreglo)
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    fun parametroID(
        clase: Class<*>
    ) {
        val intentProductoCliente=Intent(this,clase)
        intentProductoCliente.putExtra("idCliente",idCliente)
        intentProductoCliente.putExtra("nombreCliente", nombreCliente)
        startActivity(intentProductoCliente)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun parametrosClienteSeleccionado(clase: Class<*>){
        val intentClienteSeleccionado = Intent(this, clase)
        intentClienteSeleccionado.putExtra("idCliente", idCliente)
        intentClienteSeleccionado.putExtra("nombreCliente",nombreCliente)
        intentClienteSeleccionado.putExtra("edadCliente",edadCliente)
        intentClienteSeleccionado.putExtra("telefonoCliente", telefonoCliente)
        intentClienteSeleccionado.putExtra("correoCliente", correoCliente)
        startActivity(intentClienteSeleccionado)
    }

    fun Context.showToast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

