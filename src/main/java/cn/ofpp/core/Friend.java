package cn.ofpp.core;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

import static cn.hutool.core.date.DateUtil.age;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Friend {

    private  String fullName;

    private  Integer howOld;

    private  String province;

    private  String city;

    private  String userId;

    private  String birthday;

    private  String loveTime;

    private  String sex;

    private  String templateId;
    
    private  String taryIt;
    private String wuyi;
    private String shiyi;
    private String zhongqiui;
    private String yuandan;
    private String chuxi;
    private String nextTime;
    private String duanwu;

    public String getDuanwu() {
        return duanwu;
    }

    public void setDuanwu(String duanwu) {
        this.duanwu = duanwu;
    }

    public String getChuxi() {
        return chuxi;
    }

    public void setChuxi(String chuxi) {
        this.chuxi = chuxi;
    }

    public String getWuyi() {
        return wuyi;
    }

    public void setWuyi(String wuyi) {
        this.wuyi = wuyi;
    }

    public String getShiyi() {
        return shiyi;
    }

    public void setShiyi(String shiyi) {
        this.shiyi = shiyi;
    }

    public String getZhongqiui() {
        return zhongqiui;
    }

    public void setZhongqiui(String zhongqiui) {
        this.zhongqiui = zhongqiui;
    }

    public String getYuandan() {
        return yuandan;
    }

    public void setYuandan(String yuandan) {
        this.yuandan = yuandan;
    }

    public Friend(String fullName, String province, String city, String userId, String birthday, String loveTime, String sex, String taryIt ) {
        this(fullName, province, city, userId, birthday, loveTime, sex, null , taryIt);
    }

    public Friend(String fullName, String province, String city, String userId, String birthday, String loveTime, String sex, String templateId ,String taryIt ) {
        this.fullName = fullName;
        this.howOld = age(DateUtil.parse(birthday), new Date());
        this.province = province;
        this.city = city;
        this.userId = userId;
        this.birthday = birthday;
        this.loveTime = loveTime;
        this.sex = sex;
        this.templateId = templateId;
        this.taryIt = taryIt;
       
    }

    public String getSpring() {

        return String.valueOf(DateUtil.betweenDay( new Date(),getYearLast(),true));
    }

    public static Date getYearLast(){
        Calendar currCal= Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,currCal.get(Calendar.YEAR));
        calendar.roll(Calendar.DAY_OF_YEAR,-1);
        Date time = calendar.getTime();

        return time;
    }


    public String getTaryIt() {
        return  String.valueOf(DateUtil.betweenDay(new Date(), DateUtil.parse(taryIt),true));
    }


    public String getFullName() {
        return fullName;
    }


    public Integer getHowOld() {
        return howOld;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getUserId() {
        return userId;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLoveTime() {
        return loveTime;
    }

    public String getSex() {
        return sex;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getHowLongLived() {
        return String.valueOf(DateUtil.between(new Date(), DateUtil.parse(birthday), DateUnit.DAY));
    }

    public String getNextBirthdayDays() {
        return getNextDay(DateUtil.parse(birthday));
    }

    public String getNextMemorialDay() {
        return getNextDay(DateUtil.parse(loveTime));
    }

    public static String getNextDay(DateTime dateTime) {
        dateTime = DateUtil.beginOfDay(dateTime);
        DateTime now = DateUtil.beginOfDay(new Date());
        dateTime.offset(DateField.YEAR, now.year() - dateTime.year());
        if (now.isAfter(dateTime)) {
            return String.valueOf(dateTime.offset(DateField.YEAR, 1).between(now, DateUnit.DAY));
        }
        return String.valueOf(dateTime.between(now, DateUnit.DAY));
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setHowOld(Integer howOld) {
        this.howOld = howOld;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setLoveTime(String loveTime) {
        this.loveTime = loveTime;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setTaryIt(String taryIt) {
        this.taryIt = taryIt;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

}
