package aed;

public class Fecha {

    private int _dia;
    private int _mes;

    public Fecha(int dia, int mes) {
       _dia = dia;
       _mes = mes;
    }

    public Fecha(Fecha fecha) {
        fecha._dia = dia();
        fecha._mes = mes();
    }

    public Integer dia() {
       return _dia;
    }

    public Integer mes() {
        return _mes;
    }
    
    @Override
    public String toString() {
        // me pide devolver el formato dia/mes ej 14/12
        // El método toString() en Java es un método especial que se utiliza para representar un objeto como una cadena de caracteres (String)
        return Integer.toString(_dia) + "/" + Integer.toString(_mes);
    }

    @Override
    public boolean equals(Object otra) {
       // El método equals() en Java es un método utilizado para comparar dos objetos para verificar si son iguales en términos de contenido o valor.
       if ( this.getClass() != otra.getClass()){
        return false;
        // distinta clase, false
       } else {
        Fecha otrFecha = (Fecha) otra;
        return (_dia == otrFecha._dia) && (_mes == otrFecha._mes);
       }
       
    }

    public void incrementarDia() {
        if (_dia >= diasEnMes(_mes)){
            _dia = 1;
            if (_mes == 12){
                _mes = 1;
            } else {
                _mes = _mes + 1;}
        }else{
            _dia = _dia + 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
