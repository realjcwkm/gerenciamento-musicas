package com.gerenciamentomusicas.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class ImageUtil {

    /**
     * Redimensiona uma imagem com alta qualidade usando a técnica de múltiplos passos
     * para preservar os detalhes e a suavidade.
     * @param originalIcon O ImageIcon original a ser redimensionado.
     * @param targetWidth A largura desejada.
     * @param targetHeight A altura desejada.
     * @return Um novo ImageIcon com a imagem redimensionada e de altíssima qualidade.
     */
    public static ImageIcon scaleImage(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        if (originalIcon == null) {
            return null;
        }

        Image currentImage = originalIcon.getImage();
        int currentWidth = currentImage.getWidth(null);
        int currentHeight = currentImage.getHeight(null);

        while (currentWidth > targetWidth * 2 && currentHeight > targetHeight * 2) {
            currentWidth /= 2;
            currentHeight /= 2;

            BufferedImage tempImage = new BufferedImage(currentWidth, currentHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = tempImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(currentImage, 0, 0, currentWidth, currentHeight, null);
            g2d.dispose();
            currentImage = tempImage;
        }

        BufferedImage finalImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = finalImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(currentImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        return new ImageIcon(finalImage);
    }
}

