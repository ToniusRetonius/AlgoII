package aed;

public class Recordatorio {

    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = new Fecha(fecha);
        _horario = horario;
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        Fecha res = new Fecha(_fecha);
        return res;
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        return _mensaje + " @ " + _fecha.toString() + " " + _horario.toString();
    }

}
