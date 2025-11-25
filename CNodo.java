package Proyecto_CRUDplus;

public class CNodo {
    String clave;
    protected CNodo sig;

    CNodo(String clave) {
        this.clave = clave;
        sig = null;
    } 
}