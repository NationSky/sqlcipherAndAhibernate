package com.ns.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * �ַ���������
 * 
 * @author zhugl
 * 
 */
@SuppressWarnings({"rawtypes"})
public class StringUtils
{
    /** 7λASCII�ַ���Ҳ����ISO646-US��Unicode�ַ����Ļ��������� */
    public static String US_ASCII = "US-ASCII";

    /** ISO ������ĸ�� No.1��Ҳ���� ISO-LATIN-1 */
    public static String ISO_8859_1 = "ISO-8859-1";

    /** 8 λ UCS ת����ʽ */
    public static String UTF_8 = "UTF-8";

    /** 16 λ UCS ת����ʽ���ֽ�˳���ɿ�ѡ���ֽ�˳��������ʶ */
    public static String UTF_16 = "UTF-16";

    /** ���ĳ����ַ��� */
    public static String GBK = "GBK";

    /**
     * ���ַ���ǰ��0����countλ
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
     * ���ַ�����0����countλ
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
     * �滻����
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
     * ��ȡ���������ֺ��������ֵĶ�Ӧ��ϵ
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
                sNum = "��";
                break;
            case 1:
                sNum = "Ҽ";
                break;
            case 2:
                sNum = "��";
                break;
            case 3:
                sNum = "��";
                break;
            case 4:
                sNum = "��";
                break;
            case 5:
                sNum = "��";
                break;
            case 6:
                sNum = "½";
                break;
            case 7:
                sNum = "��";
                break;
            case 8:
                sNum = "��";
                break;
            case 9:
                sNum = "��";
                break;
        }
        return sNum;
    }

    /**
     * Description: �ж�һ���ַ����Ƿ�Ϊ��
     * 
     * 
     * @param str String �ַ���
     * @return boolean ���Ϊ�գ��򷵻�true�����򷵻�false
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
     * Description: �ж�һ���ַ����Ƿ�Ϊ���ַ���
     * 
     * 
     * @param str String �ַ���
     * @return boolean ���Ϊ���ַ������򷵻�true�����򷵻�false
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
     * Description: ȥ���ַ����еĿո�
     * 
     * @param str String Ҫȥ���ո���ַ���
     * @return String ȥ���ո�֮����ַ���
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
     * Description: ת���ַ���Ϊ�ַ�������(Ĭ����,Ϊ�ָ���)
     * 
     * @param stringSet String �ַ���
     * @return String[] ת������ַ�������
     * @throws GenericException
     */
    public static String[] split(String stringSet) throws Exception
    {
        return split(stringSet, ",");
    }

    /**
     * Description: ת���ַ���Ϊ�ַ�������(Ĭ����,Ϊ�ָ���)
     * 
     * @param stringSet String �ַ���
     * @param splitor String �ָ��
     * @return String[] ת������ַ�������
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
     * Description: ��listת����string,�м���,Ϊ�ָ���
     * 
     * @param addressList List �����˻����˵ĵ�ַlist
     * @return String ת������ַ���(��,Ϊ�ָ���)
     */
    public static String toString(List addressList)
    {
        return toString(addressList, ",");
    }

    /**
     * Description: ��listת����string,�м���ָ���ָ������
     * 
     * @param list List list
     * @param splitor String �ָ��
     * @return String ת������ַ���
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
     * Description: �ж�һ���ַ����Ƿ�Ϊ�ջ�Ϊ���ַ���
     * 
     * 
     * @param str String �ַ���
     * @return boolean ���Ϊ�ջ���ַ������򷵻�true�����򷵻�false
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
     * �ж��ַ��Ƿ��������ַ�
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
     * �ж��ַ����Ƿ��������ַ���
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
     * �ж��ַ��Ƿ���Ӣ���ַ�
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
     * �ж��ַ����Ƿ�ΪӢ���ַ���
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
