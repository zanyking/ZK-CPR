/**
 * 
 */
package org.zkoss.util.cpr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ian YT Tsai(zanyking)
 *
 */
public class Consts {
	private static final String SEGMENT = "[\\w\\$&&[^0-9]][\\w\\$]*";
	public static final Pattern JAVA_PACKAGE_PTN = Pattern.compile(SEGMENT+"([\\.]"+SEGMENT+")*");
	
	public static void main(String[] args){
		String testPackage;
		Matcher ma  = null;
		
		Pattern ptn = Pattern.compile(SEGMENT);
		
		ma = ptn.matcher(testPackage = "");
		System.out.println("SEGMENT has a match? "+testPackage+" : " + ma.matches());
		
		ma = ptn.matcher(testPackage = "abc_");
		System.out.println("SEGMENT has a match? "+testPackage+" : " + ma.matches());
		
		ma = ptn.matcher(testPackage = "abc.");
		System.out.println("SEGMENT has a match? "+testPackage+" : " + ma.matches());
		
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "a.");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "$$.$$$.$$$$");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "12.345.6789");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "a.b.c");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "a.b.c..");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "a..b.c");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "a..##b.c");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
		
		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "$$a.b1234.c___");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());

		ma = JAVA_PACKAGE_PTN.matcher(testPackage = "$$a.b1234.c-345");
		System.out.println("has a match? "+testPackage+" : " + ma.matches());
	}
	
}
