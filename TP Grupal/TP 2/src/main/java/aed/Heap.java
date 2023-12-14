package aed;
public class Heap {
    public Nodo[] Heap;
    public int size;
    public class Nodo{
        int votos;
        int indice;
        int voto_total;

        public Nodo(int value, int i, int j){
            votos = value;
            indice = i;
            voto_total = j;
        }
    }
    // Complejidad: O(n * log (n)) 
    //! Si construirMaxHeap es O(n logn) entonces esto no puede ser O(n) (corregido)
    public Heap(int[] arr){
        int i = 0;
        size = arr.length - 1;
        Heap = new Nodo[size];

        while(i < size){
            Heap[i] = new Nodo(arr[i],i,arr[i]);
            i++;
        }
        // cuando se terminan de agregar, reacomodo
        construirMaxHeap();
    }

    // Complejidad: O(n)
    //! Floyd no es O(n log n) (corregido)
    private void construirMaxHeap(){
        for (int i = (this.size / 2) - 1; i >= 0 ; i--) {
            maxHeap(i);
        }
    }
    // Complejidad: O(log n)
    public void maxHeap(int i){
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < size && Heap[left].votos > Heap[largest].votos) {
            largest = left;
        }

        if (right < size && Heap[right].votos > Heap[largest].votos) {
            largest = right;
        }

        if (largest != i) {
            // intercambiar el elemento y heapificar
            intercambiar(i, largest);
            maxHeap(largest);
        }
    }
    // Complejidad: O(log n)
    //! maxHeap no es O(1) (corregido)
    public void modificarRaiz(int i){
        this.Heap[0].votos = this.Heap[0].voto_total / i;
        maxHeap(0);
    }
    // Complejidad: O(1)
    //! Todas esas operaciones son O(1)(corregido)
    public void intercambiar(int pos_padre, int pos_hijo)
    {
        Nodo temporal;
        temporal = Heap[pos_padre];
        Heap[pos_padre] = Heap[pos_hijo];
        Heap[pos_hijo] = temporal;
    }
}

//! No termina de caracterizar a un heap y habla de objetos que no pertenecn a la clase heap en el irep. No es un invariante para esta clase
/* Invariante de representación (para Heap)
     Para cada elemento de _nombreDistrito va a haber un heap correspondiente con los votos de diputados
     Todos los heaps van a tener igual cantidad de nodos que elementos de _nombrePartido
     //! Esto no es necesariamente cierto, no tiene por qué estar ordenado de mayor a menor o al menos digan que entienden por ordenado.
     El heap siempre va estar ordenado de mayor a menor y el mayor siempre va a ser la raiz, representando a el siguiente partido a tener una banca asignada para si
     El size del Heap tiene que ser mayor a 0.
     Para cada nodo en el heap, su valor de votos va a ser mayor o igual que el valor de los votos en sus hijos.
*/
