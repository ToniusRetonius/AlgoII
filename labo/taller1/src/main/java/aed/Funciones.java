package aed;

class Funciones {
    int cuadrado(int x) {
        int res = x * x;
        return res;
    }
    double cuadrado2(double x) {
        double res = x * x;
        return res;
    } 

    double distancia(double x, double y) {
            double res =  Math.sqrt(cuadrado2(x) + cuadrado2(y));
            return res;
    }

    boolean esPar(int n) {

        if( n % 2 == 0 ) {return true;} else {return false;}
    
    }

    boolean esBisiesto(int n) {
        boolean res = false;
        if (n %  400 == 0) {res = true;}
        else if ( n % 4 == 0 && n % 100 != 0) {res = true;}

        return res;
    }

    int factorialIterativo(int n){
        int res = 1;
        if (n < 2) { }
        else {
            for(int i = n; i != 1; i --){
            res = i * res;
        }
        }
        
        return res;
    }

    int factorialRecursivo(int n) {
        int res = 1;
        if ( n >=2){
        res = n * factorialRecursivo(n-1);
        
       }
       return res;
    }

    boolean esPrimo(int n) {
        boolean res = true;
        if (n == 0 || n == 1 ){ res = false;}
        for (int i = 2 ; i < n ; i++)
        if (n % i == 0){
            res = false;
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for ( int x : numeros ) {
            res += x;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        for (int i = 0; i < numeros.length; i ++){
            if (buscado == numeros[i]){
                return i;
            }
        }
        return 0;
    }

    boolean tienePrimo(int[] numeros) {
        for (int x : numeros){
            if (esPrimo(x)){
                return true;
            }
        }
        return false;

    }
    boolean todosPares(int[] numeros) {
        for (int x : numeros){
            if (!esPar(x)){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        if (s1.length() <= s2.length()){
            for (int i = 0 ; i < s1.length(); i++){
                if (s1.charAt(i) != s2.charAt(i)){
                    return false;
                }
            } 
        } else { return false;}
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res = true;
          if (s1.length() <= s2.length()){
            for (int i = 0; i < s1.length(); i++){
                if (s1.charAt( s1.length() - i - 1) != s2.charAt( s2.length() - i - 1)){
                    res = false;
                }
            }
            }
            else { res = false;}
      return res; 
    }
}