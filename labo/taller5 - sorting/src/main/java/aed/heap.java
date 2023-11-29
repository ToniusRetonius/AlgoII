package aed;

public class Heap {
    public Nodo[] Heap;
    public int size;
    // cambiar tipo
    public class Nodo {
        Fragment valor;

        public Nodo(Fragment val){
            this.valor = val;
        }
    }

    public crearHeap(Fragment[] arr){
        int i = 0;
        length = arr.length - 1;
        H = new Nodo[size];

        while(i < size){
            Heap[i] = new Nodo(arr[i]);
            i++;
        }
        // arma el heap
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

        if (left < size && Heap[left].valor > Heap[largest].valor) {
            largest = left;
        }

        if (right < size && Heap[right].valor > Heap[largest].valor) {
            largest = right;
        }

        if (largest != i) {
            // Swap the elements at positions i and largest
            intercambiar(i, largest);
            maxHeap(largest);
        }
    }

    public void intercambiar(int pos_padre, int pos_hijo){

        Nodo temporal;
        temporal = Heap[pos_padre];
        Heap[pos_padre] = Heap[pos_hijo];
        Heap[pos_hijo] = temporal;
    }

    public void desencolar(){
        // la idea sera intercambiar el nodo hoja(minimo) con el max, eliminar la hoja y poner en el array de res el valor del max desecolado
        
        hoja = Heap[size]

    }
}