public class Jugador {
    public String nombre;
    public int x;
    public int y;
    public int inicialX;
    public int inicialY;
    public int murosRestantes;
    public StringBuilder historialMovimientos;

    public Jugador(String nombre, int inicialX, int inicialY, int murosRestantes) {
        this.nombre = nombre;
        this.x = inicialX;
        this.y = inicialY;
        this.inicialX = inicialX;
        this.inicialY = inicialY;
        this.murosRestantes = murosRestantes;
        this.historialMovimientos = new StringBuilder();
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    public void mover(Tablero tablero, String direccion) {
        int nuevaX = x;
        int nuevaY = y;
        switch (direccion) {
            case "ARRIBA":
                nuevaX--;
                break;
            case "ABAJO":
                nuevaX++;
                break;
            case "IZQUIERDA":
                nuevaY--;
                break;
            case "DERECHA":
                nuevaY++;
                break;
            default:
                System.out.println("Dirección de movimiento inválida. Use: ARRIBA, ABAJO, IZQUIERDA, DERECHA.");
                return;
        }
        if (tablero.esMovimientoValido(nuevaX, nuevaY)) {
            tablero.actualizarPosicion(x, y, nuevaX, nuevaY, getSimbolo());
            x = nuevaX;
            y = nuevaY;
            historialMovimientos.append("(").append(x).append(", ").append(y).append(") ");
        } else {
            System.out.println("Movimiento inválido. La posición está fuera de límites o bloqueada.");
        }
    }

    public void colocarMuro(Tablero tablero, int x, int y, char orientacion) {
        if (murosRestantes <= 0) {
            System.out.println("No quedan muros por colocar.");
            return;
        }
        if (tablero.esColocacionMuroValida(x, y, orientacion)) {
            tablero.colocarMuro(x, y, orientacion);
            murosRestantes--;
        } else {
            System.out.println("Colocación de muro inválida. La posición está fuera de límites o se superpone con otro muro.");
        }
    }

    public boolean haGanado(int tamanoTablero) {
        return (nombre.equals("Blanco") && y == tamanoTablero - 1) || (nombre.equals("Rojo") && y == 0);
    }

    public String getPosicionInicial() {
        return "(" + inicialX + ", " + inicialY + ")";
    }

    public String getHistorialMovimientos() {
        return historialMovimientos.toString();
    }

    public char getSimbolo() {
        return nombre.charAt(0);
    }
}