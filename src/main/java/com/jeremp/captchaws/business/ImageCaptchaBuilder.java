package com.jeremp.captchaws.business;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ImageCaptchaBuilder implements CaptchaBuilder {
    
private static final Logger LOG = LoggerFactory.getLogger(ImageCaptchaBuilder.class);     
    
    @Override
    public InputStream generate(String phrase) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(phrase);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        
        // ligne oblique
        //g2d.draw(new Line2D.Double(0, 0, width, height));
        //g2d.setColor(Color.BLACK.BLACK);
        
        g2d.drawString(phrase, 0, fm.getAscent());
        
    
        
                        
// draw QuadCurve2D.Float with set coordinates
QuadCurve2D q = new QuadCurve2D.Float();
q.setCurve(0, 0, 30, 60, width, 10);
g2d.setStroke(new BasicStroke(3));
g2d.draw(q);
        
        g2d.dispose();
        
        // emboss
        //img = embossImage(img);
        
        
        
        
        
        try {
            ImageIO.write(img, "png", new File("c:/temp/Text.png"));
        } catch (IOException ex) {
            LOG.error("ouille", ex);
        }
        return null ;
    }
    
    private BufferedImage blurImage(BufferedImage img, float blurPowa) {
        Kernel kernel = new Kernel(3, 3,
                new float[]{blurPowa / 9f, blurPowa / 9f, blurPowa / 9f,
                    blurPowa / 9f, blurPowa / 9f, blurPowa / 9f,
                    blurPowa / 9f, blurPowa / 9f, blurPowa / 9f});

        BufferedImageOp op = new ConvolveOp(kernel);

        return op.filter(img, null);

    }
    
    
   
}
