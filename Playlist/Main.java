//ainda falta configurar o banco de dados na playlist
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Main {
    public static void main(String[] args) {
        try {
            FlatLightLaf.setup(); // tema claro
            // ou FlatDarkLaf.setup(); // tema escuro
        } catch (Exception e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(() -> new ui.PlaylistGUI());
    }
}
