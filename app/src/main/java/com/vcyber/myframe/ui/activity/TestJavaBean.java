package com.vcyber.myframe.ui.activity;

import java.util.List;

/**
 * description ：
 * author : zjl
 * date : 9/22/21
 */
public class TestJavaBean {

    /**
     * rewardLuckDrawCfg : {"first":5,"rate":20,"ncfg":[{"times":[-1,5],"rate":20},{"times":[6,10],"rate":30},{"times":[11,-1],"rate":50}]}
     */


    /**
     * first : 5
     * rate : 20
     * ncfg : [{"times":[-1,5],"rate":20},{"times":[6,10],"rate":30},{"times":[11,-1],"rate":50}]
     */

    public int first;
    public int rate;
    public List<NcfgBean> ncfg;


    public static class NcfgBean {
        /**
         * times : [-1,5]
         * rate : 20
         */

        public int[] times;
        public int rate;
    }
}