package Proyecto_CRUDplus;

public class CNodoProducto{
    String clave;
    String descripcion;
    String precio;
    String stock;
    CNodoProducto sig;


    public CNodoProducto(String clave, String descripcion, String precio, String stock) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        sig = null;
    }
}
