package com.queue.test;

import org.hibernate.Session;
import org.junit.Test;
import java.util.Properties;


import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

/**
 * Created by JUSTIN on 2015/7/30.
 */
public class stuffTest {
    @Test
    public void testMultiply() {
//        Double[] commends = new Double[4];
        Double[] commends = {0.0, 0.0, 0.0, 0.0};
        int[] ints = new int[4];
        for (int i = 0; i < 4; i++) {
            System.out.println(commends[i]);
        }
        System.out.print(commends.length);
    }

    @Test
    public void testString() {
        String myString = "新白鹿";
        System.out.println(myString.substring(0, 1));
        System.out.println(myString.substring(1, 2));
        System.out.println(myString.substring(2, 3));
        System.out.println(myString.length());

    }

    @Test
    public void testArraySort() {
        int[] nums = {22, 33, 11, 223, 435, 54, 332, 32, 5, 65, 321, 43};
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void testNumber() {
        Random random = new Random();
        byte[] number = new byte[11];
        for (int i = 0; i < 19; i++) {
            number[0] = '1';
            number[1] = '3';
            number[2] = '5';
            number[3] = (byte) ('0' + random.nextInt(10));
            number[4] = (byte) ('0' + random.nextInt(10));
            number[5] = (byte) ('0' + random.nextInt(10));
            number[6] = (byte) ('0' + random.nextInt(10));
            number[7] = (byte) ('0' + random.nextInt(10));
            number[8] = (byte) ('0' + random.nextInt(10));
            number[9] = (byte) ('0' + random.nextInt(10));
            number[10] = (byte) ('0' + random.nextInt(10));

            String numberName = new String(number);
            System.out.println(numberName);
        }


    }

    /*@Test
    public void testEmail() {

        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        Session session = Session.getInstance(props);

        String msgBody = "test mail";

        try {
            Transport transport = session.getTransport();
            Message msg = new MimeMessage(session);
            InternetAddress fromAd = new InternetAddress("rebornzeroj@163.com");
            InternetAddress toAd = new InternetAddress("904568622@qq.com");
            msg.setFrom(fromAd);
//            msg.addRecipient(Message.RecipientType.TO,
//                    toAd);
            msg.setSubject("Your Example.com account has been activated");
            msg.setText("玩儿");
            transport.connect("rebornzeroj", 密码);
            transport.sendMessage(msg, new Address[]{
                    toAd
            });
            transport.close();

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
    }
*/
}
