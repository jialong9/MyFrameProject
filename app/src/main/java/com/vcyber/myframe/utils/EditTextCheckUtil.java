package com.vcyber.myframe.utils;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by zjl on 2019/9/19
 * ---- 输入框验证工具类 ----
 */
public class EditTextCheckUtil {

    private int mPreDot;
    private int mBehindDot;

    public TextWatcher getTextWatcher() {
        return mTextWatcher;
    }

    public TextWatcher getTextWatcherTwo() {
        return mTextWatcherTwo;
    }

    public TextWatcher getTextWatcherN(int preDot, int behindDot) {
        mPreDot = preDot;
        mBehindDot = behindDot;
        return mTextWatcherN;
    }


    /**
     * 小数点位数保留前5后2位
     */
    public TextWatcher mTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable edt) {
            judgeNumber(edt);
        }

        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    };

    /**
     * 小数点位数保留前2后2位
     */
    public TextWatcher mTextWatcherTwo = new TextWatcher() {
        public void afterTextChanged(Editable edt) {
            judgeNumberTwo(edt);
        }

        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    };

    /**
     * 小数点位数保留前N后N位
     */
    private TextWatcher mTextWatcherN = new TextWatcher() {
        public void afterTextChanged(Editable edt) {
            judgeNumberN(edt, mPreDot, mBehindDot);
        }

        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    };

    /**
     * 金额输入框中的内容限制（最大：小数点前N位，小数点后N位）
     *
     * @param edt
     */
    private void judgeNumberN(Editable edt, int preDot, int behindDot) {

        String temp = edt.toString();
        int posDot = temp.indexOf(".");//返回指定字符在此字符串中第一次出现处的索引
        if (posDot < 0) {//不包含小数点
            if (temp.length() <= preDot) {
                return;//小于五位数直接返回
            } else {
                edt.delete(preDot, ++preDot);//大于五位数就删掉第六位（只会保留五位）
                return;
            }
        }

        //走到这有小数点
        if (posDot > preDot) {
            edt.delete(preDot, ++preDot);//大于五位数就删掉第六位（只会保留五位）
            return;
        }
        if (temp.length() - posDot - 1 > behindDot) {//如果包含小数点
            int a = behindDot;
            ++a;
            int b = behindDot;
            int b2 = b + 2;
            edt.delete(posDot + a, posDot + b2);//删除小数点后的第三位
        }
    }

    /**
     * 金额输入框中的内容限制（最大：小数点前五位，小数点后2位）
     *
     * @param edt
     */
    private void judgeNumber(Editable edt) {

        String temp = edt.toString();
        int posDot = temp.indexOf(".");//返回指定字符在此字符串中第一次出现处的索引
        if (posDot < 0) {//不包含小数点
            if (temp.length() <= 5) {
                return;//小于五位数直接返回
            } else {
                edt.delete(5, 6);//大于五位数就删掉第六位（只会保留五位）
                return;
            }
        }

        //走到这有小数点
        if (posDot > 5) {
            edt.delete(5, 6);//大于五位数就删掉第六位（只会保留五位）
            return;
        }
        if (temp.length() - posDot - 1 > 2)//如果包含小数点
        {
            edt.delete(posDot + 3, posDot + 4);//删除小数点后的第三位
        }
    }

    /**
     * 金额输入框中的内容限制（最大：小数点前2位，小数点后2位）
     *
     * @param edt
     */
    private void judgeNumberTwo(Editable edt) {

        String temp = edt.toString();
        int posDot = temp.indexOf(".");//返回指定字符在此字符串中第一次出现处的索引
        if (posDot < 0) {//不包含小数点
            if (temp.length() <= 2) {
                return;//小于2位数直接返回
            } else {
                edt.delete(2, 3);//大于五位数就删掉第3位（只会保留2位）
                return;
            }
        }
        //走到这有小数点
        if (posDot > 2) {
            edt.delete(2, 3);//大于五位数就删掉第六位（只会保留五位）
            return;
        }

        if (temp.length() - posDot - 1 > 2)//如果包含小数点
        {
            edt.delete(posDot + 3, posDot + 4);//删除小数点后的第3位
        }
    }

    /**
     * @prama: str 要判断是否包含特殊字符 表情 的目标字符串
     */

    public static boolean compileExChar(String str) {

        String limitEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？][\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";

        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);

        if (m.find()) {
            //            Toast.makeText(InputActivity.this, "不允许输入特殊符号！", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }
    }

    /**
     * @prama: str 只检查 表情 的目标字符串
     */

    public static boolean compileExpressionChar(String str) {

        String limitEx = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";

        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);

        if (m.find()) {
            //            Toast.makeText(InputActivity.this, "不允许输入特殊符号！", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }
    }


    /**
     * 正则表达式:验证用户名(不包含中文和特殊字符)如果用户名使用手机号码或邮箱 则结合手机号验证和邮箱验证
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式:验证密码(不包含特殊字符)
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式:验证手机号
     */
    public static final String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";

    /**
     * 正则表达式:验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式:验证汉字(1-9个汉字)  {1,9} 自定义区间
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,9}$";

    /**
     * 正则表达式:验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";

    /**
     * 正则表达式:验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w ./?%&=]*)?";

    /**
     * 正则表达式:验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUserName(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddress
     * @return
     */
    public static boolean isIPAddress(String ipAddress) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddress);
    }

}
