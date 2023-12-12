package aed;

public class InternetToolkit {
    public InternetToolkit() {
    }


    public Fragment[] tcpReorder(Fragment[] fragments) {
        for (int i = 1; i < fragments.length; i ++){
            int j = i - 1;
            Fragment elem = fragments[i]; 
            while(j>= 0 && fragments[j].compareTo(elem) > 0){
                fragments[j + 1] = fragments[j];
                j --;
                fragments[j + 1] = elem;
            }
        }
        return fragments;
    }

    public int superanElUmbral(Router[] routers, int umbral){
        int res = 0;
        for(int i = 0; i < routers.length; i++){
            if (routers[i].getTrafico() > umbral){
                res++;
            }
        }
        return res;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        int cantSuperanUmbral = superanElUmbral(routers, umbral);
        Heap heap = new Heap(routers);
        Router[] res = new Router[Math.min(k, cantSuperanUmbral)];
        for (int i = 0; i < res.length ; i++){
            Router max = heap.maximo();
            heap.descencolar();
            if (max.getTrafico() > umbral){
                res[i] = max;
            } else {
                i--;
            }
        }
        return res;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        IPv4Address[] res = new IPv4Address[ipv4.length];

        for(int i = 0; i < ipv4.length; i++){
            res[i] = new IPv4Address(ipv4[i]);
        }    

        for(int i = 0; i < res.length - 1; i++){
            for(int j = 0; j < res.length - i - 1; j++){
                IPv4Address direc1 = res[j];
                IPv4Address direc2 = res[j+1];
                int octeto = 0;
                while(octeto < 4) {
                    if(direc1.getOctet(octeto) < direc2.getOctet(octeto)){
                        octeto = 4;
                    } else if (direc1.getOctet(octeto) > direc2.getOctet(octeto)){
                        res[j] = res[j+1];
                        res[j+1] = direc1;
                        octeto = 4;
                    } else {
                        octeto++;
                    }
                }
            }
        }
        return res;
    }

}