package cn.ofpp.core;

import cn.hutool.core.util.StrUtil;
import cn.ofpp.calendarist.Calendarist;
import cn.ofpp.calendarist.pojo.LunarDate;
import cn.ofpp.calendarist.pojo.SolarDate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.*;

import static cn.ofpp.core.GaodeUtil.getAdcCode;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class MessageFactory {



    public static WxMpTemplateMessage resolveMessage(Friend friend) {



        GregorianCalendar ca = new GregorianCalendar();
        int x = ca.get(GregorianCalendar.AM_PM);
        String wxip = null;

        if (x == 1) {
            wxip = "5lFZaNChhOQcSrLBNK2zyq3TwpKJ7bEcKUVT4FDkvKk";
        } else {
            wxip = "RQdxgfbxtQCDlyTIsqfuY03YmhZd-e9TR05R2ktfeM8";
        }
        //System.err.println(wxip);

        return WxMpTemplateMessage.builder()
                .url("Me marry you Emily！！！") // 点击后的跳转链接 可自行修改 也可以不填
                .toUser(friend.getUserId())
                .templateId(StrUtil.emptyToDefault(friend.getTemplateId(),wxip))
                .data(buildData(friend))
                .build();
    }

    /**
     *
     * {@code {{xxxx.DATA}}} xxxx就是一个变量名，消息中设置变量 然后传递时传递变量即可
     * <br/>
     * 色彩取值可以从这里挑选 https://arco.design/palette/list
     *
     *  <p>
     *      你叫{{friendName.DATA}}
     *      今年{{howOld.DATA}}
     *      距离下一次生日{{nextBirthday.DATA}}天
     *      具体我们的下一次纪念日{{nextMemorialDay.DATA}}天
     *      现在在{{province.DATA}}{{city.DATA}}
     *      当前天气{{weather.DATA}}
     *      当前气温{{temperature.DATA}}
     *      风力描述{{winddirection.DATA}}
     *      风力级别{{windpower.DATA}}
     *      空气湿度{{humidity.DATA}}
     *      {{author.DATA}}
     *      {{origin.DATA}}
     *      {{content.DATA}}
     *  </p>
     */
    private static List<WxMpTemplateData> buildData(Friend friend) {
        WeatherInfo weather = GaodeUtil.getNowWeatherInfo(getAdcCode(friend.getProvince(), friend.getCity()));
        RandomAncientPoetry.AncientPoetry ancientPoetry = RandomAncientPoetry.getNext();
        ArrayList list = new ArrayList();
        //list.add(  TemplateDataBuilder.builder().name("taryIt").value(friend.getTaryIt()).color("#D92AD9").build());
        list.add( TemplateDataBuilder.builder().name("taryIt").value(friend.getTaryIt()).color("#D92AD9").build() );
        list.add( TemplateDataBuilder.builder().name("nextSpring").value(friend.getSpring()).color("#D94AD9").build() );
        list.add( TemplateDataBuilder.builder().name("friendName").value(friend.getFullName()).color("#D91AD9").build() );
        list.add( TemplateDataBuilder.builder().name("howOld").value(friend.getHowOld().toString()).color("#F77234").build() );
        list.add( TemplateDataBuilder.builder().name("howLongLived").value(friend.getHowLongLived()).color("#437004").build() );
        list.add( TemplateDataBuilder.builder().name("nextBirthday").value(friend.getNextBirthdayDays()).color("#771F06").build() );
        list.add( TemplateDataBuilder.builder().name("nextMemorialDay").value(friend.getNextMemorialDay()).color("#551DB0").build() );
        list.add( TemplateDataBuilder.builder().name("province").value(friend.getProvince()).color("#F53F3F").build() );
        list.add( TemplateDataBuilder.builder().name("city").value(friend.getCity()).color("#F53F3F").build() );
        list.add( TemplateDataBuilder.builder().name("weather").value(weather.getWeather()).color("#00B42A").build() );
        list.add( TemplateDataBuilder.builder().name("temperature").value(weather.getTemperature()).color("#722ED1").build() );
        list.add( TemplateDataBuilder.builder().name("winddirection").value(weather.getWinddirection()).color("#F5319D").build() );
        list.add( TemplateDataBuilder.builder().name("windpower").value(weather.getWindpower()).color("#3491FA").build() );
        list.add( TemplateDataBuilder.builder().name("humidity").value(weather.getHumidity()).color("#F77234").build() );
        list.add( TemplateDataBuilder.builder().name("author").value(ancientPoetry.getAuthor()).color("#F53F3F").build() );
        list.add( TemplateDataBuilder.builder().name("origin").value(ancientPoetry.getOrigin()).color("#F53F3F").build() );
        list.add( TemplateDataBuilder.builder().name("content").value(ancientPoetry.getContent()).color("#F53F3F").build() );

        list.add(TemplateDataBuilder.builder().name("wuyi").value(friend.getWuyi()).color("CCFF33").build());
        list.add(TemplateDataBuilder.builder().name("zhongqiu").value(friend.getZhongqiui()).color("CCFF33").build());
        list.add(TemplateDataBuilder.builder().name("shiyi").value(friend.getShiyi()).color("CCFF33").build());
        list.add(TemplateDataBuilder.builder().name("chuxi").value(friend.getChuxi()).color("CCFF33").build());

        list.add(TemplateDataBuilder.builder().name("tx").value("浪漫的灵魂，从不向平坦的日子妥协\n我爱你且日益剧增").color(cor()).build());
        list.add(TemplateDataBuilder.builder().name("txx").value("").color(cor()).build());

        /**
         {{friendName.DATA}}
         今年{{howOld.DATA}}岁
         我们在一起的{{taryIt.DATA}} 天
         纪念日还有{{nextMemorialDay.DATA}}天
         生日还有{{nextBirthday.DATA}}天
         春节还有{{chuxi.DATA}}天
         五一还有{{wuyi.DATA}}天
         中秋还有{{zhongqiu.DATA}}天
         十一还有{{shiyi.DATA}}天
         位置：{{province.DATA}}-{{city.DATA}}
         当前天气：{{weather.DATA}}
         当前气温：{{temperature.DATA}}°C
         风向：{{winddirection.DATA + "风"}}
         风力级别：{{windpower.DATA + "级"}}
         湿度：{{humidity.DATA + "%"}}
         {{author.DATA}}
         {{origin.DATA}}
         {{content.DATA}}
         {{tx}}



         */


        return list;
    }

    public static String cor() {
        Random random = new Random();
        int nextInt = random.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;

    }

    /*
    return List.of(
                TemplateDataBuilder.builder().name("taryIt").value(friend.getTaryIt()).color("#D92AD9").build(),
                TemplateDataBuilder.builder().name("nextSpring").value(friend.getSpring()).color("#D94AD9").build(),
                TemplateDataBuilder.builder().name("friendName").value(friend.getFullName()).color("#D91AD9").build(),
                TemplateDataBuilder.builder().name("howOld").value(friend.getHowOld().toString()).color("#F77234").build(),
                TemplateDataBuilder.builder().name("howLongLived").value(friend.getHowLongLived()).color("#437004").build(),
                TemplateDataBuilder.builder().name("nextBirthday").value(friend.getNextBirthdayDays()).color("#771F06").build(),
                TemplateDataBuilder.builder().name("nextMemorialDay").value(friend.getNextMemorialDay()).color("#551DB0").build(),
                TemplateDataBuilder.builder().name("province").value(friend.getProvince()).color("#F53F3F").build(),
                TemplateDataBuilder.builder().name("city").value(friend.getCity()).color("#F53F3F").build(),
                TemplateDataBuilder.builder().name("weather").value(weather.getWeather()).color("#00B42A").build(),
                TemplateDataBuilder.builder().name("temperature").value(weather.getTemperature()).color("#722ED1").build(),
                TemplateDataBuilder.builder().name("winddirection").value(weather.getWinddirection()).color("#F5319D").build(),
                TemplateDataBuilder.builder().name("windpower").value(weather.getWindpower()).color("#3491FA").build(),
                TemplateDataBuilder.builder().name("humidity").value(weather.getHumidity()).color("#F77234").build(),
                TemplateDataBuilder.builder().name("author").value(ancientPoetry.getAuthor()).color("#F53F3F").build(),
                TemplateDataBuilder.builder().name("origin").value(ancientPoetry.getOrigin()).color("#F53F3F").build(),
                TemplateDataBuilder.builder().name("content").value(ancientPoetry.getContent()).color("#F53F3F").build()
        );
     */
    static class TemplateDataBuilder {
        private String name;
        private String value;
        private String color;

        public static TemplateDataBuilder builder() {
            return new TemplateDataBuilder();
        }
        public TemplateDataBuilder name(String name) {
            this.name = name;
            return this;
        }
        public TemplateDataBuilder value(String value) {
            this.value = value;
            return this;
        }
        public TemplateDataBuilder color(String color) {
            this.color = color;
            return this;
        }
        public WxMpTemplateData build() {
            if (StrUtil.hasEmpty(name, value)) {
                throw new IllegalArgumentException("参数不正确");
            }
            WxMpTemplateData data = new WxMpTemplateData();
            data.setName(name);
            data.setValue(value);
            data.setColor(color);
            return data;
        }
    }
}


