package Proyecto_CRUDplus;

import java.util.Scanner;

public class CListaProducto {
    CNodoProducto primero;
    CNodoProducto ultimo;
    int tamano;

    CPila pilaUndo = new CPila();
    CPila pilaRedo = new CPila();

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

        pilaUndo.push("ALTA", nodo); 
        pilaRedo.vaciar();
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

        CNodoProducto respaldo = actual.clonar();
        pilaUndo.push("BAJA", respaldo);
        pilaRedo.vaciar();

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
        System.out.println("Has eliminado el producto de la clave:" + clave);
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

                CNodoProducto fotoVieja = null;
                if (opcion >= 1 && opcion <= 3) {
                    fotoVieja = actual.clonar();
                }

                switch (opcion) {
                    case 1: 
                        System.out.println("Nuevo Nombre:");
                        String viejaDescripcion = actual.descripcion;
                        String nuevaDescripcion = entrada.nextLine();
                        actual.descripcion = nuevaDescripcion;
                        
                        pilaUndo.push("EDICION", fotoVieja);
                        pilaRedo.vaciar();
                        LOG.añadirRegistro(new CNodoLog("EDICION", "Nombre cambiado de " + viejaDescripcion + " a " + nuevaDescripcion, "[" + actual.clave +"]"));
                        
                        System.out.println("Nombre actualizado.");
                        break;

                    case 2: 
                        System.out.println("Nuevo Precio:");
                        double viejoPrecio = actual.precio;
                        double nuevoPrecio = entrada.nextDouble();
                        entrada.nextLine(); 
                        actual.precio = nuevoPrecio;
                        
                        pilaUndo.push("EDICION", fotoVieja);
                        pilaRedo.vaciar();
                        LOG.añadirRegistro(new CNodoLog("EDICION", "Precio cambiado. de $" + viejoPrecio + " a $" + nuevoPrecio, "[" +actual.clave +"]"));
                        
                        System.out.println("Precio actualizado.");
                        break;

                    case 3: 
                        System.out.println("Nuevo Stock:");
                        int viejoStock = actual.stock;
                        int nuevoStock = entrada.nextInt();
                        entrada.nextLine(); 
                        actual.stock = nuevoStock;
                        
                        pilaUndo.push("EDICION", fotoVieja);
                        pilaRedo.vaciar();
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

    void deshacer(CColaLog LOG) {
        if (pilaUndo.isEmpty()) {
            System.out.println("Nada que deshacer.");
            return;
        }

        CNodoUndoRedo accion = pilaUndo.pop();
        CNodoProducto respaldo = accion.respaldo;

        LOG.añadirRegistro(new CNodoLog("UNDO", "Revirtiendo " + accion.tipoAccion, respaldo.clave));

        switch (accion.tipoAccion) {
            case "ALTA":
                pilaRedo.push("ALTA", respaldo);
                eliminarInterno(respaldo.clave); 
                break;

            case "BAJA":
                pilaRedo.push("BAJA", respaldo);
                insertarInterno(respaldo);
                break;

            case "EDICION":
                CNodoProducto actual = buscarInterno(respaldo.clave);
                if (actual != null) {
                    pilaRedo.push("EDICION", actual.clonar());
                    actual.pegarDatos(respaldo); 
                }
                break;
        }
        System.out.println("<< Deshacer completado.");
    }

    void rehacer(CColaLog LOG) {
        if (pilaRedo.isEmpty()) {
            System.out.println("Nada que rehacer.");
            return;
        }

        CNodoUndoRedo accion = pilaRedo.pop();
        CNodoProducto respaldo = accion.respaldo;

        LOG.añadirRegistro(new CNodoLog("REDO", "Re-aplicando " + accion.tipoAccion, respaldo.clave));

        switch (accion.tipoAccion) {
            case "ALTA":
                pilaUndo.push("ALTA", respaldo);
                insertarInterno(respaldo);
                break;
            case "BAJA":
                pilaUndo.push("BAJA", respaldo);
                eliminarInterno(respaldo.clave);
                break;
            case "EDICION":
                CNodoProducto actual = buscarInterno(respaldo.clave);
                if (actual != null) {
                    pilaUndo.push("EDICION", actual.clonar());
                    actual.pegarDatos(respaldo);
                }
                break;
        }
        System.out.println(">> Rehacer completado.");
    }

    private void insertarInterno(CNodoProducto nodo) {
        nodo.sig = null; 
        if (tamano == 0) {
            primero = ultimo = nodo;
        } else {
            ultimo.sig = nodo;
            ultimo = nodo;
        }
        tamano++;
    }

    private void eliminarInterno(String clave) {
        if (tamano == 0) return;
        CNodoProducto ant = null, act = primero;
        while(act != null && !act.clave.equals(clave)) {
            ant = act; act = act.sig;
        }
        if (act == null) return;

        if (act == primero && act == ultimo) { primero = ultimo = null; }
        else if (act == primero) { primero = primero.sig; act.sig = null; }
        else if (act == ultimo) { ultimo = ant; ultimo.sig = null; }
        else { ant.sig = act.sig; act.sig = null; }
        tamano--;
    }

    private CNodoProducto buscarInterno(String clave) {
        CNodoProducto temp = primero;
        while (temp != null) {
            if (temp.clave.equals(clave)) return temp;
            temp = temp.sig;
        }
        return null;
    }

    void imprimir() {
        System.out.println("\n--- PRODUCTOS ---");
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