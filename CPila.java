package Proyecto_CRUDplus;

import java.util.Scanner;

public class CPila {
    CNodo primero;
    CNodo ultimo;
    int tamano;

    CPila() {
        primero = ultimo = null;
        tamano = 0;
    }


    boolean esBalanceadoPasoAPaso(String s) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (s == null)
                return true;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    push(new CNodo(String.valueOf(c)));
                    System.out.println("Paso " + (i) + ": se encontró '(' -> push ");
                    System.out.println(imprimir());
                    System.out.println("Presiona ENTER para continuar...");
                    scanner.nextLine();
                } else if (c == ')') {
                    if (isEmpty()) {
                        System.out.println("Paso " + (i) + ": se encontró ')' pero la pila está vacía -> falso");
                        return false;
                    }
                    pop();
                    System.out.println("Paso " + (i) + ": se encontró ')' -> pop");
                    System.out.println(imprimir());
                    System.out.println("Presiona ENTER para continuar...");
                    scanner.nextLine();
                }
            }
        }
        boolean resultado = isEmpty();
        System.out.println("Resultado final: " + (resultado ? "balanceado" : "no balanceado"));
        return resultado;
    }

    // método PUSH para pilas (equiv. a "insertarFinal" en listas)
    void push(CNodo nodo) {
        if (tamano == 0) {
            primero = ultimo = nodo;
        } else {
            ultimo.sig = nodo;
            ultimo = nodo;
        }
        tamano++;
    }

    String peek() {
        System.out.println();
        if (tamano == 0) {
            System.out.println("No hay elementos!");
            return "No hay elementos!";      // ESTO EN CASO DE NO MANEJAR POSITIVOS EN LA PILA 
        } else 
            return ultimo.dato;
    }

   // método POP (equiv. a eliminarFinal en listas)
    CNodo pop() {
        CNodo temp = null;
        CNodo temp2 = null;

        if (tamano == 0)
            System.out.println("No hay elems. en la pila!");
        else {
            int indice = 1;
            temp = primero;
            temp2 = ultimo;

            while (indice < tamano-1) {
                temp = temp.sig;
                indice++;
            }

            if (tamano > 1) {
                ultimo = temp;
                ultimo.sig = null;
            } else 
                primero = ultimo = null;

            tamano--;
        }
        return temp2;
    }

    boolean isEmpty() {
        if (tamano == 0)
            return true;
        else
            return false;
    }

    String imprimir() {
        String cad = "";
        if (tamano == 0)
            return cad;
        else {
            CNodo temp = primero;
            while(temp != null) {
                cad = "[ " + String.valueOf(temp.dato) + " ]" + "\n" + cad;
                temp = temp.sig;
            }
        }
        return cad;
    }
}