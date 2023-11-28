package aed;

public class Heap {
    public Nodo[] Heap;
    public int size;

    public class Nodo {
        int valor;

        public Nodo(int value){
            this.valor = value;
        }
    }

    public Heap(Fragment[] arr){
        int i = 0;
        size = arr.length - 1;
        Heap = new Nodo[size];

        while(i < size){
            if (arr[i].compareTo(umbral) > 0){
                // si el valor supera el umbral, lo metemos en el heap  
                Heap[i] = new Nodo(arr[i]);
            }
            i++;
        }
        // O(n)
    }

    private void construirMaxHeap(){
        for (int i = (this.size / 2) - 1; i >= 0 ; i--) {
            maxHeap(i);
        }
    }
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
            // Swap the elements at positions i and largest
            intercambiar(i, largest);
            maxHeap(largest);
        }
    }

    public void modificarRaiz(int i){
        this.Heap[0].votos = this.Heap[0].voto_total / i;
        maxHeap(0);
    }

    public void intercambiar(int pos_padre, int pos_hijo)
    {
        Nodo temporal;
        temporal = Heap[pos_padre];
        Heap[pos_padre] = Heap[pos_hijo];
        Heap[pos_hijo] = temporal;
    }
}