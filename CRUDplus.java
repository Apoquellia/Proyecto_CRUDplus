package Proyecto_CRUDplus;

import java.util.Scanner;
//hola hola
public class CRUDplus{

    public static void main(String[] args) {

    CListaProducto DATOS = new CListaProducto();
    CColaLog LOG = new CColaLog();

    String clave;

    Scanner entrada = new Scanner(System.in);
    int opcion;

        do {
            System.out.println("===== MENú PRINCIPAL =====");
            System.out.println("1. Agregar");
            System.out.println("2. Eliminar");
            System.out.println("3. Actualizar");
            System.out.println("4. Deshacer");
            System.out.println("5. Rehacer");
            System.out.println("6. Log");
            System.out.println("7. Imprimir");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:

                    entrada.nextLine();

            System.out.println("");
            System.out.println("===== AGREGAR =====");
            System.out.println("");        
                    System.out.println("Por favor, ingresa los datos en su respectivo órden");
                    System.out.println("");
                    
                    System.out.print("Clave:");
                    clave = entrada.nextLine();
                    
                    System.out.print("Descripción:");
                    String descripcion = entrada.nextLine();
                    
                    System.out.print("Precio:");
                    double precio = entrada.nextDouble();
                    
                    System.out.print("Stock:");
                    int stock = entrada.nextInt();
                    entrada.nextLine();

            CNodoProducto n1 = new CNodoProducto(clave, descripcion, precio, stock);

            DATOS.insertar(n1, LOG);
                
                    System.out.println("Datos guardados.");
                    
                break;
                
                case 2:

                System.out.println("");
            System.out.println("===== ELIMINAR =====");
            System.out.println("");        
                    System.out.println("Por favor, ingresa la clave del producto que quieres eliminar");
                    
                    entrada.nextLine();
                    
                    System.out.print("Clave:");
                    clave = entrada.nextLine();

                DATOS.eliminar(clave, LOG);

                break;

                case 3:

                                System.out.println("");
            System.out.println("===== EDITAR =====");
            System.out.println("");        
                    System.out.println("Por favor, ingresa la clave del producto que quieres editar");
                    
                    entrada.nextLine();
                    
                    System.out.print("Clave:");
                    clave = entrada.nextLine();

                DATOS.actualizar(clave, LOG);

                    System.out.print("Has actualizado el producto de la clave:" + clave);
                
                break;

                case 4:

                    System.out.println("");
                    System.out.println("===== DESHACER =====");

                    DATOS.deshacer(LOG);

                break;

                case 5:
                    System.out.println("");
                    System.out.println("===== REHACER =====");

                    DATOS.rehacer(LOG);

                break;

                case 6:

                LOG.imprimirLog();
                
                break;
                
                case 7:

                    System.out.println("     --PRDODUCTOS --      ");

                DATOS.imprimir();

                    break;

                case 8:
                    System.out.println("Gracias por usar el sistema CRUD+");
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

            System.out.println(); 
        } while (opcion != 8);

        entrada.close();
    }
}