package aed;

class Main {
    int fibonacci(int n) {
        int res;

        if (n == 0) {
            res = 0;
        } else if (n == 1) {
            res = 1;
        } else {
            res = fibonacci(n - 1) + fibonacci(n - 2);
        }

        return res;
    }

    int fibonacciReturn(int n) {
        // Se pueden unificar estos if?
        // necesitamos dos casos base
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciReturn(n - 1) + fibonacciReturn(n - 2);
    }

    // Revisar, no pasa los tests
    double maximo(double[] xs) {
        double res = xs[0];

        for (double x : xs) {
            // Si encuentro un elemento mayor al que tenía guardado, me lo guardo
            if (x > res) {
                res = x;
            }
        }

        return res;
    }
    
    int[] sumarArreglos(int[] seq1, int[] seq2) {
        
        int[] res = new int[seq1.length];
        
        for (int i = 0 ; i < seq1.length; i++) {

            res[i] = seq1[i] + seq2[i];
        
        }
        
        return res;
    }
    
    String iniciales(String texto) {
        String res = "";
        for (int i = 0; i < texto.length(); i++) {
            if (i == 0 || (texto.charAt(i-1) == ' ' && texto.charAt(i) != ' ')) {
                res += texto.charAt(i);
                //El método de charat devuelve el carácter en el índice definido. En este método, el valor del índice debe estar entre 0 y la longitud de la cadena menos 1
            }
        }
        return res;
    }
}

// Para inicializar un array existen 2 maneras:
// int[] matriz = new int[4] o
// int[] matriz = {100,200,302,400}
// Como podrás concluir, en la primera declaras el array nada más, diciéndole la cantidad de memoria secuencial que se debe reservar, en el segundo ejemplo se inicializa el array dándole los valores que va a contener (obviamente la cantidad de memoria secuencial reservada será igual a la cantidad de elementos insertados)
