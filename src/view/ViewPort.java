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
    int coScheme[][];
    SakuraMap sakura;
    private int wid;
    private int hei;
    private boolean workerMode = false;

    ViewPort(int wid, int hei, SakuraMap sakura){
        this.sakura = sakura;
        this.wid = wid;
        this.hei = hei;
        map = new BufferedImage[hei][wid];
        bobs = new BobTheMapBuilder[hei][wid];
        coScheme = new int[hei][wid];
        for(int i = 0;i<hei;++i){
            for(int j = 0; j<wid; ++j){
                bobs[i][j] = new BobTheMapBuilder();
            }
        }
        sakura.build(bobs);
        for(int i = 0;i<hei;++i){
            for(int j = 0; j<wid; ++j){
                coScheme[i][j] = bobs[i][j].coSchemer();
                map[i][j] = bobs[i][j].buildMeViewPort();
            }
        }
    }

    void setWorkerMode(boolean on){
        workerMode = on;
    }

    BufferedImage get(int i, int j){
        return map[i][j];
    }

    int getScheme(int i, int j){
        return coScheme[i][j];
    }

    void refreshImage(){
        sakura.build(bobs);
        for(int i = 0;i<bobs.length;++i){
            for(int j = 0; j<bobs[0].length; ++j){
                if(!workerMode)
                    map[i][j] = bobs[i][j].buildMeViewPort();
                else
                    map[i][j] = bobs[i][j].buildMeWorkerPort();
            }
        }
    }
}
