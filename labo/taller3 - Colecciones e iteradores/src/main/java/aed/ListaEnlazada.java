package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private int longitud;
    // me va a interesar apuntar al primer y último elemento
    private Nodo primero;
    private Nodo ultimo;
    // queremos guardar los datos de tipo T en una lista
    
    private class Nodo {
    // Completar
        private Nodo anterior;
        private Nodo siguiente;
        private T value;
        
        public Nodo (T valor){
            value = valor;
            anterior = null;
            siguiente = null;
        }
        
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitud = 0;

    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        // lo convierto a elem en el objeto nodo
        Nodo nuevo = new Nodo(elem);
        nuevo.anterior = null;
        
        // y ahora lo vamos a modificar teniendo en cuenta si hay o no elementos en la ListaEnlazada
        if (longitud == 0){
            nuevo.siguiente = null;
            primero = nuevo;
            ultimo = nuevo; 
        } else {
            // si ya hay nodos existentes ...
            nuevo.siguiente = primero;
            primero.anterior = nuevo;

            primero = nuevo;     
        }
        longitud ++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        // el siguiente al nuevo es siempre null
        nuevo.siguiente = null;

        // en caso de agregar atrás tenemos las mismas premisas: 
        // si no hay elementos 
        if (longitud == 0){
            nuevo.anterior = null;
            // los punteros apuntan al nuevo
            primero = nuevo;
            ultimo = nuevo;
      
        }
        // si hay elementos
        else {
            Nodo actual = primero;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.anterior = actual;
            
            ultimo = nuevo;            
        }
        longitud ++ ;
    }

    public T obtener(int i) {
       Nodo actual = primero;
       int j;
       j = 0;
       while (j != i){
        actual = actual.siguiente;
        j ++;
       }
       return actual.value;
    }

    public void eliminar(int i) {
        Nodo target = primero;
        Nodo ant = primero;
        // recorremos la lista para obtener ant como el anterior al que queremos borrar
        for (int j = 0; j < i; j ++){
            ant = target;
            target = target.siguiente;
        }
        if (i == 0){
            // si queremos eliminar el primer elemento...
            primero = target.siguiente; 
        } else {
            // en este caso actual muere y por tanto al ant le asignamos actual.siguiente
            ant.siguiente = target.siguiente;
        }
        longitud --;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nodo = primero;
        Nodo modificacion = new Nodo(elem);
        for (int i = 0; i < indice; i++){
            nodo = nodo.siguiente;
        }
        if ( indice == 0){
            primero = new Nodo(elem);
        } else {
            nodo.value = modificacion.value;
        }
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        Nodo actual = primero;
        for(int i = 0; i < longitud; i ++){
            copia.agregarAtras(actual.value);
            actual = actual.siguiente;
        }
        return copia; 
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        longitud = lista.longitud;
        primero = lista.primero;
        ultimo = lista.ultimo;
    }
    
    @Override
    public String toString() {
        StringBuffer lista = new StringBuffer();
        Nodo fst = primero;
        for (int i = 0 ; i < longitud; i++){
            if (i == 0){
            lista.append("[" + fst.value + ", ");
            fst = fst.siguiente;
            } else if (i == longitud - 1){
                lista.append(fst.value + "]");
            } else {
                lista.append(fst.value + ", ");
                fst = fst.siguiente;
            }
        }
        return lista.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados 
        private int puntero;
        // inicializo ListaIterador en 0
        ListaIterador(){
            puntero = 0;
        }
        // en la última pos no hay siguiente
        public boolean haySiguiente() {
	       return puntero != longitud;
        }
        // en la primera pos no hay anterior
        public boolean hayAnterior() {
	       return puntero != 0;
        }
        // tenemos el método obtener
        public T siguiente() {
	       int i = puntero;
           // modificamos el observador 
           puntero = puntero + 1;
           return obtener(i);
        }
        
        public T anterior() {
            int i = puntero;
            // modificamos el observador 
            puntero = puntero - 1;
            return obtener(i-1);
        }
    }
    // este constructor inicializa el iterador como una nueva ListaIterador
    public Iterador<T> iterador(){
        Iterador<T> iterador = new ListaIterador();
        return iterador;
    }
}

