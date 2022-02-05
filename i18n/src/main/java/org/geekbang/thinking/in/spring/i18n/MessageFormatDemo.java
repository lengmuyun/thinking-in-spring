package org.geekbang.thinking.in.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} 示例
 *
 * @author fangkuangzhang
 * @date 2022/2/5 15:32
 */
public class MessageFormatDemo {

    public static final String pattern = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.";
    public static final int planet = 7;
    public static final String event = "a disturbance in the Force";

    public static void main(String[] args) {
        staticUsage();
        resetMessageFormatPattern();
        resetLocale();
        resetFormat();
    }

    private static void resetFormat() {
        System.out.println("======================print reset format start======================");
        MessageFormat messageFormat = new MessageFormat(pattern);
        print(messageFormat);

        // 重置locale
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy/MM/dd"));
        print(messageFormat);
    }

    private static void resetLocale() {
        System.out.println("======================print reset locale start======================");
        MessageFormat messageFormat = new MessageFormat(pattern);
        print(messageFormat);

        // 重置locale
        messageFormat.setLocale(Locale.CHINA);
        print(messageFormat);
    }

    private static void resetMessageFormatPattern() {
        System.out.println("======================print reset message format pattern start======================");
        MessageFormat messageFormat = new MessageFormat(pattern);
        print(messageFormat);

        // 重置pattern
        messageFormat.applyPattern("The disk \"{1}\" contains {0} file(s).");
        Object[] testArgs = {1273, "MyDisk"};
        System.out.println(messageFormat.format(testArgs));
    }

    private static void print(MessageFormat messageFormat) {
        Object[] args = {planet, new Date(), event};
        String result = messageFormat.format(args);
        System.out.println(result);
    }

    private static void staticUsage() {
        System.out.println("======================print static usage start======================");
        String result = MessageFormat.format(pattern, planet, new Date(), event);
        System.out.println(result);
    }

}
