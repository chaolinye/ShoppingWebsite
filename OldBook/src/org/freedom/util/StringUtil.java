package org.freedom.util;
/**
 * 字符串操作工具类
 * @author chaolin
 *
 */
public class StringUtil {
	
	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
}
