package Proyecto_CRUDplus;

public class CNodoUndoRedo {
    String tipoAccion;      
    CNodoProducto respaldo; 
    CNodoUndoRedo sig;      

    public CNodoUndoRedo(String accion, CNodoProducto prod) {
        this.tipoAccion = accion;
        this.respaldo = prod;
        this.sig = null;
    }
}