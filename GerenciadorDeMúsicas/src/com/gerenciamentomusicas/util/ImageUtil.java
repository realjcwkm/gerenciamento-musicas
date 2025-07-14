package com.gerenciamentomusicas.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class ImageUtil {

    /**
     * Redimensiona um ImageIcon para as dimensões desejadas com alta qualidade,
     * usando interpolação bilinear.
     * @param originalIcon O ImageIcon original a ser redimensionado.
     * @param targetWidth A largura desejada.
     * @param targetHeight A altura desejada.
     * @return Um novo ImageIcon com a imagem redimensionada e de alta qualidade.
     */
    public static ImageIcon scaleImage(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        if (originalIcon == null) {
            return null;
        }

        Image originalImage = originalIcon.getImage();
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2.dispose();
        return new ImageIcon(resizedImage);
    }
}