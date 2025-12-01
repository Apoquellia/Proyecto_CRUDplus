package Proyecto_CRUDplus;

public class CPila {

    CNodoUndoRedo cima; 
    int tamano;

    CPila() {   
        cima = null;
        tamano = 0;
    }
    
    void push(String tipoAccion, CNodoProducto productoCopiado) {

        CNodoUndoRedo nuevo = new CNodoUndoRedo(tipoAccion, productoCopiado);

        if (cima == null) {
            cima = nuevo;
        } else {
            nuevo.sig = cima; 
            cima = nuevo;     
        }
        tamano++;
    }

    CNodoUndoRedo pop() {
        if (cima == null) {
            System.out.println("No hay elems. en la pila!");
            return null;
        }

        CNodoUndoRedo nodoSacado = cima;
        cima = cima.sig;                 
        
        nodoSacado.sig = null; 
        tamano--;
        
        return nodoSacado;
    }

    String peek() {
        if (cima == null) {
            return "Pila vacía";
        } else {
            return cima.tipoAccion;
        }
    }

    void vaciar() {
        cima = null; 
        tamano = 0;
    }

    boolean isEmpty() {
        return cima == null;
    }
}