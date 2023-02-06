package cn.ofpp.calendarist.core.convert;


import cn.ofpp.calendarist.pojo.SolarDate;

public interface ISolarConvert {


    /**
     * 将某日期转为阳历日期
     *
     * @return {@link SolarDate}
     */
    SolarDate toSolar();



}
