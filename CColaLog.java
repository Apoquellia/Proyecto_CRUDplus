package Proyecto_CRUDplus;

public class CColaLog {
    CNodoLog primero;
    CNodoLog ultimo;
    int tamano;

    CColaLog() {
        primero = ultimo = null;
        tamano = 0;
    }

    // método EnQueue.
    void añadirRegistro(CNodoLog nodo) {
        nodo.sig = null;
        if (tamano == 0) {
            primero = ultimo = nodo;
        } else {
            ultimo.sig = nodo;
            ultimo = nodo;
        }
        tamano++;
    }

    boolean isEmpty() {
        if (tamano == 0)
            return true;
        else
            return false;
    }

public void imprimirLog() {
        if (isEmpty()) {
            System.out.println("Bitácora vacía.");
            return;
        }

        System.out.println("\n=== HISTORIAL DE ACCIONES (Queue) ===");
        CNodoLog actual = primero;
        while (actual != null) {
            System.out.println(actual.fecha + " | " + actual.mensaje + " | " + "clave: " + actual.clave);
            actual = actual.sig;
        } 
        System.out.println("=====================================\n");
    }
}

