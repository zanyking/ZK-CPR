/**
 * 
 */
package org.zkoss.util.cpr;

import org.zkoss.spring.core.io.support.ResourcePatternResolver;
import org.zkoss.spring.util.ClassUtils;
import org.zkoss.spring.util.SystemPropertyUtils;

/**
 *  A helper class to help retrieving each "*.class" resource under given package.
 *  
 * 
 * @author Ian YT Tsai(zanyking)
 *
 */
public class ClassFinder<T> extends ClasspathResourceFinder<T>{

	protected String _package_;
	
	/**
	 * 
	 * @param _package_ a String that matches the pattern of java package (ex: "a.b.c") 
	 * @param resourceAllocator cannot be null, 
	 * @param shouldAvoidJava true will assert if the given package is not matching "java" or "javax", 
	 * 		throw IllegalException if _package_ got a match.
	 * @throws IllegalArgumentException
	 */
	public ClassFinder(String _package_, ResourceAllocator<T> resourceAllocator, boolean shouldAvoidJava){
		super(resourceAllocator);
		validate(_package_, shouldAvoidJava);
		this._package_ = _package_;
	}
	/**
	 * 
	 * @param _package_ ex: "a.b.c" or "org.zkoss.zk"
	 * @param visitorArgClass the resource Type that you want to visit by visitor 
	 * @param shouldAvoidJava true will assert if the given package is not matching "java" or "javax", 
	 * throw IllegalException if _package_ got a match.  
	 */
	public ClassFinder(String _package_, Class<T> visitorArgClass, boolean shouldAvoidJava){
		super(visitorArgClass);
		validate(_package_, shouldAvoidJava);
		this._package_ = _package_;
	}
	private static void validate(String _package_, boolean shouldAvoidJava){
		if(_package_==null || !Consts.JAVA_PACKAGE_PTN.matcher(_package_).matches())
			throw new IllegalArgumentException(
				"_package_ is null, empty (default package) or not a valid format: "+_package_);
		if(shouldAvoidJava){
			if(_package_.startsWith("java") || _package_.startsWith("javax"))
				throw new IllegalArgumentException(
					"_package_ shouldn't starts with java or javax: "+ _package_);	
		}
	}
	
	
	protected String resolvePathMatchingResourcePattern(){
		return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
        resolveBasePackage(_package_) +  "/**/*.class";
	}
	
	private  static String resolveBasePackage(String basePackage) {
	    return ClassUtils.convertClassNameToResourcePath(
	    		SystemPropertyUtils.resolvePlaceholders(basePackage));
	}
	

}
