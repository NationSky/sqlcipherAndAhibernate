package com.ns.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 字符串工具类
 * 
 * @author zhugl
 * 
 */
@SuppressWarnings({"rawtypes"})
public class StringUtils
{
    /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
    public static String US_ASCII = "US-ASCII";

    /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
    public static String ISO_8859_1 = "ISO-8859-1";

    /** 8 位 UCS 转换格式 */
    public static String UTF_8 = "UTF-8";

    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
    public static String UTF_16 = "UTF-16";

    /** 中文超大字符集 */
    public static String GBK = "GBK";

    /**
     * 在字符串前补0补足count位
     * 
     * @param str
     * @param count
     * @return
     */
    public static String fillBeforeZero(String str, int count)
    {
        if(str == null)
        {
            return null;
        }
        else if(str.length() >= count)
        {
            return str;
        }
        String zeroStr = "";
        for(int i = 0; i < (count - str.length()); i++)
        {
            zeroStr += "0";
        }
        return zeroStr + str;
    }

    /**
     * 在字符串后补0补足count位
     * 
     * @param str
     * @param count
     * @return
     */
    public static String fillAfterZero(String str, int count)
    {
        if(str == null)
        {
            return null;
        }
        else if(str.length() >= count)
        {
            return str;
        }
        String zeroStr = "";
        for(int i = 0; i < (count - str.length()); i++)
        {
            zeroStr += "0";
        }
        return str + zeroStr;
    }

    /**
     * 替换变量
     * 
     * @param strMain
     * @param strFind
     * @param strReplaceWith
     * @return
     */
    public static String replaceEx(String strMain, String strFind, String strReplaceWith)
    {
        StringBuffer tSBql = new StringBuffer();
        String tStrMain = strMain.toLowerCase();
        String tStrFind = strFind.toLowerCase();
        int intStartIndex = 0;
        int intEndIndex = 0;

        if(strMain == null || strMain.equals(""))
        {
            return "";
        }

        while((intEndIndex = tStrMain.indexOf(tStrFind, intStartIndex)) > -1)
        {
            tSBql.append(strMain.substring(intStartIndex, intEndIndex));
            tSBql.append(strReplaceWith);

            intStartIndex = intEndIndex + strFind.length();
        }
        tSBql.append(strMain.substring(intStartIndex, strMain.length()));

        return tSBql.toString();
    }

    /**
     * 
     * @param c_Str
     * @param c_i
     * @param c_Split
     * @return
     */
    public static String findStrBySplit(String c_Str, int c_i, String c_Split)
    {
        String t_Str1 = "", t_Str2 = "", t_strOld = "";
        int i = 0, i_Start = 0;
        t_Str1 = c_Str;
        t_Str2 = c_Split;
        i = 0;
        try
        {
            while(i < c_i)
            {
                i_Start = t_Str1.indexOf(t_Str2, 0);
                if(i_Start >= 0)
                {
                    i += 1;
                    t_strOld = t_Str1;
                    t_Str1 = t_Str1.substring(i_Start + t_Str2.length(), t_Str1.length());
                }
                else
                {
                    if(i != c_i - 1)
                    {
                        t_Str1 = "";
                    }
                    break;
                }
            }

            if(i_Start >= 0)
            {
                t_Str1 = t_strOld.substring(0, i_Start);
            }
        }
        catch(Exception ex)
        {
            t_Str1 = "";
        }
        return t_Str1;
    }

    /**
     * 获取阿拉伯数字和中文数字的对应关系
     * 
     * @param value String
     * @return String
     */
    public static String getChineseNum(String value)
    {
        String sNum = "";
        Integer I = new Integer(value);
        int iValue = I.intValue();
        switch(iValue)
        {
            case 0:
                sNum = "零";
                break;
            case 1:
                sNum = "壹";
                break;
            case 2:
                sNum = "贰";
                break;
            case 3:
                sNum = "叁";
                break;
            case 4:
                sNum = "肆";
                break;
            case 5:
                sNum = "伍";
                break;
            case 6:
                sNum = "陆";
                break;
            case 7:
                sNum = "柒";
                break;
            case 8:
                sNum = "捌";
                break;
            case 9:
                sNum = "玖";
                break;
        }
        return sNum;
    }

    /**
     * Description: 判断一个字符串是否为空
     * 
     * 
     * @param str String 字符串
     * @return boolean 如果为空，则返回true，否则返回false
     */
    public static boolean isNull(String str)
    {
        if(str == null)
        {
            return true;
        }
        return false;
    }

    /**
     * Description: 判断一个字符串是否为空字符串
     * 
     * 
     * @param str String 字符串
     * @return boolean 如果为空字符串，则返回true，否则返回false
     */
    public static boolean isEmpty(String str)
    {
        if(str != null && str.trim().equals(""))
        {
            return true;
        }
        return false;
    }

    /**
     * Description: 去掉字符串中的空格
     * 
     * @param str String 要去掉空格的字符串
     * @return String 去掉空格之后的字符串
     * @throws GenericException
     */
    public static String trim(String str) throws Exception
    {
        try
        {
            if(str == null)
                return "";
            str = str.trim();
            if(str == null)
                return "";
            str = new String(str.getBytes("8859_1"), UTF_8);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        return str;
    }

    /**
     * Description: 转换字符串为字符串数组(默认以,为分隔符)
     * 
     * @param stringSet String 字符串
     * @return String[] 转换后的字符串数组
     * @throws GenericException
     */
    public static String[] split(String stringSet) throws Exception
    {
        return split(stringSet, ",");
    }

    /**
     * Description: 转换字符串为字符串数组(默认以,为分隔符)
     * 
     * @param stringSet String 字符串
     * @param splitor String 分割符
     * @return String[] 转换后的字符串数组
     * @throws GenericException
     */
    public static String[] split(String stringSet, String splitor) throws Exception
    {
        List<String> list = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(stringSet, splitor);
        while(tokens.hasMoreTokens())
        {
            list.add(StringUtils.trim(tokens.nextToken()));
        }
        String[] stringArray = new String[list.size()];

        list.toArray(stringArray);
        return stringArray;
    }

    /**
     * Description: 将list转换成string,中间以,为分隔符
     * 
     * @param addressList List 接收人或抄送人的地址list
     * @return String 转换后的字符串(以,为分隔符)
     */
    public static String toString(List addressList)
    {
        return toString(addressList, ",");
    }

    /**
     * Description: 将list转换成string,中间以指定分割符隔开
     * 
     * @param list List list
     * @param splitor String 分割符
     * @return String 转换后的字符串
     */
    public static String toString(List list, String splitor)
    {
        String str = "";
        if(list != null)
        {
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                str += it.next().toString();
                if(it.hasNext())
                {
                    str += splitor;
                }
            }
        }
        return str;
    }

    /**
     * Description: 判断一个字符串是否为空或为空字符串
     * 
     * 
     * @param str String 字符串
     * @return boolean 如果为空或空字符串，则返回true，否则返回false
     */
    public static boolean isNullOrEmpty(String str)
    {
        return isNull(str) || isEmpty(str);
    }

    public static void main(String args[])
    {
        System.out.println(findStrBySplit("asdjlg?Prem?djskg?Amount? sdg", 100, "?").equals(""));
    }

    /**
     * 判断字符是否是中文字符
     * 
     * @param c
     * @return
     */
    public static boolean isChinese(char c)
    {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
        {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否是中文字符串
     * 
     * @param strName
     * @return
     */
    public static boolean isChinese(String word)
    {
        for(int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            if(!isChinese(c))
            {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符是否是英文字符
     * @param c
     * @return
     */
    public static boolean isEnglish(char c) {  
        if ((c >= 'A' && c <= 'Z')  
                        || (c >= 'a' && c <= 'z')) {  
                    return true;  
                }  
        return false;
    }
    /**
     * 判断字符串是否为英文字符串
     * @param word
     * @return
     */
    public static boolean isEnglish(String word) {  
        for(int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            if(!isEnglish(c))
            {
                return false;
            }
        }
        return true;
    }  
}
