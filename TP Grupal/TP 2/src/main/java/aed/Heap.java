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

        /*
        Invariante de representación (para Nodos)
            El valor de votos tendrá que ser mayor o igual a cero.
            El valor de indice debera ser mayor o igual a cero.
            El valor de voto_total deberá ser mayor o igual a cero.
        */
    }


    // Complejidad: O(n * log (n)) 
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
    public void modificarRaiz(int i){
        this.Heap[0].votos = this.Heap[0].voto_total / i;
        maxHeap(0);
    }

    // Complejidad: O(1)
    public void intercambiar(int pos_padre, int pos_hijo) {
        Nodo temporal;
        temporal = Heap[pos_padre];
        Heap[pos_padre] = Heap[pos_hijo];
        Heap[pos_hijo] = temporal;
    }
}

/* Invariante de representación (para Heap)
     Para todo nodo dentro del Heap, si el mismo tiene hijos, entonces tendrán que ser menores o iguales en votos (Dado que se implementa Max Heap).
     El Heap cumple la condición de izquierdista (siempre se inserta el siguiente nodo de izquierda a derecha).
     El size del Heap tiene que ser mayor a 0.
*/
