import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JuegoQuoridor {
    public static final int TAMANO_TABLERO = 9;
    public static final int MAX_MUROS = 10;
    public Tablero tablero;
    public Jugador jugadorBlanco;
    public Jugador jugadorRojo;
    public Jugador jugadorActual;
    public BufferedReader leer;

    public JuegoQuoridor() {
        this.tablero = new Tablero(TAMANO_TABLERO);
        this.jugadorBlanco = new Jugador("Blanco", TAMANO_TABLERO / 2, 0, MAX_MUROS);
        this.jugadorRojo = new Jugador("Rojo", TAMANO_TABLERO / 2, TAMANO_TABLERO - 1, MAX_MUROS);
        this.jugadorActual = jugadorBlanco;
        this.leer = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void iniciar() {
        while (true) {
            tablero.imprimirTablero(jugadorBlanco, jugadorRojo);
            System.out.println("Turno del jugador " + jugadorActual.obtenerNombre());
            System.out.print("Ingrese su movimiento (ARRIBA, ABAJO, IZQUIERDA, DERECHA) o coloque muro (MURO x y H/V) o (EXIT) para salir: ");
            String entrada;
            try {
                entrada = leer.readLine();
            } catch (IOException e) {
                System.out.println("Error leyendo entrada: " + e.getMessage());
                continue;
            }
            entrada = entrada.toUpperCase();

            if (entrada.equals("EXIT")) {
                finalizarJuego("Partida interrumpida");
                break;
            }

            try {
                if (entrada.startsWith("MURO")) {
                    colocarMuro(entrada);
                } else {
                    moverJugador(entrada);
                }

                if (jugadorActual.haGanado(TAMANO_TABLERO)) {
                    tablero.imprimirTablero(jugadorBlanco, jugadorRojo);
                    finalizarJuego(jugadorActual.obtenerNombre() + " gana!");
                    break;
                }

                cambiarTurno();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void colocarMuro(String entrada) {
        String[] partes = entrada.split(" ");
        if (partes.length != 4) {
            System.out.println("Comando de muro inválido. Use: MURO x y H/V");
            return;
        }
        int x = Integer.parseInt(partes[1]);
        int y = Integer.parseInt(partes[2]);
        char orientacion = partes[3].charAt(0);
        jugadorActual.colocarMuro(tablero, x, y, orientacion);
    }

    public void moverJugador(String entrada) {
        jugadorActual.mover(tablero, entrada);
    }

    public void cambiarTurno() {
        if (jugadorActual == jugadorBlanco) {
            jugadorActual = jugadorRojo;
        } else {
            jugadorActual = jugadorBlanco;
        }
    }

    public void finalizarJuego(String mensaje) {
        System.out.println(mensaje);
        System.out.println("Posición inicial del Jugador Blanco: " + jugadorBlanco.getPosicionInicial());
        System.out.println("Posición inicial del Jugador Rojo: " + jugadorRojo.getPosicionInicial());
        System.out.println("Recorrido final del Jugador Blanco: " + jugadorBlanco.getHistorialMovimientos());
        System.out.println("Recorrido final del Jugador Rojo: " + jugadorRojo.getHistorialMovimientos());
    }
}