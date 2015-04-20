/**
 * 
 */
package com.ns.cst;

/**
 * @author jiang
 * 
 */
public class SexCst
{
    
    public static final String MALE = "0";
    public  static final String FEMALE = "1";
    
    public  static String getSexMale(String  sex){
        if(sex.equals(MALE)){
            return "ÄÐ";
        }else{
        
            return "Å®";
        }
        
    }
}
