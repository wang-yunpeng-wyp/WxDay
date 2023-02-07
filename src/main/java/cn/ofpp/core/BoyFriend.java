package cn.ofpp.core;

/**
 * 你可以new一个男朋友
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class BoyFriend extends Friend {

    public BoyFriend(String fullName, String province, String city, String birthday, String loveTime, String userId,String taryIt) {
        super(fullName, province, city, userId, birthday, loveTime, "男",taryIt);
    }

    public BoyFriend(String fullName, String province, String city, String birthday, String loveTime, String userId, String templateId,String taryIt) {
        super(fullName, province, city, userId, birthday, loveTime, "男", templateId,taryIt);
    }


}