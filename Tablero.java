public class Tablero {
    public char[][] tablero;
    public int tamano;

    public Tablero(int tamano) {
        this.tamano = tamano;
        this.tablero = new char[tamano][tamano];
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                tablero[i][j] = '.';
            }
        }
    }

    public boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < tamano && y >= 0 && y < tamano && tablero[x][y] == '.';
    }

    public boolean esColocacionMuroValida(int x, int y, char orientacion) {
        // Verificar si la colocación del muro está dentro de los límites y no se superpone con otros muros
        if (orientacion == 'H') {
            return x >= 0 && x < tamano - 1 && y >= 0 && y < tamano && tablero[x][y] == '.' && tablero[x + 1][y] == '.';
        } else {
            return x >= 0 && x < tamano && y >= 0 && y < tamano - 1 && tablero[x][y] == '.' && tablero[x][y + 1] == '.';
        }
    }

    public void colocarMuro(int x, int y, char orientacion) {
        if (orientacion == 'V') {
            tablero[x][y] = '|';
            tablero[x - 1][y] = '|';
        } else {
            tablero[x][y] = '-';
            tablero[x][y - 1] = '-';
        }
    }

    public void actualizarPosicion(int viejaX, int viejaY, int nuevaX, int nuevaY, char simbolo) {
        tablero[viejaX][viejaY] = '.';
        tablero[nuevaX][nuevaY] = simbolo;
    }

    public void imprimirTablero(Jugador jugadorBlanco, Jugador jugadorRojo) {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (i == 0 && j == jugadorBlanco.obtenerY()) {
                    System.out.print(" PB ");
                } else if (i == tamano - 1 && j == jugadorRojo.obtenerY()) {
                    System.out.print(" PR ");
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public int getTamano() {
        return tamano;
    }

    public char getValor(int x, int y) {
        return tablero[x][y];
    }
}
