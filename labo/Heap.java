// la propiedad del heap es : el nodo padre es mayor o igual a sus hijos
public class Heap {
    // se representa a partir de un array 
    // la posición 0 es null se comienza siempre en 1
    public int[] array;
    public int tamaño;
    
    public Heap(int i){
        this.tamaño = i;
        this.array = new int[tamaño + 1];
    }

    public boolean vacio(){
        if (this.tamaño == 0){return true;}
        else{return false;}
    }

    // nos interesa la relación entre nodos
    // sea el array = [1,2,3,4,5,6]
    // si tomo el número 2 : su padre está en el índice 1, sus hijos son el 4 y el 5 que ocupan las posiciones 4 y 5 del arreglo
    // dicho esto:
    public int hijo_izquierdo(int i){return 2 * i;}
    public int hijo_derecho(int i){return 2 * i + 1;}
    public int padre(int i){return Math.floorDiv(i, 2);}
    
    // tenemos las operaciones de inserción y de eliminación 
    // para ambos casos tenemos que mantener la propiedad del heap
    // en el caso de la inserción que será en la última posición, tendremos que comparar recursivamente con su padre y en caso de ser mayor, cambiarlo por él
    // en el caso de la eliminación que será de la raíz, tenemos que reemplazar por el último elemento y luego comparamos recursivamente con sus hijos.

    public void insertar(int i){
        this.tamaño++;
        this.array[tamaño] = i;
        // mientras que el hijo sea mayor al padre, andá swapeando
        while (padre(i) > 0 && padre(i) < i){
            intercambiar(padre(i), i);
            i = padre(i);
        }
    }

    public void intercambiar(int posicion_padre, int posicion_hijo){
        int temporal = this.array[posicion_padre];
        this.array[posicion_padre] = this.array[posicion_hijo];
        this.array[posicion_hijo] = temporal;
    }

    public int eliminar(){
        int eliminado = this.array[1];
        intercambiar(1, tamaño);
        tamaño--;
        heapify(1);
        return eliminado;
    }

    public void heapify(int i){
        // el proceso de mantener la propiedad de heap comparando recursivamente con los hijos del nodo
        int actual = this.array[i];
        int indice = i;
        int hijo_izquierdo = hijo_izquierdo(i);
        int hijo_derecho = hijo_derecho(i);

        // vamos a asumir que tiene hijo izquierdo como minimo
        if (hijo_izquierdo <= this.tamaño){
            if (actual < this.array[hijo_izquierdo]){ indice = hijo_izquierdo;}
        }
        if (hijo_derecho <= this.tamaño){
            if (this.array[hijo_izquierdo] < this.array[hijo_derecho]){ indice = hijo_derecho;}
        }
        if (indice != i){
            intercambiar(i, indice);
            heapify(indice);
        }
    }

    // un ejemplo de uso es el heapsort, un algoritmo de ordenamiento en el que se elimina
    public int[] heapsort(){
        int[] res = new int[tamaño];
        for(int i = 1; i <= this.tamaño; i++){
            res[i] = eliminar();
        }
        return res;
    }

    // qué pasa por ejemplo si ya tenemos un arreglo de números que no cumplen con la propieda del heap?
    public void heap(int[] arreglo){
        this.tamaño = arreglo.length;
        
        for (int i = 1; i < arreglo.length; i++){
            this.array[i] = arreglo[i - 1];
        }

        // y ahora que tengo el arreglo quiero desde una posición específica ir corroborando la prop del heap
        // esa posición es j, el último padre
        int j = Math.floorDiv(tamaño, 2);
        for(int i = j; i > 0; i--){
            heapify(i);
        }
    }
}