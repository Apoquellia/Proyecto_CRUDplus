package Proyecto_CRUDplus;

public class CLista {
    CNodoProducto primero;
    CNodoProducto ultimo;
    int tamano;

    CLista() {
        primero = ultimo = null;
        tamano = 0;
    }

/*     void actualizar(String clave){
        
        CNodoProducto actual = primero; 
        if (tamano == 0)
        {
            System.out.println("No hay qué actualizar...");
            return;

        } else if(tamano == 1){ 
            
            if(actual.clave.equals(clave)){
            
            
            System.out.println("Producto encontrado! ¿qué atributo deseas actualizar?");
            System.out.println("1. Descripción");
            System.out.println("2. Precio");
            System.out.println("3. Stock");

            switch (opcion) {

            }else{
                System.out.println("No está el objeto");
            } 

        } else{

            while(actual != null && !actual.clave.equals(clave)) {
            actual = actual.sig;
            }
            
        }
    }
}
*/
    void eliminar(String clave) {
        
        CNodoProducto anterior = null;
        CNodoProducto actual = primero; 
        if (tamano == 0)
        {
            System.out.println("No hay qué eliminar...");
            return;
        } else if(tamano == 1 && actual.clave.equals(clave)){ 
        primero = ultimo = null;
        tamano--;
        return;
        } 
        while(actual != null && !actual.clave.equals(clave)) {
            anterior = actual;
            actual = actual.sig;
            }
        if(actual == null){
            System.err.println("Producto no encontrado");
            return;
        }
        if(anterior == null){
            primero = actual.sig;
            if (primero == null)
            {
                ultimo = null;
            }
        }else {
            anterior.sig = actual.sig;
            if (actual == ultimo){
                ultimo = anterior;
            }
            
        
    }
        tamano--;
}

    void insertarUltimo(CNodoProducto nodo)
    {
    if (tamano == 0){
            primero = ultimo = nodo;
    }else{
        ultimo.sig = nodo;
        ultimo = nodo;
        }
    tamano++;
    }

    void imprimir() {
        System.out.println();
        if (tamano == 0)
            System.out.println("No hay elementos!");
        else {
            CNodoProducto temp = primero;
            while(temp != null) {
                System.out.print("[" + temp.clave + ", " + temp.descripcion + ", " + temp.precio + ", " + temp.stock + "]");
                System.err.println();
            temp = temp.sig;
            }
        }
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

