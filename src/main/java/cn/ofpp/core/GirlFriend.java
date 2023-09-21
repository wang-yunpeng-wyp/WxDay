package cn.ofpp.core;

/**
 * 你可以new一个女朋友
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class GirlFriend extends Friend {

    
    public GirlFriend(String fullName, String province, String city, String birthday, String loveTime, 
                      String userId,String taryIt, String author, String origin, String content) {
        super(fullName, province, city, userId, birthday, loveTime, "女", taryIt,author,origin,content);
    }

    public GirlFriend(String fullName, String province, String city, String birthday, String loveTime, 
                      String userId, String templateId,String taryIt , String author, String origin, String content) {
        super(fullName, province, city, userId, birthday, loveTime, "女", templateId, taryIt,author,origin,content);
    }

}
