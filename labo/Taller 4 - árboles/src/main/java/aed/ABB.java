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
        Nodo min = raiz;
        while (min.izquierda != null){
            min = min.izquierda;
        }
        return min.value;
    }
    public T maximo(){
        Nodo max = raiz;
        while (max.derecha != null){
            max = max.derecha;
        }
        return max.value;
    }

    public void insertar(T elem){
        Nodo insert = new Nodo(elem);
        if (raiz == null){
            raiz = insert; 
            longitud ++; 
        } else {
            if(!pertenece(elem)){
                insertar_2(raiz, insert);
                longitud ++; 
            }
        }
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
        } else if (elemento.value.compareTo(root.value) < 0){
                // caso que el elemento sea más chico que la raiz
            if (root.izquierda == null){
                    // no tenemos nada a izquierda
                    root.izquierda = elemento;
                    elemento.padre = root;
            } else {
                    insertar_2(root.izquierda, elemento);
            }
        } else {
            return;
        }
    }
    
    public boolean pertenece (T elem){
        return comparador(raiz, elem);
    }
    
    public boolean comparador (Nodo actual , T elem){
        if (actual == null){
            return false;
        } 
        if (actual.value.compareTo(elem) > 0){
            // si el actual es más grande, busco en el subárbol a izquierda
            return comparador(actual.izquierda, elem);

        } else if (actual.value.compareTo(elem) < 0){
            // si el actual es más chico, busco en el subárbol a derecha
            return comparador(actual.derecha, elem);

        } else {
            return true;
        }
        
    }
    public void eliminar(T elem){
        // para eliminar un nodo tenemos casos : 
        // el caso 1 : es una hoja, buscamos al padre y vuela
        // el caso 2 : tiene un hijo (sea "p" el padre si existe y su hijo "h") reemplazamos la conexión ("p",borrado) por ("p","h")
        // el caso 3 : tiene dos hijos ahora lo vemos 
        // el caso 4 : es la raiz
        // como tengo que recorrer mi árbol, empiezo por la raiz
        raiz = search_and_destroy(raiz, elem);
    }

    public Nodo search_and_destroy(Nodo actual, T elem){
        // para no estar todo el tiempo metiendo la comparación en el if ...
        int compare = elem.compareTo(actual.value);

        // search
        if (compare > 0){
            actual.derecha = search_and_destroy(actual.derecha, elem);
        } else if (compare < 0){
            actual.izquierda = search_and_destroy(actual.izquierda, elem);
        }
        // destroy 
        else {
            // caso 1
            if(actual.derecha == null && actual.izquierda == null){
                if (actual.padre.value.compareTo(actual.value) > 0) {
                    actual.padre.izquierda = null;
                } else {
                    actual.padre.derecha = null;
                }
            } 
            // caso 2 
            else if (actual.derecha == null && actual.izquierda != null){
                if (actual.padre.value.compareTo(actual.value) > 0) {
                    actual.padre.izquierda = null;
                } else {
                    actual.padre.derecha = null;
                }
            }
        }
        
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
