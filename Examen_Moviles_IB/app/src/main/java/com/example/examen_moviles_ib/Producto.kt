package com.example.examen_moviles_ib

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


class Producto (

    private var codigo: Int,
    private var nombre: String,
    private var marca: String,
    private var precio: Double,
    private var stock: Int,
    private var nuevo: Boolean
) {
    companion object {
        fun verProductos() {
            val path = Paths.get("app/src/files/productos.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                println(
                    "\nID Producto: " + atributo[0] +
                            "\nNombre: " + atributo[1] +
                            "\nMarca: " + atributo[2] +
                            "\nPrecio: " + atributo[3] +
                            "\nStock: " + atributo[4] +
                            "\nNuevo: " + atributo[5]
                )
            }
        }

        fun agregarProducto(
            nombre: String,
            marca: String,
            precio: Double,
            stock: Int,
            nuevo: Boolean
        ) {
            var path = Paths.get("app/src/files/productos.txt")
            var id_aux = Files.lines(path).count()
            var producto_nuevo =
                "\n" + (id_aux + 1).toString() + ";" + nombre + ";" + marca + ";" + precio + ";" + stock + ";" + nuevo
            try {
                Files.write(path, producto_nuevo.toByteArray(), StandardOpenOption.APPEND)
                println("Producto agregado")
            } catch (e: IOException) {
                println("Error al ingresar el producto")
            }
        }

        fun actualizarProducto(codigo: Int) {
            var path = Paths.get("app/src/files/productos.txt")
            var cadenaOriginal = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                if (atributo[0].toInt() == codigo) {
                    for (i in 0..5) {
                    }
                } else {
                    for (i in 0..4) {
                        cadenaOriginal += atributo[i] + ";"
                    }
                    cadenaOriginal += atributo[5] + "\n"
                }
            }

            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";").toMutableList()
                do {
                    if (atributo[0].toInt() != codigo) {
                        break
                    }
                    var continuarActualizacion = true

                    println(
                        "\nID Producto: " + atributo[0] +
                                "\nNombre: " + atributo[1] +
                                "\nMarca: " + atributo[2] +
                                "\nPrecio: " + atributo[3] +
                                "\nStock: " + atributo[4] +
                                "\nNuevo: " + atributo[5]
                    )

                    print(
                        "\nIngrese el campo que desea actualizar: " +
                                "\n1) Nombre" +
                                "\n2) Marca" +
                                "\n3) Precio" +
                                "\n4) Stock " +
                                "\n5) Nuevo\n "
                    )
                    var opcion_actualizar = readln().toInt()
                    print("\nIngrese el el nuevo valor: ")
                    var dato_producto_actualizar = readln()
                    if (opcion_actualizar == 1 || opcion_actualizar == 2) {
                        dato_producto_actualizar.toString()
                    } else if (opcion_actualizar == 3) {
                        dato_producto_actualizar.toDouble()
                    } else if (opcion_actualizar == 4) {
                        dato_producto_actualizar.toInt()
                    } else if (opcion_actualizar == 5) {
                        dato_producto_actualizar.toBoolean()
                    } else {
                        print("Opción incorrecta")
                    }
                    atributo[opcion_actualizar] = dato_producto_actualizar
                    continuarActualizacion = false
                    var cadenaActualizada = ""

                    for (i in 0..4) {
                        cadenaActualizada += atributo[i] + ";"
                    }
                    cadenaActualizada += atributo[5] + "\n"

                    cadenaActualizada += cadenaOriginal

                    File("app/src/files/productos.txt").printWriter()
                        .use { out -> out.print(cadenaActualizada) }

                    print("Producto actualizado")

                } while (continuarActualizacion == true)
            }

        }

        fun eliminarProducto(codigo: Int){
            var path = Paths.get("app/src/files/productos.txt")
            var cadenaOriginal = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var atributo = it.split(";")
                if (atributo[0].toInt() == codigo) {
                    for (i in 0..5) {
                    }
                } else {
                    for (i in 0..4) {
                        cadenaOriginal += atributo[i] + ";"
                    }
                    cadenaOriginal += atributo[5] + "\n"
                }
                var cadenaActualizada = ""
                cadenaActualizada += cadenaOriginal
                File("app/src/files/productos.txt").printWriter()
                    .use { out -> out.print(cadenaActualizada) }

                print("Producto eliminado")
            }
        }
    }

}