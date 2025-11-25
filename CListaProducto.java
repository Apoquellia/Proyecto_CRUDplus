package Proyecto_CRUDplus;

import java.util.Scanner;

public class CListaProducto {
    CNodoProducto primero;
    CNodoProducto ultimo;
    int tamano;
    Scanner entrada = new Scanner(System.in);

    CListaProducto() {
        primero = ultimo = null;
        tamano = 0;
    }

    void insertar(CNodoProducto nodo, CColaLog LOG) {
        if (tamano == 0) {
            primero = ultimo = nodo;
        } else {
            ultimo.sig = nodo;
            ultimo = nodo;
        }
        tamano++;
        
        LOG.añadirRegistro(new CNodoLog("ALTA", nodo.descripcion, nodo.clave));
    }

    void eliminar(String clave, CColaLog LOG) {
        if (tamano == 0) {
            System.out.println("Lista vacía, nada que eliminar.");
            return;
        }

        CNodoProducto anterior = null;
        CNodoProducto actual = primero; 

        while(actual != null && !actual.clave.equals(clave)) {
            anterior = actual;
            actual = actual.sig; 
        }

        if (actual == null) {
            System.out.println("No se encontró el producto con clave: " + clave);
            return;
        }

        LOG.añadirRegistro(new CNodoLog("BAJA", actual.descripcion, actual.clave));

        if (actual == primero && actual == ultimo) {
            primero = ultimo = null;
        } 
        else if (actual == primero) {
            primero = primero.sig; 
            actual.sig = null;    
        } 
        else if (actual == ultimo) {
            ultimo = anterior;
            ultimo.sig = null;
        } 
        else { 
            anterior.sig = actual.sig; 
            actual.sig = null;      
        }

        tamano--; 
        System.out.println("Producto eliminado correctamente.");
    }

    void actualizar(String clave, CColaLog LOG) {
        if (tamano == 0) {
            System.out.println("No hay nada que actualizar...");
            return;
        }

        CNodoProducto actual = primero; 

        while (actual != null && !actual.clave.equals(clave)) {
            actual = actual.sig; 
        }

        if (actual == null) {
            System.out.println("Producto no encontrado...");
        } else {
            int opcion = 0;
            do {
                System.out.println("\n--- EDITANDO: " + actual.descripcion + " ---");
                System.out.println("1. Nombre (" + actual.descripcion + ")");
                System.out.println("2. Precio ($" + actual.precio + ")");
                System.out.println("3. Stock (" + actual.stock + ")");
                System.out.println("4. Terminar Edición");
                System.out.print("Elige una opción: ");
                
                opcion = entrada.nextInt();
                entrada.nextLine(); 

                switch (opcion) {
                    case 1: 
                        System.out.println("Nuevo Nombre:");
                        String viejaDescripcion = actual.descripcion;
                        String nuevaDescripcion = entrada.nextLine();
                        actual.descripcion = nuevaDescripcion;
                        
                        LOG.añadirRegistro(new CNodoLog("EDICION", "Nombre cambiado de " + viejaDescripcion + " a " + nuevaDescripcion, "[" + actual.clave +"]"));
                        System.out.println("Nombre actualizado.");
                        break;

                    case 2: 
                        System.out.println("Nuevo Precio:");
                        double viejoPrecio = actual.precio;
                        double nuevoPrecio = entrada.nextDouble();
                        entrada.nextLine(); 
                        actual.precio = nuevoPrecio;
                        
                        LOG.añadirRegistro(new CNodoLog("EDICION", "Precio cambiado. de $" + viejoPrecio + " a $" + nuevoPrecio, "[" +actual.clave +"]"));
                        System.out.println("Precio actualizado.");
                        break;

                    case 3: 
                        System.out.println("Nuevo Stock:");
                        int viejoStock = actual.stock;
                        int nuevoStock = entrada.nextInt();
                        entrada.nextLine(); 
                        actual.stock = nuevoStock;
                        
                        LOG.añadirRegistro(new CNodoLog("EDICION", "Stock ajustado: " + viejoStock + " -> " + nuevoStock, "[" +actual.clave +"]"));
                        System.out.println("Stock actualizado.");
                        break;

                    case 4:
                        System.out.println("Guardando cambios y saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 4);
        }
    }

    void imprimir() {
        System.out.println("\n--- INVENTARIO ACTUAL ---");
        if (tamano == 0)
            System.out.println("No hay elementos!");
        else {
            CNodoProducto temp = primero;
            while(temp != null) {
                System.out.println("[" + temp.clave + "] " + temp.descripcion + " | $" + temp.precio + " | Stock: " + temp.stock);
                temp = temp.sig;
            }
        }
        System.out.println("-------------------------\n");
    }
}

    /* 

    void imprimirPosicionN(int n){

        CNodoProducto posicion = primero;  
        for (int i = 0; i < n; i++) {
        posicion = posicion.sig;
        }
    System.out.println("");
    System.out.println("El dato de la posición " + (n+1) + " Es:");
    System.out.println(posicion.dato);
    }
    
    
    void insertarEnMedio(CNodo anterior, CNodo anteriorCopia, CNodo siguiente){
    
    anterior.sig = anteriorCopia;
    anteriorCopia.sig = siguiente;

    }

    void duplicarLista(){
    
    if (tamano == 0){
        System.out.println("Primero, ingresa datos a la lista");
    
    } else {
    
        CNodoProducto anterior = primero;
        CNodoProducto siguiente = primero.sig;
        int tamanoOriginal = tamano;

        for (int i = 0; i < tamanoOriginal; i++) {
        
        CNodo anteriorCopia = new CNodo(anterior.dato);

        insertarEnMedio(anterior, anteriorCopia, siguiente);
        tamano ++;

        if (i == tamanoOriginal-1){

            ultimo = anteriorCopia;
            break;
        }    

        anterior = siguiente;
        if (siguiente != null) {
            siguiente = siguiente.sig;  
        }
        
        }
    }
}
        void insertar(CNodoProducto nodo) {
        if (tamano == 0)
            primero = ultimo = nodo;
        else {
            nodo.sig = primero;
            primero = nodo;
        }
        tamano++;
    }
*/

