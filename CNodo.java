package Proyecto_CRUDplus;

public class CNodo {
    CNodoProducto dato;
    CNodo sig;

    CNodo(CNodoProducto dato) {
        this.dato = dato;
        sig = null;
    } 
}