package aed;

public class Heap{
    private Router[] array;
    private int longitud;

    
    public Heap(Router[] array){

        this.array = new Router[array.length];
        this.longitud = array.length;

        for(int l = 0; l < this.array.length; l++){
            this.array[l] = array[l];
        }

        //reconstruimos con el algoritmo de Floyd
        int j = (longitud - 2) / 2;
        int i = hijo(j);
        while (j >= 0){
            if(this.array[i].compareTo(this.array[j]) > 0){
                heapify(j, i);
            }
            j--;
            i = hijo(j);
        }
    }

    public void heapify (int j, int i){
        while (i < longitud && array[i].compareTo(array[j]) > 0){
            Router padre = array[j]; 
            array[j] = array[i];
            array[i] = padre;
            j = i;
            i = hijo(j);
        }
    }

    public int hijo(int j){
        int i;
        if (2*j + 2 < longitud && j >= 0){
            if (array[2*j + 1].compareTo(array[2*j + 2]) > 0){
                i = 2*j + 1;
            } else {
                i = 2*j + 2;
            }
        } else {
            i = 2*j + 1;
        }
        return i;
    }


    public Router maximo(){
        return array[0];
    }
    
    public void descencolar(){
        if(longitud > 0){
            array[0] = array[longitud - 1];
            longitud --;
            heapify(0,hijo(0));
        }
    }
}