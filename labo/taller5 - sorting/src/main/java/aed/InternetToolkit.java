package aed;

public class InternetToolkit {
    public InternetToolkit() {
        // hay que implemantar algo aca??
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        // vamos a implementar insertion sort 
        
        for (int i = 1, i < fragments.length , i++){
            Fragment actual = this.fragments[i];
            int j = i - 1;

            while (j >= 0 && (this.fragments[j].compareTo(actual) > 0)){
                this.fragments[j + 1] = this.fragments[j]
                this.fragments[j] = actual;
                j--;
            }
        } 

        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        // vamos a hacer un max heap con las rutas de mayor trafico y extraer los k routers mayores que superan el umbral
        Heap heap_de_routers = new Heap[routers]
        return null;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        // IMPLEMENTAR
        return null;
    }

}
