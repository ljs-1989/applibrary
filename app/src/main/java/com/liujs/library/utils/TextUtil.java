package com.liujs.library.utils;

import android.support.annotation.NonNull;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static void limitInputNumber(@NonNull EditText editText, String s) {
        String text = s;
        if (!android.text.TextUtils.isEmpty(text) && text.contains(".")) {
            int dotIndex = text.indexOf('.'); // 点的位置 同时也代表 点前面有几个字
            if (dotIndex == 0) { // 第一个输入的是点
                editText.setText("");
            } else if (text.length() - dotIndex - 1 > 2) { // 3.66  4 - 1 - 1 小数点的位数 = 长度 - 点前面的字数 - 点
                String nexString = text.substring(0, dotIndex + 1 + 2); // 长度 = 点前面的字数 + 点 + 小数位数2
                editText.setText(nexString);
                editText.setSelection(nexString.length());
            }
        }
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 字符判空
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 数值保留N小数
     *
     * @param big   转换前数值
     * @param scale 保留小数的位数
     * @return 转换后字符串
     */
    public static String decimalToString(BigDecimal big, int scale) {
        big.setScale(scale, RoundingMode.HALF_UP);
        StringBuffer strFormatRest = new StringBuffer();
        for (int i = 0; i < scale; i++) {
            strFormatRest.append("0");
        }
        DecimalFormat format = null;
        if (strFormatRest.length() > 0) {
            format = new DecimalFormat("##0." + strFormatRest);
        } else {
            format = new DecimalFormat("##0");
        }
        return format.format(big);
    }

    /**
     * 屏蔽用户名（例：长度大于5，则隐藏前3位；反之隐藏前1位）
     * @return
     */
    public static String shieldingUserName(String userName) {
        if (userName == null || userName.length() == 0) {
            return "";
        } else {
            if (userName.length() >= 5) {

                int length = userName.length() - 2;
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < length; i++) {
                    sb.append("*");
                }
                return userName.substring(0, 2) + sb.toString();
            } else {
                int length = userName.length() - 1;
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < length; i++) {
                    sb.append("*");
                }
                return userName.substring(0, 1) + sb.toString();
            }
        }
    }

    /**
     * 屏蔽手机号码（例：186****0571）
     *
     * @param phone
     * @return
     */
    public static String shieldingPhone(String phone) {
        if (phone == null || phone.length() < 9) {
            return null;
        } else {
            return phone.replace(phone.substring(3, 7), "****");
        }
    }

    /**
     * 屏蔽银行卡卡号（例：6621****1978）
     *
     * @param card
     * @return
     */
    public static String shieldingBankCardNum(String card) {
        if (card == null || card.length() < 9) {
            return "";
        } else {
            return card.replace(card.substring(4, card.length() - 4), "****");
        }
    }

    /**
     * 屏蔽身份证号码
     *
     * @param identNum
     * @return
     */
    public static String shieldingIdentNum(String identNum) {
        if (identNum == null || identNum.length() < 15) {
            return "";
        } else {
            return identNum.replace(identNum.substring(1, identNum.length() - 1), "*****************");
        }
    }

    /**
     * 半角转换为全角(解决TextView换行问题)
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号(解决TextView换行问题)
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * @return
     */
    public static boolean checkStringNotZero(String number) {
        try {
            Double temp = Double.parseDouble(number);
            if (temp > 0) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    /**
     * 字符串转换为整形
     *
     * @param intOfString
     * @return
     */
    public static int stringToInteger(String intOfString) {

        try {
            if (intOfString.contains(".")) {
                return Integer.parseInt(intOfString.substring(0, intOfString.indexOf(".")));
            } else {
                return Integer.parseInt(intOfString);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return 0;
        }
    }

    /**
     * 将double转化成带相应位数小数的double
     *
     * @param doubleValue
     * @return
     */
    public static String getDecimalFormatFromDouble(Double doubleValue, int decimalNum) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < decimalNum; i++) {
            stringBuffer.append("0");
        }
        DecimalFormat df = null;
        if (stringBuffer.length() > 0) {
            if (doubleValue >= 1) {
                df = new DecimalFormat("#." + stringBuffer);
            } else {
                df = new DecimalFormat("0." + stringBuffer);
            }
        } else {
            if (doubleValue == 0) {
                return "0";
            } else {
                df = new DecimalFormat("#");
            }

        }
        String result = df.format(doubleValue);

        return result;
    }

    /**
     * 金额格式化
     *
     * @param
     * @return
     */

    public static String parseMoney(String pattern, String math) {
        double totalIncome = 0.00;
        try {
            totalIncome = Double.parseDouble(math);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        BigDecimal bd = new BigDecimal(totalIncome);
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(bd);
    }

    /**
     * 解析字符串为数组
     *
     * @param JsonAraayOfLevelValue
     * @return
     */
    public static String[] DecodeJsonAraayOfLevelValue(String JsonAraayOfLevelValue) {
        String[] array = JsonAraayOfLevelValue.split(",");

        return array;
    }


    public static float round(float value, int sclae, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(sclae, roundingMode);
        float d = bd.floatValue();
        bd = null;
        return d;
    }

}
