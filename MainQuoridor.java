public class MainQuoridor {
    public static void main(String[] args) {
        try {
            JuegoQuoridor juego = new JuegoQuoridor();
            juego.iniciar();
        } catch (Exception e) {
            System.err.println("Error al iniciar el juego: " + e.getMessage());
        }
    }
}
