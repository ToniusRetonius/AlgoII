package aed;

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

    public class VotosPartido {
        private int presidente;
        private int diputados;

        VotosPartido(int presidente, int diputados) {
            this.presidente = presidente;
            this.diputados = diputados;
        }

        public int votosPresidente() {
            return presidente;
        }

        public int votosDiputados() {
            return diputados;
        }
    }

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        // inicializamos los atributos privados
        _nombresPartidos = new String[nombresPartidos.length];
        _diputadosPorDistrito = new int[nombresDistritos.length];
        _nombresDistritos = new String[nombresDistritos.length];
        _ultimasMesasDistritos = new int[nombresDistritos.length];
        votos_presidente = new int[nombresPartidos.length];
        votos_diputados = new int[nombresDistritos.length][nombresPartidos.length];
        registroMesa = new int[ultimasMesasDistritos[ultimasMesasDistritos.length - 1]];
        heap_por_distrito = new Heap[nombresDistritos.length];
        suma_votos = new int[3];
        fueVisitado = new int[nombresDistritos.length];
        bancas_totales = new int[nombresDistritos.length][nombresPartidos.length];
        votosCumplenUmbral = new int[nombresPartidos.length];


        int i = 0;
        int j = 0;

        fueVisitado = new int[nombresDistritos.length];

        // observamos que en el peor de los casos mi primer while itera hasta |nombresPartidos| = P => O(P)
        while (i < nombresPartidos.length) {
            _nombresPartidos[i] = nombresPartidos[i];
            i++;
        }

        // observamos que en el peor de los casos mi segundo while itera hasta nombresDistritos = D => O(D)
        while (j < nombresDistritos.length) {
            _diputadosPorDistrito[j] = diputadosPorDistrito[j];
            _nombresDistritos[j] = nombresDistritos[j];
            _ultimasMesasDistritos[j] = ultimasMesasDistritos[j];
            j++;
        }

        //Complejidad: O(P + D)
    }


    public String nombrePartido(int idPartido) {
        return _nombresPartidos[idPartido];

        //Complejidad: O(1)
    }

    public String nombreDistrito(int idDistrito) {
        return _nombresDistritos[idDistrito];

        //Complejidad: O(1)
    }

    public int diputadosEnDisputa(int idDistrito) {
        return _diputadosPorDistrito[idDistrito];

        //Complejidad: O(1)
    }

    public String distritoDeMesa(int idMesa) {
        int izquierda = 0;
        int derecha = this._ultimasMesasDistritos.length - 1;
        int medio = derecha / 2;

        if (idMesa < _ultimasMesasDistritos[0]) {
            return _nombresDistritos[0];
        } else {
            while (derecha - izquierda > 1) {
                if (idMesa < _ultimasMesasDistritos[medio]) {
                    derecha = medio;
                    medio = (derecha - izquierda) / 2;
                } else {
                    izquierda = medio;
                    medio = (derecha + izquierda) / 2;
                }
            } // O(log(n))

            if (idMesa >= _ultimasMesasDistritos[izquierda]) {
                return _nombresDistritos[izquierda + 1];
            } else {
                return _nombresDistritos[izquierda];
            }
        }

        //Complejidad: O(log(P))
    }

    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        int izquierda = 0;
        int derecha = this._ultimasMesasDistritos.length - 1;
        int medio = derecha / 2;
        int res = 0;

        if (idMesa >= _ultimasMesasDistritos[0]) {
            while (derecha - izquierda > 1) {
                if (idMesa < _ultimasMesasDistritos[medio]) {
                    derecha = medio;
                    medio = (derecha - izquierda) / 2;
                } else {
                    izquierda = medio;
                    medio = (derecha + izquierda) / 2;
                }
            } // O(log(n))

            if (idMesa >= _ultimasMesasDistritos[izquierda]) {
                res = izquierda + 1;
            } else {
                res = izquierda;
            }
        }

        int i = 0;
        int total_diputados = 0;
        while (i < actaMesa.length) {
            this.votos_presidente[i] += actaMesa[i].presidente;
            this.votos_diputados[res][i] += actaMesa[i].diputados;
            total_diputados += votos_diputados[res][i];
            i++;
        } // O(M)
        this.registroMesa[res] = idMesa;

        int j = 0;
        while(j < votos_diputados[res].length - 1 ){
            if(((votos_diputados[res][j] *100) / total_diputados) >= 3 ){
                votosCumplenUmbral[j] = votos_diputados[res][j];
            }
            j++;
        } // O(D)

        heap_por_distrito[res] = new Heap(this.votosCumplenUmbral);


        //utilizado para hayBallotage();
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
        } // O(P)
        suma_votos[0] = max;
        suma_votos[1] = max2;
        suma_votos[2] = total;


        //Complejidad: O(M + log(n))
    }

    public int votosPresidenciales(int idPartido) {
        return votos_presidente[idPartido];
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        return votos_diputados[idDistrito][idPartido];
    }

    public int[] resultadosDiputados(int idDistrito) {
        int[] bancas_partido = new int[_nombresPartidos.length];
        int _cantBancas = _diputadosPorDistrito[idDistrito];
        Heap distrito = heap_por_distrito[idDistrito];

        if (this.fueVisitado[idDistrito] == 1){
            return this.bancas_totales[idDistrito];
        } else {
            int k = 0;
            Heap.Nodo raiz;
            int indice;
            int divisor;

            while (k < _cantBancas ){ // O(D_d)
                raiz = distrito.Heap[0];
                indice = raiz.indice;
                bancas_partido[indice]++;
                divisor = bancas_partido[indice];
                distrito.modificarRaiz(divisor + 1); // Log(P)
                _cantBancas--;
            }
            bancas_totales[idDistrito] = bancas_partido;
            fueVisitado[idDistrito] = 1;
        }
        return bancas_partido;

        //Complejidad: O(D_d * log(P))
    }

    public boolean hayBallotage() {
        if (((suma_votos[0] * 100) / suma_votos[2]) >= 45){
            return false;
        }else if (((suma_votos[0] * 100) / suma_votos[2]) >= 40 && (((suma_votos[0] - suma_votos[1]) * 100) / suma_votos[2]) > 10){
            return false;
        } else {
            return true;
        }

        //Complejidad: O(1)
    }
}

/*
 Invariantes de representación (para Sistemas)

 - _diputadosPorDistrito ,_nombresDistritos , _ultimaMesasDistritos , fueVisitado, bancas_totales tienen el mismo tamaño que nombresDistritos.
 - Todos los elementos de _nombresPartidos son distintos entre sí.
 - Todos los elementos de _diputadosPorDistrito son mayor a 0.
 - Todos los elementos de _ultimasMesasDistrito son mayor a 0 y es estrictamente creciente (_ultimasMesasDistrito[i] < _ultimasMesasDistrito[i+1]).
 - votos_presidente y votos_diputados tienen el mismo tamaño.
 - Para suma_votos el tamaño siempre va a ser igual a 3. El primer elemento siempre va a ser igual al elemento maximo de votos presidencial,
 - El segundo elemento al segundo maximo y el tercero va a ser a la suma total de los votos.
 - Todos los elementos de fueVisitado son o 0 o 1.
 - Todos los elementos de bancas_totales tienen el mismo tamaño que nombresPartidos.
 - Para cada elemento de _nombreDistito va a haber un heap correspondiente con los votos de diputados.
 - Todos los heaps van a tener igual cantidad de nodos que elementos de _nombrePartidos.
 - El heap siempre va estar ordenado de mayor a menor y el mayor siempre va a ser la raiz, representando a el siguiente partido a tener una banca asignada para si
 - _bancasPartido siempre va a tener la misma longitud que _nombrePartidos y va a ver tantas _bancasPartidos dentro de _bancasTotales como distritos en el sistema
 - Todo nodo guarda su respectivo indice (original) en la lista _nombrepartido, un valor con los votos totales que siempre va a ser igual a la cantidad de votos que el partido recibio en ese distrito para los diputados y un valor que cambia durante la asignación de bancas.
 - El heap siempre va estar ordenado de mayor a menor y el mayor siempre va a ser la raiz, representando a el siguiente partido a tener una banca asignada para si
 - _bancasPartido siempre va tener la misma longitud que nombre partidos.
 - _bancas_totales va a tener tantos elementos (_bancasPartido) como distritos en el sistema.

*/

