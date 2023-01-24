package com.example.examen_moviles_ib

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class Compras (
    private var id_producto: Int,
    private var cedula_comprador: String
    ){

    companion object{

        fun verCompras(){
            val path = Paths.get("app/src/files/compras.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                println(
                    "\nID Producto: " + atributo[0] +
                    "\nCédula de comprador: " + atributo[1]
                )
            }
        }

        fun agregarCompra (id_producto: Int, cedula_comprador: String){
            var path = Paths.get("app/src/files/compras.txt")
            var compra = "\n"+id_producto+";"+cedula_comprador
            try {
                Files.write(path, compra.toByteArray(), StandardOpenOption.APPEND)
                println("Compra agregada")
            } catch (e: IOException) {
                println("Error al ingresar la compra")
            }
        }
    }
}