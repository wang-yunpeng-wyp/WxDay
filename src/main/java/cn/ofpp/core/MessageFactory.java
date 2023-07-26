package cn.ofpp.core;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.text.SimpleDateFormat;
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
        //获取北京时区

        Date today = new Date(new Date().getTime() + (8 * 60 * 60 * 1000));

// 格式化时间 

 SimpleDateFormat sf = new SimpleDateFormat("HH");
        String format = sf.format(today);
        Integer time =  Integer.valueOf(format);
            
        System.out.println("当前时间："+time);

        if (time > 12) {

            wxip = "TGkBXuHy1mRWHvVDhkNdhW_j7DhRHyQrzRJA4CWN6Gw"; //下午
        } else {
            wxip = "RYneCazpNYPshuUK1S0SG3EfjxGEtQyVuEEPdABpxHk";//上午
        }

        System.err.println(buildData(friend));
        return WxMpTemplateMessage.builder()
                .url("http://slither.io/") // 点击后的跳转链接 可自行修改 也可以不填
                .toUser(friend.getUserId())
                .templateId(StrUtil.emptyToDefault(friend.getTemplateId(),wxip))
                .data(buildData(friend))
                .build();
    }

    private static List<WxMpTemplateData> buildData(Friend friend) {
        WeatherInfo weather = GaodeUtil.getNowWeatherInfo(getAdcCode(friend.getProvince(), friend.getCity()));
        RandomAncientPoetry.AncientPoetry ancientPoetry = null;
        try{
            ancientPoetry = RandomAncientPoetry.getNext();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取古诗接口失败！！！");
        }
        String str = "初始体重:134斤"
                +"" + "今日体重:131.1"
                +"当日合同金额390元";
        ArrayList list = new ArrayList();
        list.add( TemplateDataBuilder.builder().name("taryIt").value(friend.getTaryIt()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("friendName").value("宝贝今天也要元气满满哟!! ").color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("howOld").value("爱你♥爱你♥").color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("nextTime").value(friend.getNextTime()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("howLongLived").value(friend.getHowLongLived()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("nextBirthday").value(friend.getNextBirthdayDays()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("nextMemorialDay").value(friend.getNextMemorialDay()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("province").value(friend.getProvince()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("city").value(friend.getCity()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("weather").value(weather.getWeather()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("temperature").value(weather.getTemperature()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("winddirection").value(weather.getWinddirection() + "风").color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("windpower").value(weather.getWindpower() + "级").color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("humidity").value(weather.getHumidity()+ "%").color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("author").value(ancientPoetry.getAuthor()).color(cor()).build() );
        list.add( TemplateDataBuilder.builder().name("origin").value(ancientPoetry.getOrigin()).color(cor()).build() );
       // list.add( TemplateDataBuilder.builder().name("content").value(ancientPoetry.getContent()).color(cor()).build() );
                list.add( TemplateDataBuilder.builder().name("content").value(ancientPoetry.getContent()+str).color(cor()).build() );
        list.add(TemplateDataBuilder.builder().name("wuyi").value(friend.getWuyi()).color(cor()).build());
        list.add(TemplateDataBuilder.builder().name("zhongqiu").value(friend.getZhongqiui()).color(cor()).build());
        list.add(TemplateDataBuilder.builder().name("shiyi").value(friend.getShiyi()).color(cor()).build());
        list.add(TemplateDataBuilder.builder().name("chuxi").value(friend.getChuxi()).color(cor()).build());
       // list.add(TemplateDataBuilder.builder().name("duanwu").value(friend.getDuanwu()).color(cor()).build());
        list.add(TemplateDataBuilder.builder().name("duanwu").value(friend.getDuanwu()
                +"\n\n初始体重:130斤"
                +"\n" + "今日体重:131.1\n"
                +"当日合同金额：-110元")
                .color(cor()).build());
        /** 当前模板 模板最大长度600个字符 当前600个字符
         {{friendName.DATA}}
         {{howOld.DATA}}
         我们已经在一起{{taryIt.DATA}} 天
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
         空气湿度：{{humidity.DATA + "%"}}
         {{author.DATA}}
         {{origin.DATA}}
         {{content.DATA}}

         {{tx.DATA}}

         */

        return list;
    }

    public static String cor() {
        Random random = new Random();//#173177
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
            try {
                if (StrUtil.hasEmpty(name, value)) {
                    throw new IllegalArgumentException("参数不正确");
                }
            }catch (Exception e){

            }
            WxMpTemplateData data = new WxMpTemplateData();
            data.setName(name);
            data.setValue(value);
            data.setColor(color);
            return data;
        }
    }
}


