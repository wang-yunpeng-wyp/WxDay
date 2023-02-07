package cn.ofpp;

import cn.ofpp.calendarist.Calendarist;
import cn.ofpp.calendarist.pojo.LunarDate;
import cn.ofpp.calendarist.pojo.SolarDate;
import cn.ofpp.core.GirlFriend;
import cn.ofpp.core.MessageFactory;
import cn.ofpp.core.Wx;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 启动类
 *
 * 这个理论上只能用测试号 正式的号 个人认证是不支持模板消息的 企业认证的又必须使用微信模板库里的模板 只有测试的可以自定义模板内容
 * <a href="https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index">申请公众号测试应用地址</a>
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Application {

    /**
     * <li>创建配置</li>
     * <li>创建几个 男/女 朋友</li>
     * <li>发消息</li>
     */
    public static void main(String[] args) {
        // load and init
        Bootstrap.init();

        // -----------------  王云鹏的数据  ------------------------

        // new 一个 女友
        GirlFriend wypFriend = new GirlFriend("我的宝儿",
                "北京市", "顺义区", "1998-03-10", "2022-02-04", "oaiup5nPe2aGJ24Uc2nQI_sJK7Yw","2022-02-03");

        wypFriend = lunarTime(wypFriend);
        int wyp = 0;

        while (true){
            try{
                wyp++;
                Wx.sendTemplateMessage(MessageFactory.resolveMessage(wypFriend));
                break;
            }catch (Exception e){
                System.out.println("王云鹏微信失败;一共执行了 "+wyp+" 次");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException t){
                    System.out.println("王云鹏休息异常");
                }
            }

        }



        //new 一个 女友
       GirlFriend zycFriend = new GirlFriend("我的宝儿\n爱你！！！",
               "安阳市", "滑县", "1998-12-09", "2022-02-04", "oaiup5lY17LhWIOqwu5hMBnUKynY","2022-02-03");
        WxMpTemplateMessage wxMpTemplateMessage = MessageFactory.resolveMessage(zycFriend);
        System.err.println(wxMpTemplateMessage.toString());
        zycFriend = lunarTime(zycFriend);
        int bb = 0;
        while (true){
            try{

                bb++;
                Wx.sendTemplateMessage(MessageFactory.resolveMessage(zycFriend));
                break;
            }catch (Exception e){
                System.out.println("宝贝微信失败;一共执行了 "+bb+" 次");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException t){
                    System.out.println("宝贝休息异常");
                }
            }

        }
             
//        GirlFriend girlFriend2 = new GirlFriend("宝贝，",
//                "河南省", "安阳市", "1998-12-09", "2022-02-03", "oaiup5lY17LhWIOqwu5hMBnUKynY");
//        Wx.sendTemplateMessage(MessageFactory.resolveMessage(girlFriend2));

      
       GirlFriend girlFriend3 = new GirlFriend("我的宝儿！",
               "安阳市", "安阳市", "1998-12-09", "2022-02-04", "oaiup5n2TF8TPePzIeDzCgQsrTro","2022-02-03");

       // girlFriend3 = lunarTime(girlFriend3);

        int bb1 = 0;
       /* while (true){
            try{

                bb1++;
                //Wx.sendTemplateMessage(MessageFactory.resolveMessage(girlFriend3));
                break;
            }catch (Exception e){
                System.out.println("宝贝1微信失败;一共执行了 "+bb1+" 次");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException t){
                    System.out.println("宝贝1休息异常");
                }
            }

        }*/


        System.err.println("发送成功");

        // new 一个 男友 也可单独针对一个friend设置模板ID 以达到不同人不同消息
//        BoyFriend boyFriend = new BoyFriend("某男友",
//                "江苏省", "南京市", "1999-08-08", "2011-04-16", "oQFk-5qtXv2uGNCu9oiCiV85KWD8", "5t7-Ksy8_rw-QmUkxf8J7Pe-QLQ2rBc7RWJi_pSmeh4");
//        Wx.sendTemplateMessage(MessageFactory.resolveMessage(boyFriend));
    }



    public static GirlFriend lunarTime (GirlFriend friend){
        // 获取当前年
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        /**
         * 生日 阴历转阳历
         */
        LunarDate lunarDate =  new LunarDate();
        String birthday = friend.getBirthday();
        //1998-03-10
        String[] split = birthday.split("-");
        String mon = split[1];
        String day = split[2];
        lunarDate.setYear(year);
        lunarDate.setMonth(Integer.valueOf(mon));
        lunarDate.setDay(Integer.valueOf(day));
        Calendarist c = Calendarist.fromLunar(lunarDate);
        SolarDate solarDate = c.toSolar();
        int month = solarDate.getMonth();
        int day1 = solarDate.getDay();
        friend.setBirthday(split[0]+ "-"+month+"-"+day1);
        //LunarDate lunarDate1 = c.toLunar();
        /**
         * 在一起纪念日
         */
       /* String loveTime = friend.getLoveTime();
        String[] loveSplit = loveTime.split("-");
        mon = loveSplit[1];
        day = loveSplit[2];
        lunarDate.setDay(Integer.valueOf(loveSplit[0]));
        lunarDate.setMonth(Integer.valueOf(mon));
        lunarDate.setDay(Integer.valueOf(day));
        Calendarist loveD = Calendarist.fromLunar(lunarDate);
        SolarDate solarDate1 = loveD.toSolar();
        month = solarDate1.getMonth();
        day1 = solarDate1.getDay();
        friend.setLoveTime(loveSplit[0]+ "-"+month+"-"+day1 ); */

        Date time = calendar.getTime();
        time.setYear(5);
        time.setDate(1);
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String dateTime = df.format(time);
        Date wuyi = null;
        Date shiyi = null;
        Date zqda = null;
        Date yd = null;
        Date chuxidata= null;
        try {
            //五一
            wuyi  = df.parse( calendar.get(Calendar.YEAR) + "-05-01");
            Long w = (wuyi.getTime() - date.getTime())/24/60/60/1000;
            if(w < 0 ){
                wuyi  = df.parse( (calendar.get(Calendar.YEAR)+1) + "-05-01");
                w = (wuyi.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setWuyi(String.valueOf(w));
            //十一
            shiyi  = df.parse( calendar.get(Calendar.YEAR) + "-10-01");
            Long s = (shiyi.getTime() - date.getTime())/24/60/60/1000;
            if (s < 0 ){
                shiyi  = df.parse( (calendar.get(Calendar.YEAR)+1) + "-10-01");
                s = (shiyi.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setShiyi(String.valueOf(s));
            //中秋
            lunarDate.setYear(year);
            lunarDate.setMonth(8);
            lunarDate.setDay(15);
            Calendarist zq = Calendarist.fromLunar(lunarDate);
            SolarDate zqSolarDate = zq.toSolar();
            zqda = df.parse(zqSolarDate.getYear() + "-" + zqSolarDate.getMonth() + "-" + zqSolarDate.getDay());
            Long z = (zqda.getTime() - date.getTime())/24/60/60/1000;
            if (z < 0 ){
                zqda = df.parse((zqSolarDate.getYear() + 1) + "-" + zqSolarDate.getMonth() + "-" + zqSolarDate.getDay());
                z = (zqda.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setZhongqiui(String.valueOf(z));

            //元旦
            yd = df.parse(calendar.get(Calendar.YEAR) + "-01-01");
            Long y =  (yd.getTime() - date.getTime())/24/60/60/1000;
            if ( y < 0 ){
                yd = df.parse((calendar.get(Calendar.YEAR) + 1) + "-01-01");
                y =  (yd.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setYuandan(String.valueOf(y));
            //除夕
            lunarDate.setYear(year +1 );
            lunarDate.setMonth(1);
            lunarDate.setDay(1);
            Calendarist cx = Calendarist.fromLunar(lunarDate);
            SolarDate cxdata = cx.toSolar();
            chuxidata = df.parse(cxdata.getYear() + "-" + cxdata.getMonth() + "-" + cxdata.getDay());
            Long cc = (chuxidata.getTime() - date.getTime() )/24/60/60/1000;
            friend.setChuxi(String.valueOf(cc));



            System.out.println( "距离==>"+"五一:"+ w + "中秋:"+s+"国庆:"+z + "元旦：" + y + "除夕："+ cc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  friend;
    }
}
