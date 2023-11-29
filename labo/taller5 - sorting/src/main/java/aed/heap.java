package aed;

public class Heap{

    public Nodo[] Heap;
    public int size;
    // cambiar tipo

    public class Nodo {
        Router valor;

        public Nodo(Router val){
            this.valor = val;
        }
    }

    public Heap(Router[] arr){
        int i = 0;
        int len = arr.length - 1;
        Nodo[] Heap = new Nodo[size];

        while(i < len){
            Heap[i] = new Nodo(arr[i]);
            i++;
        }
        // arma el heap
        // O(n)
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

        if ((left < size) && Heap[left].valor.compareTo(Heap[largest].valor) > 0){
            largest = left;
        }

        if ((right < size) && Heap[right].valor.compareTo(Heap[largest].valor) > 0) {
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

    public Nodo desencolar(){
        // la idea sera intercambiar el nodo hoja(minimo) con el max, eliminar la hoja y poner en el array de res el valor del max desecolado
        Nodo max = Heap[0];
    
        this.intercambiar(size,0);
        this.size --;
        this.maxHeap(0);

        return max;

    }

    public Router[] HeapSort(int tam){
        Router[] res = new Router[tam + 1];

        for (int i = 0 ; i <= tam; i++){
            
        }

        return res;
    }
}