package com.example.examen_moviles_ib

import java.util.Date


fun main(args: Array<String>) {
    print("\tEXAMEN MÓVILES 2022B - JONATHAN GONZÁLEZ\n")
    do {
        var continuar=true
        print(
                    "\nSeleccione la operación a relizar: \n" +
                    "1) Listar productos existentes\n" +
                    "2) Listar los clientes existentes\n" +
                    "3) Listar productos comprados por determinando cliente\n" +
                    "4) Ver las compras realizadas\n" +
                    "5) Salir\n"
        )

        var opcion = readln().toInt()

        if (opcion == 1) {
            Producto.verProductos()
            print(
                "\n\n¿Desea realizar alguna de las siguientes operaciones?: \n" +
                "1) Ingresar un nuevo producto\n" +
                "2) Actualizar algún campo de un producto\n" +
                "3) Eliminar un producto\n" +
                "4) Volver\n"
            )
            var opCrud = readln().toInt()
            do {
                if (opCrud == 1){
                    print("Ingreso de nuevo producto. Por favor ingrese los datos del mismo:\n ")
                    print("\nNombre: ")
                    var nombre = readln().toString()
                    print("\nMarca: ")
                    var marca = readln().toString()
                    print("\nPrecio: ")
                    var precio = readln().toDouble()
                    print("\nStock: ")
                    var stock = readln().toInt()
                    print("\n¿Es nuevo?: ")
                    var nuevo = readln().toBoolean()

                    Producto.agregarProducto(nombre, marca, precio, stock, nuevo)

                    opCrud=4
                }

                else if (opCrud == 2){
                    print("\nIngrese el id del producto que desea actualizar: ")
                    var id_producto_actualizar = readln().toInt()
                    Producto.actualizarProducto(id_producto_actualizar)
                    opCrud=4
                }

                else if (opCrud == 3){
                    print("\nIngrese el id del producto que desea eliminar: ")
                    var id_producto_eliminar = readln().toInt()
                    Producto.eliminarProducto(id_producto_eliminar)
                    opCrud=4
                }
            } while(opCrud!=4)

        } else if (opcion == 2) {
            Cliente.verClientes()
            print(
                "\n\n¿Desea realizar alguna de las siguientes operaciones?: \n" +
                        "1) Ingresar un nuevo cliente\n" +
                        "2) Actualizar algún campo de un cliente\n" +
                        "3) Eliminar un cliente\n" +
                        "4) Volver\n"
            )
            var opCrud = readln().toInt()
            do {
                if (opCrud == 1){
                    print("Ingreso de nuevo cliente. Por favor ingrese los datos del mismo:\n ")
                    print("\nCédula: ")
                    var cedula = readln().toString()
                    print("\nNombre: ")
                    var nombre = readln().toString()
                    print("\nFecha de Nacimiento (yyyy-mm-dd): ")
                    var fecha_nacimiento = readln().toString()
                    print("\n¿Es mayor de edad?: \n")
                    var mayor_edad = readln().toBoolean()
                    print("\nSueldo: \n")
                    var sueldo = readln().toDouble()

                    Cliente.agregarCliente(cedula, nombre, fecha_nacimiento, mayor_edad, sueldo)

                    opCrud=4
                }

                else if (opCrud == 2){
                    print("\nIngrese la cédula del cliente que desea actualizar: ")
                    var cedula_cliente_actualizar = readln().toString()
                    Cliente.actualizarCliente(cedula_cliente_actualizar)

                    opCrud=4
                }

                else if (opCrud == 3){
                    print("\nIngrese la cedula del cliente que desea eliminar: ")
                    var cedula_cliente_eliminar = readln().toString()
                    Cliente.eliminarCliente(cedula_cliente_eliminar)

                    opCrud=4
                }
            } while(opCrud!=4)

        } else if (opcion == 3) {
            print("\nIngrese el número de cédula del cliente: ")
            var cedula = readln().toString()
            Cliente.verProductosPorCliente(cedula)
        }
        else if (opcion == 4) {
            Compras.verCompras()
            print(
                "\n\n¿Desea realizar alguna una nueva compra?: \n" +
                        "1) Si\n" +
                        "2) No\n"
            )
            var opCrud = readln().toInt()
            do {
                if (opCrud == 1) {
                    print("Ingreso el id del prodcuto a comprar:\n ")
                    var id_producto = readln().toInt()
                    print("\nIngrese el número de cédula del cliente que adquirió el producto: ")
                    var cedula = readln().toString()

                    Compras.agregarCompra(id_producto, cedula)

                    opCrud = 2
                } else {
                    opCrud = 2
                }
            }while (opCrud!=2)
        }
        else if (opcion==5){
            continuar=false
        }
    } while(continuar==true)


}