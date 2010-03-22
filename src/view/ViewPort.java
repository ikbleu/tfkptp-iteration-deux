/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src.view;

import apple.laf.CoreUIConstants.Widget;
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
    private int wid;
    private int hei;
    private boolean workerMode = true;

    ViewPort(int wid, int hei, SakuraMap sakura){
        this.sakura = sakura;
        this.wid = wid;
        this.hei = hei;
        map = new BufferedImage[hei][wid];
        bobs = new BobTheMapBuilder[hei][wid];
        for(int i = 0;i<hei;++i){
            for(int j = 0; j<wid; ++j){
                bobs[i][j] = new BobTheMapBuilder();
            }
        }
        sakura.build(bobs);
        for(int i = 0;i<hei;++i){
            for(int j = 0; j<wid; ++j){
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
