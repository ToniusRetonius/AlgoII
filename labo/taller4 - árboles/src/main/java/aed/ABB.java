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
        Nodo nuevo = new Nodo(elem);
        Nodo actual = raiz;
        int i = 0;
        if (!this.pertenece(elem)){
            while (i < longitud){
                if (actual.value.compareTo(elem) < 0 && actual.derecha != null){
                    actual = actual.derecha;
                } else if (actual.value.compareTo(elem) > 0 && actual.izquierda != null) {
                    actual = actual.izquierda;
                }
                i ++;
            }
            
            if (longitud == 0){
                raiz = new Nodo(elem);
            } else if (actual.value.compareTo(elem) < 0){
                actual.derecha = nuevo;
                nuevo.padre = actual;
            }else if (actual.value.compareTo(elem) > 0){
                actual.izquierda = nuevo;
                nuevo.padre = actual;
            }
            longitud ++;
        }
    }

    public boolean pertenece(T elem){
        Nodo actual = raiz;
        boolean res = false;
        int i = 0;
        while (i < longitud){
            if (actual.value.compareTo(elem) == 0){
                res = true;
            } else if (actual.value.compareTo(elem) > 0 && actual.izquierda != null){
                actual = actual.izquierda;
            } else if (actual.derecha != null) {
                actual = actual.derecha;
            }
            i ++;
        }
        return res;
    }
    public void eliminar(T elem){
        Nodo actual = raiz;
        if (this.pertenece(elem)){
            //search
            while (actual.value.compareTo(elem) != 0){
                if (actual.value.compareTo(elem) > 0){
                    actual = actual.izquierda;
                } else {
                    actual = actual.derecha;
                }
            }

            // Caso 1 : hoja
            if ( actual.izquierda == null && actual.derecha == null){
                if (actual.padre == null){
                    raiz.value = null;
                } else {
                    if (actual.padre.value.compareTo(elem) > 0){
                        actual.padre.izquierda = null;
                    } else {
                        actual.padre.derecha = null;
                    } 
                }
                
            //Caso 2 : hijo a derecha
            } else if (actual.izquierda == null && actual.derecha != null){
                if (actual.padre == null){
                    actual.derecha.padre = null;
                    raiz = actual.derecha;//
                } else if (actual.padre.value.compareTo(elem) > 0){
                    actual.padre.izquierda = actual.derecha;
                    actual.derecha.padre = actual.padre;
                } else {
                    actual.padre.derecha = actual.derecha;
                    actual.derecha.padre = actual.padre;
                }

            //Caso 2 : hijo a izquierda
            } else if (actual.izquierda != null && actual.derecha == null){
                if (actual.padre == null){
                    actual.izquierda.padre = null;
                    raiz = actual.izquierda;//
                } else if (actual.padre.value.compareTo(elem) > 0){
                    actual.padre.izquierda = actual.izquierda;
                    actual.izquierda.padre = actual.padre;
                } else {
                    actual.padre.derecha = actual.izquierda;
                    actual.izquierda.padre = actual.padre;
                }
            
            //Caso en que tiene ambas descendencias una Mayor y otra Menor
            } else {
                //mínimo = el minimo de la derecha (mínimo del sub-Arbol derecho)
                Nodo min = minimoDelSubABB(actual.derecha);

                eliminar(min.value);
                longitud ++; 

                //asignar padre al mínimo
                if (actual.padre == null){
                    min.padre = null;
                    raiz = min;//
                } else if (actual.padre.value.compareTo(elem) > 0){
                    actual.padre.izquierda = min; 
                    min.padre = actual.padre;    
                } else {
                    actual.padre.derecha = min;
                    min.padre = actual.padre; 
                }

                //asignar a izquierda
                actual.izquierda.padre = min;
                min.izquierda = actual.izquierda;

                //asignar a derecha
                if (actual.derecha != null){
                    actual.derecha.padre = min;
                    min.derecha = actual.derecha;
                }

            }
            longitud --;
        }    
    }

    public Nodo minimoDelSubABB(Nodo actual){
        while(actual.izquierda != null){
            actual = actual.izquierda;
        }
        return actual;

    }

    public String toString(){
        String res = "";
        if (longitud == 0){
            res = "{ }";
        } else {
            res = "{" + minimo().toString();
            Nodo actual = minimoDelSubABB(raiz);
            while (actual.value.compareTo(maximo()) != 0){
                if (actual.derecha != null){
                    actual = minimoDelSubABB(actual.derecha);
                } else {
                    Nodo siguiente = actual.padre;
                    while(siguiente.value.compareTo(actual.value) < 0){
                        siguiente = siguiente.padre;
                    }
                    actual = siguiente;
                }
                res = res + "," + actual.value.toString();
            }
            res = res + "}";
        }
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        ABB_Iterador(){
            _actual = null;
        }

        public boolean haySiguiente() {            
            return maximo() != _actual ;
        }
    
        public T siguiente() {
            Nodo siguiente = null;
            if (_actual == null){
                siguiente = minimoDelSubABB(raiz);
            } else if (haySiguiente()){
                if (_actual.derecha != null){
                    siguiente = minimoDelSubABB(_actual.derecha);
                } else {
                    siguiente = _actual.padre;
                    while(siguiente.value.compareTo(_actual.value) < 0){
                        siguiente = siguiente.padre;
                    }
                }
            }
            _actual = siguiente;

            return siguiente.value;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
