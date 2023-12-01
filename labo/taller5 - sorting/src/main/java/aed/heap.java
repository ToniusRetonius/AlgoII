package aed;

public class Heap{

    public Router[] Heap;
    public int size;

    public Heap(aed.Router[] arr){
       size = arr.length;
       Heap = new Router[size];
       int i = 0;

       while (i < size) {
            Heap[i] = arr[i];
            i++;
       }

       construirMaxHeap();

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
        if (this.size == 0){
            return null;
        } else{
            Router max = Heap[0];
        
            this.intercambiar(size - 1 ,0);
            this.maxHeap(0);
            this.size --;

            return max;
        }
    }
}