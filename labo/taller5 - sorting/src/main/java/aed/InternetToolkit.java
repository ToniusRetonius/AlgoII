package aed;


public class InternetToolkit {
    public InternetToolkit() {
        // hay que implemantar algo aca??
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        // vamos a implementar insertion sort 
        
        for (int i = 1 ; i < fragments.length ; i++){
            Fragment actual = fragments[i];
            int j = i - 1;

            while (j >= 0 && (fragments[j].compareTo(actual) > 0)){
                fragments[j + 1] = fragments[j];
                fragments[j] = actual;
                j--;
            }
        } 

        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        // para que nos se nos vata de complejidad queremos que construir un heap con los valores que superen el umbral
        // para luego devolver los k que cumplen 
        // complejidad esperada O(n + k log n)
        Router[] filtrada = new Router[routers.length];

        // me armo un array con todos los elementos que cumplen el umbral 
        // complejidad : O (n)
       for (int i = 0; i < routers.length; i++){
            if (routers[i].getTrafico() >= umbral) {
                filtrada[i] = routers[i];
            }
       }
        
       // ahora que tengo n elementos que cumplen el umbral, quiero los k mayores
       // para eso puedo hacer heapsort en (k * log n)
       Heap f = new Heap(filtrada);

       Router[] ordenada = new Router[filtrada.length];

       for (int i = 0; i < filtrada.length; i++){
           ordenada[i] = f.desencolar();
        }
        int res_length;
        if (k > 0 && umbral > 0){
            res_length = Math.min(k, umbral);
        } else if (umbral <= 0){
            res_length = k;
        } else {
            res_length = 0;
        }

        Router[] res = new Router[res_length];

        for (int i = 0; i <  res_length ; i++){
            res[i] = ordenada[i];
        }

        return res;

    }


    public IPv4Address[] sortIPv4(String[] ipv4) {
        // IMPLEMENTAR
        return null;
    }

}
