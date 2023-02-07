package cn.ofpp;

import cn.hutool.core.util.StrUtil;
import cn.ofpp.core.Wx;
import java.util.GregorianCalendar;

/**
 * 引导配置
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Bootstrap {

    /**
     * 公众号AppID
     */
    public static final String APP_ID = "wx04c125a17400e6cf";

    /**
     * 公众号秘钥
     */
    public static final String SECRET = "c93a88b87af60289e195798b87ece976";

    /**
     * 全局模板ID  也可针对单个Friend指定模板
     */
    public static final String TEMPLATE_ID = "";

    /**
     * 初始化
     */
    public static void init() {
        
        GregorianCalendar ca = new GregorianCalendar();
        int x = ca.get(GregorianCalendar.AM_PM);//结果为“0”是上午     结果为“1”是下午
         String wxip = null;

        /*if (x == 1) {
            wxip = "onDBJMymfYGR2_grS2DTSbs4iIUZ0izhenRCiYnGxnI";
        } else {
            wxip = "alPUNA7ndnQhUscfIK3xjcAy1HJqRNE4RLNQd7crx00";
        }*/
        if (x == 1) {
            wxip = "c0RkxL8MC2XOWEMVbWAm4p5w1oMpVDFBltD3B7AUnAQ";
        } else {
            wxip = "c0RkxL8MC2XOWEMVbWAm4p5w1oMpVDFBltD3B7AUnAQ";
        }

        
        if (StrUtil.hasEmpty(APP_ID, SECRET, wxip)) {
            throw new IllegalArgumentException("请检查配置");
        }
        Wx.init();
    }

}
