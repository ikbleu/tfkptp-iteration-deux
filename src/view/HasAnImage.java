/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;
import java.awt.image.BufferedImage;
/**
 *
 * @author spock
 */
abstract class HasAnImage {
    int imageWidth;
    int imageHeight;
    BufferedImage imageBuffer;

    abstract void refreshImage();
    BufferedImage image(){
        return imageBuffer;
    }

}
