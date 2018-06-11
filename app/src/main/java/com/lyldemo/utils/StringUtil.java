package com.lyldemo.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {

    /**
     * Encode URL using "UTF-8" format.
     *
     * @param value
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeURL(String value) throws UnsupportedEncodingException {
        if (!isEmpty(value)) {
            return URLEncoder.encode(value, "UTF-8");
        }
        return "";
    }

    /**
     * Decode URL using "UTF-8" format.
     *
     * @param value
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeURL(String value) throws UnsupportedEncodingException {
        if (!isEmpty(value)) {
            return URLDecoder.decode(value, "UTF-8");
        }
        return "";
    }

    /**
     * check if the string is empty
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().equals("") || value.length() == 0;
    }

    /**
     * Check the string whether is long data type
     *
     * @param str
     * @return
     */
    public static boolean isLong(String str) {
        if ("0".equals(str.trim())) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[^0]\\d*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Check the string whether is float data type
     *
     * @param str
     * @return
     */
    public static boolean isFloat(String str) {
        if (isLong(str)) {
            return true;
        }
        Pattern pattern = Pattern.compile("\\d*\\.{1}\\d+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Check the string whether is float data type with precison
     *
     * @param str
     * @param precision
     * @return
     */
    public static boolean isFloat(String str, int precision) {
        String regStr = "\\d*\\.{1}\\d{" + precision + "}";
        Pattern pattern = Pattern.compile(regStr);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveInteger(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Check the string whether is number data type
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (isLong(str)) {
            return true;
        }
        Pattern pattern = Pattern.compile("(-)?(\\d*)\\.{0,1}(\\d*)");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Check the string whether is email data type
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        Matcher isEMail = pattern.matcher(str);
        if (!isEMail.matches()) {
            return false;
        }
        return true;
    }

    /*
     * 验证身份证号码是否合法
     */
    public static boolean isIdentityNo(String str) {
        if (str != null) {
            str = str.toLowerCase();
        }
        Pattern pattern = Pattern.compile("^(\\d{18,18}|\\d{15,15}|\\d{17,17}x)$");
        Matcher isIdentityNo = pattern.matcher(str);
        return isIdentityNo.matches();
    }

    /*
     * 验证输入是否为电话号码或手机号码
     */
    public static boolean isValidatePhone(String phone) {

        String regEx = "^\\d{3,4}-\\d{7,8}(-\\d{3,4})?|1\\d{10}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 验证输入是否为手机号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regEx = "^(1[3,4,5,7,8]\\d{9}$)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * Format the specified String with param placeholder list. ex. the string
     * like format "I am {0},and she comes from {1}."
     *
     * @param str
     * @param args the param placeholder list.
     * @return the formated string
     */
    public static String format(String str, Object... args) {

        if (StringUtil.isEmpty(str) || args.length == 0) {
            return str;
        }

        String result = str;
        Pattern pattern = Pattern.compile("\\{(\\d+)\\}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            if (index < args.length) {
                result = result.replace(matcher.group(), args[index].toString());
            }
        }

        return result;
    }

    /**
     * Format price
     *
     * @param price
     * @return format like "￥123.00" or "￥-123.00"
     */
    public static String priceToString(double price) {
        String priceString = new DecimalFormat("###,###,###.##").format(Math.abs(price));
        if (!priceString.contains(".")) {
            priceString = priceString + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###.00");
            priceString = format.format(Math.abs(price));
        }
        if (priceString.indexOf(".") == 0) {
            priceString = "0" + priceString;
        }
        if (price < 0) {
            return "¥-" + priceString;
        } else if (price < 1) {
            return "¥" + priceString;
        } else {
            return "¥" + priceString;
        }
    }

    public static String priceToNoString(double price) {
        String priceString = new DecimalFormat("#########.##").format(Math.abs(price));
        if (!priceString.contains(".")) {
            priceString = priceString + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#########.00");
            priceString = format.format(Math.abs(price));
        }
        if (priceString.indexOf(".") == 0) {
            priceString = "0" + priceString;
        }
        if (price < 0) {
            return "¥-" + priceString;
        } else if (price < 1) {
            return "¥" + priceString;
        } else {
            return "¥" + priceString;
        }
    }


    public static String priceToString3(double price) {
        String priceString = new DecimalFormat("###,###,###.##").format(Math.abs(price));
        if (!priceString.contains(".")) {
            priceString = priceString + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###.00");
            priceString = format.format(Math.abs(price));
        }
        if (priceString.indexOf(".") == 0) {
            priceString = "0" + priceString;
        }
        if (price < 0) {
            return "-" + priceString;
        } else if (price < 1) {
            return priceString;
        } else {
            return priceString;
        }
    }


    /**
     * Format price
     *
     * @param price
     * @return
     */
    public static String quanPriceToString(double price) {
        String priceString = new DecimalFormat("###,###,###.##").format(Math.abs(price));
        return priceString;
    }

    /**
     * Format price
     *
     * @param price
     * @return format like "￥123.00" or "-￥123.00"
     */
    public static String priceToString2(double price) {
        String priceString = new DecimalFormat("###,###,###.##").format(Math.abs(price));
        if (!priceString.contains(".")) {
            priceString = priceString + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###.00");
            priceString = format.format(Math.abs(price));
        }
        if (priceString.indexOf(".") == 0) {
            priceString = "0" + priceString;
        }
        if (price < 0) {
            return "-¥" + priceString;
        } else if (price < 1) {
            return "¥" + priceString;
        } else {
            return "¥" + priceString;
        }
    }


    public static String priceToString4(double price) {
        String priceString = new DecimalFormat("###,###,###.##").format(Math.abs(price));
        if (!priceString.contains(".")) {
            priceString = priceString + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("###,###,###.00");
            priceString = format.format(Math.abs(price));
        }
        if (priceString.indexOf(".") == 0) {
            priceString = "0" + priceString;
        }
        if (price <= 0) {
            return "-¥" + priceString;
        } else if (price < 1) {
            return "¥" + priceString;
        } else {
            return "¥" + priceString;
        }
    }

    /**
     * format point
     *
     * @param point
     * @return
     */
    public static String getPointToString(float point) {
        String priceString = new DecimalFormat("###,###,###.##").format(Math.abs(point));
        return priceString;
    }

    /**
     * format number to price
     *
     * @param number
     * @return
     */
    public static String getPriceByFloat(float number) {

        String mynumber = String.valueOf(number);
        if (!mynumber.contains(".")) {
            mynumber = mynumber + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#.00");
            mynumber = format.format(number);
            if (mynumber.indexOf(".") == 0) {
                mynumber = "0" + mynumber;
            }

        }

        return mynumber;

    }

    /**
     * @param number
     * @return
     */
    public static String getPriceByDouble(double number) {

        String mynumber = String.valueOf(number);
        if (!mynumber.contains(".")) {
            mynumber = mynumber + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#.00");
            mynumber = format.format(number);
            if (mynumber.indexOf(".") == 0) {
                mynumber = "0" + mynumber;
            }
        }

        return mynumber;
    }

    /**
     * get price from string
     *
     * @param inputString
     * @return
     */
    public static float stringToPrice(String inputString) {
        if (isEmpty(inputString))
            return 0.0f;
        if (inputString.contains("¥")) {
            inputString = inputString.replace("¥", "");
        }
        inputString = inputString.replaceAll(",", "");
        return Float.parseFloat(inputString);
    }

    /**
     * 检查是否是中文
     *
     * @param str
     * @return
     */
    public static boolean checkStringIsChinese(String str) {
        String regEx = "^[\\u4E00-\\u9FA5]{2,10}$|^[\\u4E00-\\u9FA5]{1,10}([\\.•∙．・･]{1}[\\u4E00-\\u9FA5]{1,10})+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String fromTime(long time) {
        if (String.valueOf(time).length() < 2) {
            return "0" + String.valueOf(time);
        }

        return String.valueOf(time);
    }

    /**
     * 格式化价格
     *
     * @param price
     * @return
     */
    public static String fromPrice(double price) {
        String str = String.valueOf(price);
        if (str.contains(".")) {
            String[] d = str.split("\\.");
            if (Integer.parseInt(d[1]) > 0) {
                return StringUtil.getPriceByDouble(price);
            } else {
                return d[0];
            }
        } else {
            return String.valueOf(price);
        }
    }

    /**
     * @param number
     * @return
     */
    public static Spannable formatPrice(Context context, double number) {

        String mynumber = String.valueOf(number);
        if (!mynumber.contains(".")) {
            mynumber = mynumber + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#.00");
            mynumber = format.format(number);
            if (mynumber.indexOf(".") == 0) {
                mynumber = "0" + mynumber;
            }
        }
        Spannable textSpan = new SpannableStringBuilder("¥" + mynumber);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 12)), 0, mynumber.indexOf(".") + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 11)), mynumber.indexOf(".") + 1, mynumber.length() + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    /**
     * @param number
     * @return
     */
    public static Spannable formatPriceStore(Context context, double number) {

        String mynumber = String.valueOf(number);
        if (!mynumber.contains(".")) {
            mynumber = mynumber + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#.00");
            mynumber = format.format(number);
            if (mynumber.indexOf(".") == 0) {
                mynumber = "0" + mynumber;
            }
        }
        Spannable textSpan = new SpannableStringBuilder("¥" + mynumber);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 12)), 0, mynumber.indexOf(".") + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 10)), mynumber.indexOf(".") + 1, mynumber.length() + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    /**
     * @param number
     * @return
     */
    public static Spannable formatPriceTo(Context context, double number) {

        String mynumber = String.valueOf(number);
        if (!mynumber.contains(".")) {
            mynumber = mynumber + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#.00");
            mynumber = format.format(number);
            if (mynumber.indexOf(".") == 0) {
                mynumber = "0" + mynumber;
            }
        }
        Spannable textSpan = new SpannableStringBuilder("¥" + mynumber);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 14)), 0, mynumber.indexOf(".") + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 11)), mynumber.indexOf(".") + 1, mynumber.length() + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }

    /**
     * @param number
     * @return
     */
    public static Spannable formatPriceTo2(Context context, double number) {

        String mynumber = String.valueOf(number);
        if (!mynumber.contains(".")) {
            mynumber = mynumber + ".00";
        } else {
            DecimalFormat format = new DecimalFormat("#.00");
            mynumber = format.format(number);
            if (mynumber.indexOf(".") == 0) {
                mynumber = "0" + mynumber;
            }
        }
        Spannable textSpan = new SpannableStringBuilder(String.valueOf(mynumber));
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 18)), 0, mynumber.indexOf("."), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(DisplayUtil.getPxByDp(context, 13)), mynumber.indexOf("."), mynumber.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }


    /**
     * @param psw
     * @return
     */
    public static boolean isPassword(String psw) {
        String regEx = "((?=.*\\d)(?=.*[a-z])(?=.*[`()+=|{}':;',\\[\\]\\/?~……?$#@!~%^&*.-<>])|(?=.*\\d)(?=.*[`()+=|{}':;',\\[\\]\\/?~……?$#@!~%^&*.-<>])|(?=.*[a-z])(?=.*[`()+=|{}':;',\\[\\]\\/?~……?$#@!~%^&*.-<>])|(?=.*\\d)(?=.*[a-z]))[a-z\\d`()+=|{}':;',\\[\\]\\/?~……?$#@!~%^&*.-<>]{6,20}";
        //		String regEx = "((?=.*[a-z])(?=.*\\d)|(?=[a-z])(?=.*[[\\]./?~!@#$%……&*()——+|{}[]';:\".,？])|(?=.*\\d)(?=.*[`~!@#$%^&*()+=|{}':;',\\[\\]./?~!@#$%……&*()——+|{}[]';:\".,？]))[a-z\\d[`~!@#$%^&*()+=|{}':;',\\[\\]./?~!@#%……&*()——+|{}[]';:\".,？\\]]{6,20}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(psw);
        return m.matches();
    }

    /**
     * 将电话号码中间四位置隐藏
     *
     * @param mobile
     * @return
     */
    public static String showHideMobile(String mobile) {
        String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
        return maskNumber;
    }

    /**
     * @param targetStr 要处理的字符串
     * @description 切割字符串，将文本和img标签碎片化，如"ab<img>cd"转换为"ab"、"<img>"、"cd"
     */
    public static List<String> cutStringByImgTag(String targetStr) {
        List<String> splitTextList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("<img.*?src=\\\"(.*?)\\\".*?>");
        Matcher matcher = pattern.matcher(targetStr);
        int lastIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > lastIndex) {
                splitTextList.add(targetStr.substring(lastIndex, matcher.start()));
            }
            splitTextList.add(targetStr.substring(matcher.start(), matcher.end()));
            lastIndex = matcher.end();
        }
        if (lastIndex != targetStr.length()) {
            splitTextList.add(targetStr.substring(lastIndex, targetStr.length()));
        }
        return splitTextList;
    }

    /**
     * 获取img标签中的src值
     *
     * @param content
     * @return
     */
    public static String getImgSrc(String content) {
        String str_src = null;
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);

                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    str_src = m_src.group(3);
                }
                //结束匹配<img />标签中的src

                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return str_src;
    }

    /**
     * 半角转全角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
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
     * 数量转W+
     *
     * @param par
     * @return
     */
    public static String toMillion(int par) {
        StringBuffer restult = new StringBuffer();
        if (par / 10000 > 0) {
            restult.append(par / 10000);
            int thousand = par % 10000;
            if (thousand >= 1000) {
                int i = (par % 10000) / 1000;
                restult.append(".").append(i);
            }
            restult.append("W");
            if (thousand % 1000 > 0) {
                restult.append("+");
            }
        } else {
            restult.append(par);
        }
        return restult.toString();
    }
}
