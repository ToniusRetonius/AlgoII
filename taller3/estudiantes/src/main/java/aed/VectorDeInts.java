package aed;

class VectorDeInts implements SecuenciaDeInts {
    // los atributos de mi clase vector de ints que utilizaré para observar su comportamiento
    private static final int CAPACIDAD_INICIAL = 1;
    private int[] _vector;
    private int _longitud;

    // constructor por defecto :  es la instacia inicial de mi tad (su creación)
    public VectorDeInts() {
        _vector = new int [CAPACIDAD_INICIAL];
        _longitud = 0;

       
    }

    // constructor por referencia : ya toma el inicial y lo modifica (en este caso loe xtiende un  espacio más)
    public VectorDeInts(VectorDeInts vector) {
        // le observo la length
        _longitud = vector._longitud;
        // lo extiendo
        _vector = new int[_longitud + 1];
        // lo recorro y copio elem a elem
        for (int i = 0; i < _longitud; i++){
            _vector[i] = vector._vector[i]; 
        }
        
    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAtras(int i) {
        // si la length es igual al tamaño del vector => está lleno => aumento la capacidad del vector
        if (_longitud == _vector.length){
            // creo un nuevo vector con el constructor por referencia
            _vector = new VectorDeInts(this)._vector;
        }
        // asigno en la última pos del vector viejo el elem i
        _vector[_longitud] =  i;
        // aumenta la length
        _longitud++;
    }


    public int obtener(int i) {
        return _vector[i];
    }

    public void quitarAtras() {
       
    }

    public void modificarPosicion(int indice, int valor) {
        
    }

    public VectorDeInts copiar() {
    }

}