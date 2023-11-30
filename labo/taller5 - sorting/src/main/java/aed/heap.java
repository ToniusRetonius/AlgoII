package aed;

public class Heap{

    public Router[] Heap;
    public int size;

    public class Router {
        private int _id;
        private int _trafico;

        public Router(int id, int trafico) {
            _id = id;
            _trafico = trafico;
        }
    }

    public Heap(Router[] arr){
       size = arr.length - 1;
       Heap = new Router[size];

       for (int i = 0; i < size; i++){
            Heap[i] = new Router(arr[i]._id, arr[i]._trafico);
       }
        // arma el heap
        // O(n)
        construirMaxHeap();
        // lo hace máx heap
        // en O(n) + O(n log n)
    }

    public void construirMaxHeap(){
        for (int i = (this.size / 2) - 1; i >= 0 ; i--) {
            maxHeap(i);
        }
    }
    public void maxHeap(int i){
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if ((left < size) && Heap[left]._trafico > (Heap[largest]._trafico)){
            largest = left;
        }

        if ((right < size) && Heap[right]._trafico > (Heap[largest]._trafico) ) {
            largest = right;
        }

        if (largest != i) {
            // Swap the elements at positions i and largest
            intercambiar(i, largest);
            maxHeap(largest);
        }
    }

    public void intercambiar(int pos_padre, int pos_hijo){

        Router temporal;
        temporal = Heap[pos_padre];
        Heap[pos_padre] = Heap[pos_hijo];
        Heap[pos_hijo] = temporal;
    }

    public Router desencolar(){
        // la idea sera intercambiar el nodo hoja(minimo) con el max, eliminar la hoja y poner en el array de res el valor del max desecolado
        Router max = Heap[0];
    
        this.intercambiar(size,0);
        this.size --;
        this.maxHeap(0);

        return max;

    }

    public Router[] HeapSort(int tam){
        Router[] res = new Router[tam];

        for (int i = 0 ; i <= tam; i++){
            res[i] = this.desencolar();
        }

        return res;
    }
}