package ui;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class PlayerMusica {
    private Player player;
    private Thread thread;

    public void tocar(String caminhoArquivo) throws Exception {
        parar(); // garante que não há música tocando
        thread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(caminhoArquivo)) {
                player = new Player(fis);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void parar() {
        if (player != null) player.close();
        if (thread != null && thread.isAlive()) thread.interrupt();
    }
}
