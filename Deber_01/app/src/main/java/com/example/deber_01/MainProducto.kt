package com.example.deber_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainProducto : AppCompatActivity() {

    var idCliente = ""
    var idItemSeleccionado = 0
    var arregloProductos = ArrayList<BProducto>()

    var idProducto = ""
    var nombreProducto = ""
    var marcaProducto = ""
    var precioProducto = ""
    var idClienteProducto = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_producto)

        val bundle=intent.extras
        //idCliente= intent.getStringExtra("idCliente").toString()
        idCliente= bundle?.getString("idCliente").toString()
        //nombreCliente = bundle?.getString("nombreCliente").toString()


        val botonCrearProducto= findViewById<Button>(R.id.btn_anadir_producto)
        botonCrearProducto.setOnClickListener {
            parametroIDP(IngresaProducto::class.java)
        }
    }

    override fun onResume() {
        super.onResume()

        arregloProductos = EBaseDeDatos.tablaCliente!!.getProductoIdCliente(idCliente.toInt())

        val listView=findViewById<ListView>(R.id.lv_productos)

        val adaptador=ProductosAdapter(this,arregloProductos)
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)

        val volverClientes=findViewById<Button>(R.id.btn_volver)
        volverClientes.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater=menuInflater
        inflater.inflate(R.menu.menu2, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado=id

    }

    var productoSeleccionado = BProducto(0, 0, "", "",0)

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.mi_eliminar_producto->{

                val listViewProducto=findViewById<ListView>(R.id.lv_productos)
                var itemSeleccionado= listViewProducto.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemSeleccionado as BProducto
                productoSeleccionado=objetoSeleccionado
                EBaseDeDatos.tablaCliente!!.eliminarProducto(objetoSeleccionado.idProducto)
                onResume()

                return true
            }
            R.id.mi_editar_producto->{

                val listViewProducto=findViewById<ListView>(R.id.lv_productos)
                var itemSeleccionado= listViewProducto.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemSeleccionado as BProducto

                productoSeleccionado=objetoSeleccionado

                idProducto= productoSeleccionado.idProducto.toString()
                idClienteProducto = productoSeleccionado.idCliente.toString()
                nombreProducto= productoSeleccionado.nombreProducto
                marcaProducto= productoSeleccionado.marcaProducto
                precioProducto = productoSeleccionado.precioProducto.toString()
                parametrosProductoSeleccionado(EditarProducto::class.java)
                return true
            }
            else->super.onContextItemSelected(item)
        }
    }

    fun parametroIDP(
        clase: Class<*>
    ) {
        val intentProductosCliente=Intent(this,clase)
        intentProductosCliente.putExtra("idCliente",idCliente)
        startActivity(intentProductosCliente)
    }

    fun parametrosProductoSeleccionado(
        clase: Class<*>
    ){
        val intentProductoSeleccionado=Intent(this, clase)
        intentProductoSeleccionado.putExtra("idProducto", idProducto )
        intentProductoSeleccionado.putExtra("nombreProducto", nombreProducto )
        intentProductoSeleccionado.putExtra("marcaProducto", marcaProducto)
        intentProductoSeleccionado.putExtra("precioProducto", precioProducto )
        intentProductoSeleccionado.putExtra("idClienteProducto", idClienteProducto)

        startActivity(intentProductoSeleccionado)
    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}