import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersMakers {
    
    public void maker(InputStream input_stream, String arquive_name, Float rating) throws Exception
    {
        // read the image
        BufferedImage old_image = ImageIO.read(input_stream);

        // make a new image with transparency and new size
        int width = old_image.getWidth();
        int heigth = old_image.getHeight();
        int new_heigth = heigth + 200;
        BufferedImage new_image = new BufferedImage(width, new_heigth, BufferedImage.TRANSLUCENT);

        // copy the real image to a new image that was created
        Graphics2D graphics = (Graphics2D) new_image.getGraphics();
        graphics.drawImage(old_image, 0, 0, null);

        // set the font of the phrase
        int size = width / 8;
        var pont = new Font(Font.MONOSPACED, Font.BOLD, size);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(pont);

        // write a phrase into the new image
        for (int i = 0; i < rating - 1; i++)
        {
            for (float j = width / 12; j < rating * width / 12; j += width / 12)
            {
                graphics.drawString("⭐", j, new_heigth - ((new_heigth - heigth)/3) + 25);
            }

            if (rating >= 9.0)
            {
                BufferedImage img = ImageIO.read(new File("./app-imdb/images/eu.png"));
                graphics.drawImage(img, width - 200, new_heigth - ((new_heigth - heigth)/3) - 200, null);
            }
        }
        // put the new image into a arquive
        File dir = new File("./app-imdb/output/");
        dir.mkdir();

        ImageIO.write(new_image, "png", new File("./app-imdb/output/" + arquive_name));
    }

}
