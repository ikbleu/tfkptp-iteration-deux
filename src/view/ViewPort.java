/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import java.awt.image.BufferedImage;
import src.model.interfaces.SakuraMap;

/**
 *
 * @author spock
 */
public class ViewPort extends HasAnImage{
    BufferedImage map[][];
    BobTheMapBuilder bobs[][];
    SakuraMap sakura;


    ViewPort(int wid, int hei){
        map = new BufferedImage[hei][wid];
        bobs = new BobTheMapBuilder[hei][wid];
        for(int i = 0;i<hei;++i){
            for(int j = 0; j<wid; ++j){
                bobs[i][j] = new BobTheMapBuilder("hi");
            }
        }
        //sakura.build(bobs);
        for(int i = 0;i<hei;++i){
            for(int j = 0; j<wid; ++j){
                map[i][j] = bobs[i][j].buildMeViewPort();
            }
        }
    }

    BufferedImage get(int i, int j){
        return map[i][j];
    }

    void refreshImage(){
        //sakura.build(bobs);
        for(int i = 0;i<bobs.length;++i){
            for(int j = 0; j<bobs[0].length; ++j){
                map[i][j] = bobs[i][j].buildMeViewPort();
            }
        }
    }
}
