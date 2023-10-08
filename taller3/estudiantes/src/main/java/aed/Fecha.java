package aed;

public class Fecha {

    private int _dia;
    private int _mes;

    public Fecha(int dia, int mes) {
        _dia = dia;
        _mes = mes;
    }

    public Fecha(Fecha fecha) {
        _dia = fecha._dia;
        _mes = fecha._mes;
    }

    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }
    
    @Override
    public String toString() {
        return Integer.toString(_dia) + '/' + Integer.toString(_mes);
    }

    @Override
    public boolean equals(Object otra) {
        if ( this.getClass() != otra.getClass()){
            return false;
        } else {
            Fecha otraFecha = (Fecha) otra;
            return (_dia == otraFecha._dia) && (_mes == otraFecha._mes);
        }
    }

    public void incrementarDia() {
        if (_dia >= diasEnMes(_mes)) {
            _dia = 1;
            if(_mes == 12){
                _mes = 1;
            }else{
                _mes = _mes + 1;
            }
        } else {
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
