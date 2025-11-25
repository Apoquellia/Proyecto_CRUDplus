package Proyecto_CRUDplus;

public class CNodoProducto {

    String clave;
    String descripcion;
    double precio;
    int stock;

    CNodoProducto sig = null; 

    public CNodoProducto(String clave, String nombre, double precio, int stock) {
        this.clave = clave;
        this.descripcion = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    public CNodoProducto clonar() {
        return new CNodoProducto(clave, descripcion, precio, stock);
    }
    
    public void pegarDatos(CNodoProducto NodoRespaldo) {
        this.descripcion = NodoRespaldo.descripcion;
        this.precio = NodoRespaldo.precio;
        this.stock = NodoRespaldo.stock;
    }
}