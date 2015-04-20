package com.ns.bean;

import java.io.File;

import com.ns.utils.LogUtils;

import android.os.Environment;

public class Env
{

    /* @formatter:off
     * /SD��Ŀ¼/insurance_esales/data/db/esales.sqlite�ȸ������ݿ��ļ� 
     * /product/product.xml
     * /product/��ƷID/���ֲ�Ʒ��Ϣ(����ʡ��ּۼ�����ʡ���Ʒ����������) 
     *  /template/pdf����ģ��
     * /company_info.xml 
     * /konwledge/ 
     * /konwledge/��Ʒ / 
     * /konwledge/����/
     * /cover/���������ͼƬ
     * /tools/logo������logo 
     * /tools/app������Ӧ��
     * 
     * /images Ӱ����
     * 
     * template\01    -������pdfģ��
		template\02    -��֪ģ��
		template\03    -Ͷ����ʾ
		template\04    -Ͷ����
		template\05    -Ͷ����֪
     */
	
    public final static int PAGESIZE = 10;

    // ֪ʶ��
    public static String KNOWLEDGE_PATH;

    // ������·��
    public static String TOOLS_PATH;
    
    // ������Ӧ��·��
    public static String TOOLS_APP_PATH;
    
    // ������logo·��
    public static String TOOLS_LOGO_PATH;
    
    // ��Ʒ����·��
    public static String PRODUCT_DEFINE_PATH;

    // ���������·������pdfģ��
    public static String PDF_TEMPLATE_PATH;
    // ���������·��
    public static String PROPOSAL_COVER_PATH;
    
    // ���������·��
    public static String PROPOSAL_PDF_PATH;
    // �����ļ�Ŀ¼
    public static String DOWNLOAD_PATH;
    // ҵ��Ա��Ƭ���Ŀ¼
    public static String GENT_PICTURE_PATH;

    // �ļ�����Ŀ¼
    public static String BACKUP_PATH;
    
    // �����������õ�SharedPreferences������
    public final static String PROPOSAL_SP = "proposal";

    /** �����������õ�json����Ͷ����tag **/
    public final static String PROPOSAL_SP_TAG = "proposal_tag";

    public final static String TYPE = "01";// ����android�ͻ���

    public final static String LOGIN_SP = "login";// ��¼�õ�SharedPreferences������

    public final static String LOGIN_SP_TAG = "login_tag";// ��¼�����û���ϢSharedPreferences���õ�tag
    public final static String LOGIN_PWD = "login_pwd"; // SharedPreferences��¼����
    public final static String LOGIN_USERNAME = "login_username"; // SharedPreferences��¼�û���
    public final static String YESTERDAY_DATE = "yesterday";// ��½֮������û��д������֮������Ҫ��ʱ�䣬�����ʱ��͵�ǰʱ��Ƚ�

    public static String DEFAULT_STORAGE_DIRECTORY;// Ĭ�ϴ洢·��
    
    public static String DEFAULT_ROOT_STORAGE_DIRECTORY;// Ĭ�ϴ洢·��

    public static String MEMORY_STORAGE_DIRECTORY;// �ڴ�

    public static String SD_SOTRAGE_DIRECTORY;// sdcard�ϡ�

    public static String DB_PATH;// ���ݿ��·��

    public static final String DEFAULT_DB_NAME = "esales.sqlite";
    
    public static String VALIDATOR_CONFIG_NAME ;// ��֤����������
    
    public static String IMAGES ;
    
    public static String AGENT_PHOTO ;
    
    /**
     * Ͷ��ģ�齨����pdf��ŵ�Ŀ¼
     */
    public static String APPLY_PROPOSAL_PDF_DIR = "UR213";
    
    public static final String DEFAULT_APK_NAME = "eSales_android.apk";
    
    /**
     * ģ��·��
     */
    public static String TEMPLATE_PATH;
    
    /**
     * Ͷ����֪·��
     */
    public static String MATERIAL_SHOW_NOTES_PATH;
    
    /**
     * ��Ʒ˵����
     */
    public static String MATERIAL_SHOW_INSURANCE_PATH;
    
    /**
     * ��֪
     */
    public static String IMPART_PATH;
    
    /**
     *Ͷ����
     */
    public static String APPLY_PATH;
    
    
    static
    {

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {

            SD_SOTRAGE_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
            DEFAULT_STORAGE_DIRECTORY = SD_SOTRAGE_DIRECTORY;

        }
        else
        {
            // �洢��ʽ 0-�ڴ桢1-���SD��
            MEMORY_STORAGE_DIRECTORY = Environment.getDataDirectory().getAbsolutePath() + File.separator;
            DEFAULT_STORAGE_DIRECTORY = MEMORY_STORAGE_DIRECTORY;
        }
        
        LogUtils.logDebug(Env.class, DEFAULT_STORAGE_DIRECTORY);
        
        DEFAULT_ROOT_STORAGE_DIRECTORY = DEFAULT_STORAGE_DIRECTORY;
        
        GENT_PICTURE_PATH = DEFAULT_STORAGE_DIRECTORY + ".insurance_esales/agent_picture/";
        DOWNLOAD_PATH = DEFAULT_STORAGE_DIRECTORY + ".insurance_esales/download/";
        BACKUP_PATH = DEFAULT_STORAGE_DIRECTORY + ".insurance_esales/backup/";
        DEFAULT_STORAGE_DIRECTORY = DEFAULT_STORAGE_DIRECTORY + ".insurance_esales/data/"; 
        
        
        DB_PATH = DEFAULT_STORAGE_DIRECTORY + "db/";
        
        TOOLS_PATH = DEFAULT_STORAGE_DIRECTORY + "tools/";
        TOOLS_APP_PATH = DEFAULT_STORAGE_DIRECTORY + "tools/app/";
        TOOLS_LOGO_PATH = DEFAULT_STORAGE_DIRECTORY + "tools/logo/";
        PRODUCT_DEFINE_PATH = DEFAULT_STORAGE_DIRECTORY + "product/";
        PROPOSAL_COVER_PATH = DEFAULT_STORAGE_DIRECTORY + "cover/";
        KNOWLEDGE_PATH = DEFAULT_STORAGE_DIRECTORY + "knowledge/";
        VALIDATOR_CONFIG_NAME = "validators/validators.xml";
    
        PROPOSAL_PDF_PATH=DEFAULT_STORAGE_DIRECTORY+"proposal/";
        IMAGES = DEFAULT_STORAGE_DIRECTORY+"images/";
        
        TEMPLATE_PATH = DEFAULT_STORAGE_DIRECTORY+"template/";
        PDF_TEMPLATE_PATH=TEMPLATE_PATH+"01/";
        
        MATERIAL_SHOW_NOTES_PATH  = TEMPLATE_PATH + "05/notes.pdf";//E����ϵͳandroid�ͻ����û�����˵����
        MATERIAL_SHOW_INSURANCE_PATH =  TEMPLATE_PATH + "insure.pdf";
        IMPART_PATH = TEMPLATE_PATH +"02/";
        APPLY_PATH = TEMPLATE_PATH +"04/apply_preview.html";
        
    }
}
