package com.ns.bean;

import java.io.File;

import com.ns.utils.LogUtils;

import android.os.Environment;

public class Env
{

    /* @formatter:off
     * /SD卡目录/insurance_esales/data/db/esales.sqlite等各种数据库文件 
     * /product/product.xml
     * /product/产品ID/各种产品信息(如费率、现价计算费率、产品利益描述等) 
     *  /template/pdf生成模版
     * /company_info.xml 
     * /konwledge/ 
     * /konwledge/产品 / 
     * /konwledge/规则/
     * /cover/建议书封面图片
     * /tools/logo工具箱logo 
     * /tools/app工具箱应用
     * 
     * /images 影像存放
     * 
     * template\01    -建议书pdf模板
		template\02    -告知模板
		template\03    -投保提示
		template\04    -投保单
		template\05    -投保须知
     */
	
    public final static int PAGESIZE = 10;

    // 知识库
    public static String KNOWLEDGE_PATH;

    // 工具箱路径
    public static String TOOLS_PATH;
    
    // 工具箱应用路径
    public static String TOOLS_APP_PATH;
    
    // 工具箱logo路径
    public static String TOOLS_LOGO_PATH;
    
    // 产品定义路径
    public static String PRODUCT_DEFINE_PATH;

    // 建议书封面路径生成pdf模版
    public static String PDF_TEMPLATE_PATH;
    // 建议书封面路径
    public static String PROPOSAL_COVER_PATH;
    
    // 建议书输出路径
    public static String PROPOSAL_PDF_PATH;
    // 下载文件目录
    public static String DOWNLOAD_PATH;
    // 业务员照片存放目录
    public static String GENT_PICTURE_PATH;

    // 文件备份目录
    public static String BACKUP_PATH;
    
    // 建议书制作用的SharedPreferences的名称
    public final static String PROPOSAL_SP = "proposal";

    /** 建议书制作用的json串中投保人tag **/
    public final static String PROPOSAL_SP_TAG = "proposal_tag";

    public final static String TYPE = "01";// 代表android客户端

    public final static String LOGIN_SP = "login";// 登录用的SharedPreferences的名称

    public final static String LOGIN_SP_TAG = "login_tag";// 登录保存用户信息SharedPreferences所用的tag
    public final static String LOGIN_PWD = "login_pwd"; // SharedPreferences登录密码
    public final static String LOGIN_USERNAME = "login_username"; // SharedPreferences登录用户名
    public final static String YESTERDAY_DATE = "yesterday";// 登陆之后提醒没有写过工作之日所需要的时间，用这个时间和当前时间比较

    public static String DEFAULT_STORAGE_DIRECTORY;// 默认存储路径
    
    public static String DEFAULT_ROOT_STORAGE_DIRECTORY;// 默认存储路径

    public static String MEMORY_STORAGE_DIRECTORY;// 内存

    public static String SD_SOTRAGE_DIRECTORY;// sdcard上。

    public static String DB_PATH;// 数据库的路径

    public static final String DEFAULT_DB_NAME = "esales.sqlite";
    
    public static String VALIDATOR_CONFIG_NAME ;// 验证配置器名称
    
    public static String IMAGES ;
    
    public static String AGENT_PHOTO ;
    
    /**
     * 投保模块建议书pdf存放的目录
     */
    public static String APPLY_PROPOSAL_PDF_DIR = "UR213";
    
    public static final String DEFAULT_APK_NAME = "eSales_android.apk";
    
    /**
     * 模板路径
     */
    public static String TEMPLATE_PATH;
    
    /**
     * 投保须知路径
     */
    public static String MATERIAL_SHOW_NOTES_PATH;
    
    /**
     * 产品说明书
     */
    public static String MATERIAL_SHOW_INSURANCE_PATH;
    
    /**
     * 告知
     */
    public static String IMPART_PATH;
    
    /**
     *投保单
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
            // 存储方式 0-内存、1-外接SD卡
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
        
        MATERIAL_SHOW_NOTES_PATH  = TEMPLATE_PATH + "05/notes.pdf";//E行销系统android客户端用户需求说明书
        MATERIAL_SHOW_INSURANCE_PATH =  TEMPLATE_PATH + "insure.pdf";
        IMPART_PATH = TEMPLATE_PATH +"02/";
        APPLY_PATH = TEMPLATE_PATH +"04/apply_preview.html";
        
    }
}
