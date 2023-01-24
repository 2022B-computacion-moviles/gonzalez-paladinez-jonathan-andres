package com.example.examen_moviles_ib

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Cliente (

    private var cedula: String,
    private var nombre: String,
    private var fecha_nacimiento: Date,
    private var mayor_edad: Boolean,
    private var sueldo: Double

){
    companion object{
        fun verClientes(){
            var path = Paths.get("app/src/files/clientes.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                println(
                    "\nCédula: " + atributo[0] +
                    "\nNombre: " + atributo[1]  +
                    "\nFecha de Nacimiento: " + atributo[2]  +
                    "\n¿Mayor de edad?: " + atributo[3] +
                    "\nSueldo: " + atributo[4]
                )
            }
        }
        fun verProductosPorCliente(cedula: String) {
            val path = Paths.get("app/src/files/clientes.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo_cliente = it.split(";")
                if (atributo_cliente[0] == cedula) {
                    println(
                        "\nInformación del cliente: \n" +
                        "\nCédula: " + atributo_cliente[0] +
                        "\nNombre: " + atributo_cliente[1] +
                        "\nFecha de Nacimiento: " + atributo_cliente[2] +
                        "\n¿Mayor de edad?: " + atributo_cliente[3] +
                        "\nSueldo: " + atributo_cliente[4]
                    )
                    listarProductos(cedula, atributo_cliente[1])
                }
            }
        }

        fun listarProductos(cedula: String, nombre: String){
            print("\nLista de productos comprados por el cliente: " + nombre +"\n")
            var path1 = Paths.get("app/src/files/compras.txt")
            Files.lines(path1, Charsets.UTF_8).forEach {
                var atributo_compra = it.split(";")
                if (atributo_compra[1] == cedula) {
                    val path2 = Paths.get("app/src/files/productos.txt")
                    Files.lines(path2, Charsets.UTF_8).forEach {
                        var atributo_producto = it.split(";")
                        if(atributo_compra[0] == atributo_producto[0]) {
                            println(
                                "\nID Producto: " + atributo_producto[0] +
                                "\nNombre: " + atributo_producto[1] +
                                "\nMarca: " + atributo_producto[2] +
                                "\nPrecio: " + atributo_producto[3] +
                                "\nStock: " + atributo_producto[4] +
                                "\nNuevo: " + atributo_producto[5]
                            )
                        }
                    }
                }
            }
        }

        fun agregarCliente(cedula: String, nombre: String, fecha_nacimiento: String,
                           mayor_edad: Boolean, sueldo: Double)
        {
            var path = Paths.get("app/src/files/clientes.txt")
            var cliente_nuevo= "\n"+cedula+";"+nombre+";"+fecha_nacimiento+";"+mayor_edad+";"+sueldo
            try {
                Files.write(path, cliente_nuevo.toByteArray(), StandardOpenOption.APPEND)
                println("Cliente agregado")
            } catch (e: IOException) {
                println("Error al ingresar el cliente")
            }
        }

        fun actualizarCliente(cedula: String) {
            var formatoFecha = SimpleDateFormat("yyyy-MM-dd")
            var path = Paths.get("app/src/files/clientes.txt")
            var cadenaOriginal = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                if (atributo[0] == cedula) {
                    for (i in 0..4) {
                    }
                } else {
                    for (i in 0..3) {
                        cadenaOriginal += atributo[i] + ";"
                    }
                    cadenaOriginal += atributo[4] + "\n"
                }
            }

            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";").toMutableList()
                do {
                    if (atributo[0] != cedula) {
                        break
                    }
                    var continuarActualizacion = true

                    println(
                        "\nCédula: " + atributo[0] +
                                "\nNombre: " + atributo[1] +
                                "\nFecha de Nacimiento: " + atributo[2] +
                                "\n¿Mayor de edad?: " + atributo[3] +
                                "\nSueldo: " + atributo[4]
                    )

                    print(
                        "\nIngrese el campo que desea actualizar: " +
                                "\n1) Nombre" +
                                "\n2) Fecha de Nacimiento" +
                                "\n3) ¿Mayor de edad?" +
                                "\n4) Sueldo\n"
                    )
                    var opcion_actualizar = readln().toInt()
                    print("\nIngrese el el nuevo valor: ")
                    var dato_producto_actualizar = readln()
                    if (opcion_actualizar == 1) {
                        dato_producto_actualizar.toString()
                    } else if (opcion_actualizar == 2) {
                        formatoFecha.format(dato_producto_actualizar)
                    } else if (opcion_actualizar == 3) {
                        dato_producto_actualizar.toBoolean()
                    } else if (opcion_actualizar == 4) {
                        dato_producto_actualizar.toDouble()
                    } else {
                        print("Opción incorrecta")
                    }
                    atributo[opcion_actualizar] = dato_producto_actualizar
                    continuarActualizacion = false
                    var cadenaActualizada = ""

                    for (i in 0..3) {
                        cadenaActualizada += atributo[i] + ";"
                    }
                    cadenaActualizada += atributo[4] + "\n"

                    cadenaActualizada += cadenaOriginal

                    File("app/src/files/clientes.txt").printWriter()
                        .use { out -> out.print(cadenaActualizada) }

                    print("Cliente actualizado")

                } while (continuarActualizacion == true)
            }
        }

        fun eliminarCliente(cedula: String){
            var path = Paths.get("app/src/files/clientes.txt")
            var cadenaOriginal = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                if (atributo[0] == cedula) {
                    for (i in 0..4) {
                    }
                } else {
                    for (i in 0..3) {
                        cadenaOriginal += atributo[i] + ";"
                    }
                    cadenaOriginal += atributo[4] + "\n"
                }
                var cadenaActualizada = ""
                cadenaActualizada += cadenaOriginal
                File("app/src/files/clientes.txt").printWriter()
                    .use { out -> out.print(cadenaActualizada) }

                print("Cliente eliminado")
            }
        }
    }
}