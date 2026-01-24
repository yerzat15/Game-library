import controller.GameController;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("      🎮 GameVault API - Starting...      ");
        System.out.println("=========================================\n");

        try {
            DatabaseConnection.getConnection();

            GameController controller = new GameController();
            controller.run();

        } catch (Exception e) {
            System.err.println("Fatal Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
