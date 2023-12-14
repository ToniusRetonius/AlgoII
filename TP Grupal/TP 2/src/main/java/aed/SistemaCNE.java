package aed;

//! No pasa el test obteneresultadosDiputadosVariosDistritos (corregido) y tampoco procesan correctamente una secuencia del estilo: registrar mesa, obtener diputados, registrar mesa, obtener diputados, todo sobre el mismo distrito

public class SistemaCNE {
    private String[] _nombresPartidos;
    // [str, str, ...] <- indice = id.partido
    private String[] _nombresDistritos;
    // [str, str, ...] <- indice = id.partido
    private int[] _diputadosPorDistrito;
    // [int, int, ...] <- indice = id.distrito
    private int[] _ultimasMesasDistritos;
    // [int, int, ...] <- indice = id.distrito (rango mesas) cada posicion tiene (int - 1) mesas
    private int[] votos_presidente;
    // los votos presidenciales tiene por idPartido la #votos presidenciales
    public int[][] votos_diputados;
    // los votos diputados tiene por indice de matriz el distrito y por cada distrito tomamos el idPartido asignando #votosDiputado
    private int[] registroMesa;
    public Heap[] heap_por_distrito;
    public int[] fueVisitado;
    public int[] suma_votos;
    public int[][] bancas_totales;
    public int[] votosCumplenUmbral;

    public class VotosPartido{
        private int presidente;
        private int diputados;

        //! Falta irep(duda ,lo pusimos abajo)

        // Complejidad: O(1)
        VotosPartido(int presidente, int diputados) {
            this.presidente = presidente;
            this.diputados = diputados;
        }

        // Complejidad: O(1)
        public int votosPresidente() {
            return presidente;
        }
        // Complejidad: O(1)
        public int votosDiputados() {
            return diputados;
        }
    }


    // Complejidad: O(D*P)
    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        // inicializamos los atributos privados
        // Complejidad: O(P)
        _nombresPartidos = new String[nombresPartidos.length];
        // Complejidad: O(D)
        _diputadosPorDistrito = new int[nombresDistritos.length];
        // Complejidad: O(D)
        _nombresDistritos = new String[nombresDistritos.length];
        // Complejidad: O(D)
        _ultimasMesasDistritos = new int[nombresDistritos.length];
        // Complejidad: O(P)
        votos_presidente = new int[nombresPartidos.length];
        // Complejidad: O(D*P)
        votos_diputados = new int[nombresDistritos.length][nombresPartidos.length];
        // Complejidad: O(P)
        registroMesa = new int[ultimasMesasDistritos[ultimasMesasDistritos.length - 1]];
        // Complejidad: O(D)
        heap_por_distrito = new Heap[nombresDistritos.length];
        // Complejidad: O(1)
        suma_votos = new int[3];
        // Complejidad: O(D)
        fueVisitado = new int[nombresDistritos.length];
        // Complejidad: O(D*P)
        bancas_totales = new int[nombresDistritos.length][nombresPartidos.length];
        // Complejidad: O(P)
        votosCumplenUmbral = new int[nombresPartidos.length];


        int i = 0;
        int j = 0;

        
        //! Falta la complejidad de estos dos while (corregido)
        // Complejidad: O(P)
        while (i < nombresPartidos.length) {
            _nombresPartidos[i] = nombresPartidos[i];
            i++;
        }
        // Complejidad O(D)
        while (j < nombresDistritos.length) {
            _diputadosPorDistrito[j] = diputadosPorDistrito[j];
            _nombresDistritos[j] = nombresDistritos[j];
            _ultimasMesasDistritos[j] = ultimasMesasDistritos[j];
            j++;
        }
    }

    // Complejidad: O(1)
    public String nombrePartido(int idPartido) {
        return _nombresPartidos[idPartido];
    }

    //Complejidad: O(1)
    public String nombreDistrito(int idDistrito) {
        return _nombresDistritos[idDistrito];
    }

    //Complejidad: O(1)
    public int diputadosEnDisputa(int idDistrito) {
        return _diputadosPorDistrito[idDistrito];
    }

    //ESTA BUSQUEDA BINARIA SOLO SIRVE PARA ESTOS DOS ENUNCIADOS EN ESPECIFICO , SI QUIERO REUTILIZARLA NO DEBERIA ASIGNAR 0 A IZQ
    public int busquedaBinaria(int idMesa ,int izquierda , int derecha , int medio ){
        if (idMesa >= _ultimasMesasDistritos[0]) {
            while (derecha - izquierda > 1) {
                if (idMesa < _ultimasMesasDistritos[medio]) {
                    derecha = medio;
                } else {
                    izquierda = medio;
                }
                medio = (derecha + izquierda) / 2;
            }

            if (idMesa >= _ultimasMesasDistritos[izquierda]) {
                return (izquierda + 1);
            } else {
                return izquierda;
            }
        }else{
            return 0; 
        }
    }    

    // Complejidad: O(log(P))
    public String distritoDeMesa(int idMesa) {
        int izquierda = 0;
        int derecha = this._ultimasMesasDistritos.length - 1;
        int medio = derecha / 2;
        return _nombresDistritos[busquedaBinaria(idMesa, izquierda, derecha, medio)];
    }

    //Complejidad: O(P + log(D))
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        int izquierda = 0;
        int derecha = this._ultimasMesasDistritos.length - 1;
        int medio = derecha / 2;

        //! Reusen el codigo que hicieron que hace exactamente esto, no copien y peguen la busqueda binaria, llamen la funcion (corregido)
        // Complejidad: O(log(D))
        int res = busquedaBinaria(idMesa, izquierda, derecha, medio);
        


        // Complejidad: O(P)
        int i = 0;
        int total_diputados = 0;
        while (i < actaMesa.length) {
            this.votos_presidente[i] += actaMesa[i].presidente;
            this.votos_diputados[res][i] += actaMesa[i].diputados;
            total_diputados += votos_diputados[res][i];
            i++;
        }
        this.registroMesa[res] = idMesa;

        // Complejidad: O(P)
        int j = 0;
        while(j < votos_diputados[res].length - 1 ){
            if(((votos_diputados[res][j] *100) / total_diputados) >= 3 ){
                votosCumplenUmbral[j] = votos_diputados[res][j];
            }
            j++;
        }

        // Complejidad: O(P)
        heap_por_distrito[res] = new Heap(this.votosCumplenUmbral);

        // Utilizado para hayBallotage();
        // Complejidad: O(P)
        int m = 0;
        int max = 0;
        int max2 = 0;
        int total = 0;
        while (m < votos_presidente.length ) {
            if (votos_presidente[m] > max) {
                max2 = max;
                max = votos_presidente[m];
            } else {
                if (votos_presidente[m] > max2) {
                    max2 = votos_presidente[m];
                }
            }
            total += votos_presidente[m];
            m++;
        }
        suma_votos[0] = max;
        suma_votos[1] = max2;
        suma_votos[2] = total;
    }


    // Complejidad: O(1)
    public int votosPresidenciales(int idPartido) {
        return votos_presidente[idPartido];
    }

    // Complejidad: O(1)
    public int votosDiputados(int idPartido, int idDistrito) {
        return votos_diputados[idDistrito][idPartido];
    }


    //Complejidad: O(D_d * log(P))
    public int[] resultadosDiputados(int idDistrito) {
        int _cantBancas = _diputadosPorDistrito[idDistrito];
        Heap distrito = heap_por_distrito[idDistrito];
        int [] bancas_partido = new int[(votos_diputados[idDistrito]).length];

        if (this.fueVisitado[idDistrito] == 1){
            return this.bancas_totales[idDistrito];
        } else {
            Heap.Nodo raiz;
            int indice;

            //! No es esa la complejidad del ciclo (corregido)
            //! Por qué ponen k y no directamente un 0 (corregido)
            while (0 < _cantBancas ){
                raiz = distrito.Heap[0];
                indice = raiz.indice;
                bancas_partido[indice]++;
                distrito.modificarRaiz(bancas_partido[indice] + 1);
                _cantBancas--;
            }
            bancas_totales[idDistrito] = bancas_partido;
            fueVisitado[idDistrito] = 1;
        }
        return bancas_partido;
    }

    //Complejidad: O(1)
    public boolean hayBallotage() {
        if (((suma_votos[0] * 100) / suma_votos[2]) >= 45){
            return false;
        }else if (((suma_votos[0] * 100) / suma_votos[2]) >= 40 && (((suma_votos[0] - suma_votos[1]) * 100) / suma_votos[2]) > 10){
            return false;
        } else {
            return true;
        }
    }
}

/*
 Invariantes de representación (para Sistemas)
 - para la construcción de VotosPartido se deben respetar valores de enteros válidos para presidente y diputados
 - _diputadosPorDistrito ,_nombresDistritos , _ultimaMesasDistritos , fueVisitado, bancas_totales tienen el mismo tamaño que nombresDistritos.
 - Todos los elementos de _nombresPartidos son distintos entre sí.
 - Todos los elementos de _diputadosPorDistrito son mayor a 0.
 - Todos los elementos de _ultimasMesasDistrito son mayor a 0 y es estrictamente creciente (_ultimasMesasDistrito[i] < _ultimasMesasDistrito[i+1]).
 - votos_presidente y cada sublista de votos_diputados tienen el mismo tamaño.
 - Para suma_votos el tamaño siempre va a ser igual a 3. El primer elemento siempre va a ser igual al elemento maximo de votos presidencial,
 - El segundo elemento al segundo maximo y el tercero va a ser a la suma total de los votos.
 - Todos los elementos de fueVisitado son o 0 o 1.
 - Todos los elementos de bancas_totales tienen el mismo tamaño que nombresPartidos.
 - Para cada elemento de _nombreDistito va a haber un heap correspondiente con los votos de diputados.
 - Todos los heaps van a tener igual cantidad de nodos que elementos de _nombrePartidos.
 - _bancasPartido siempre va a tener la misma longitud que _nombrePartidos y va a ver tantas _bancasPartidos dentro de _bancasTotales como distritos en el sistema
 - Todo nodo guarda su respectivo indice (original) en la lista _nombrepartido, un valor con los votos totales que siempre va a ser igual a la cantidad de votos que el partido recibio en ese distrito para los diputados y un valor que cambia durante la asignación de bancas.
 - _bancasPartido siempre va tener la misma longitud que nombre partidos.
 - _bancas_totales va a tener tantos elementos (_bancasPartido) como distritos en el sistema.

*/