package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int longitud;

    private class Nodo {
        // Agregar atributos privados del Nodo
        private T value;
        private Nodo derecha;
        private Nodo izquierda;
        private Nodo padre;

        // Crear Constructor del nodo
        public Nodo (T valor){
            value = valor;
            derecha = null;
            izquierda = null;
            padre = null;
        }
    }

    public ABB() {
        raiz = null;
        longitud = 0;
    }

    public int cardinal() {
        return longitud;
    }

    public T minimo(){
        Nodo sig = new Nodo(raiz.value);
        while (sig.izquierda != null){
            sig = sig.izquierda;
        }
        return sig.value;
    }

    public T maximo(){
        Nodo sig = new Nodo(raiz.value);
        while (sig.derecha != null){
            sig = sig.derecha;
        }
        return sig.value;
    }

    public void insertar(T elem){
        Nodo insert = new Nodo(elem);
        
        if (raiz == null){
            raiz = insert;
        } else {
            insertar_2(raiz, insert);
        }
        longitud = longitud + 1;
    }
    // me armo una función recursiva para poder recorrer el ABB
    private void insertar_2 (Nodo root , Nodo elemento){
        if (elemento.value.compareTo(root.value) > 0){
            // si el valor a insertar es mayor a la raiz
            if (root.derecha == null){
                // si a derecha no hay nada, le mando el insert
                root.derecha = elemento;
                elemento.padre = root;
            } else {
                insertar_2(root.derecha, elemento);
            }
        } else {
            // caso que el elemento sea más chico que la raiz
            if (root.izquierda == null){
                // no tenemos nada a izquierda
                root.izquierda = elemento;
                elemento.padre = root;
            } else {
                insertar_2(root.izquierda, elemento);
            }
        }  
    }
    
    public boolean pertenece(T elem){
        Nodo target = new Nodo(elem);
        boolean res = false;
        if (raiz == null){
            return false;
        } else {
            res = comparador(raiz, target);
        }
        return res;
    }
    
    public boolean comparador(Nodo actual,Nodo elemento){
        boolean res = false;
        // si el elemento es mayor, compara recursivamente con el de la derecha
        if (elemento.value.compareTo(actual.value) > 0){
            comparador(actual.derecha, elemento);  
        // si el elemento es menor compara recursivamente a izquierda
        } else if (elemento.value.compareTo(actual.value) < 0){
            comparador(actual.izquierda, elemento);
        // si son el mismo true
        } else if (elemento.value.compareTo(actual.value) == 0){
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
