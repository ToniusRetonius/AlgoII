package aed;

import java.util.Vector;

public class Agenda {

    private Vector<Recordatorio> _recordatorios;
    private Fecha _hoy;

    public Agenda(Fecha fechaActual) {
        _hoy = new Fecha(fechaActual);
        _recordatorios = new Vector<Recordatorio>();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        _recordatorios.add(recordatorio);
    }

    @Override
    public String toString() {
        String res = _hoy.toString() + "\n=====\n";
        for(int i = 0; i < _recordatorios.size(); i ++){
            if (_recordatorios.get(i).fecha().equals(_hoy)){
                res = res + _recordatorios.get(i).toString() + "\n";
            }
        }
        return res;

    }

    public void incrementarDia() {
        _hoy.incrementarDia();
    }

    public Fecha fechaActual() {
        Fecha res = new Fecha(_hoy);
        return res;
    }
}
