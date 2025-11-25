package Proyecto_CRUDplus;

import java.util.Date; 

public class CNodoLog { 
    
    String fecha;
    String mensaje;    
    String clave;

    CNodoLog sig = null;  

    public CNodoLog(String accion, String nombre, String clave) {

        this.fecha = new Date().toString();
        this.mensaje = "[" + accion + "]: " + nombre; 
        this.clave = clave;
    }
}