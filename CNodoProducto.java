package Proyecto_CRUDplus;

public class CNodoProducto {
    String clave;
    String descripcion;
    String precio;
    String stock;
    CNodoProducto sig;

    CNodoProducto(String clave, String descripcion, String precio) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.precio = precio;
        sig = null;
    } 


    CNodoProducto(String clave, String descripcion, String precio, String stock) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        sig = null;
    } 
}
