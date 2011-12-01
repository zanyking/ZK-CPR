/**
 * 
 */
package org.zkoss.util.cpr;

import java.io.IOException;

import org.zkoss.spring.core.io.Resource;
import org.zkoss.spring.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author Ian YT Tsai(zanyking)
 *
 */
public abstract class ClasspathResourceFinder<T> {
	protected ResourceAllocator<T> _resourceAllocator;

	/**
	 * 
	 */
	public ClasspathResourceFinder(){}
	/**
	 * 
	 * @param _resourceAllocator
	 */
	public ClasspathResourceFinder(ResourceAllocator<T> _resourceAllocator) {
		this._resourceAllocator = _resourceAllocator;
	}
	/**
	 * 
	 * @param visitorArgClass
	 */
	public ClasspathResourceFinder( Class<T> visitorArgClass) {
		this._resourceAllocator = ResourceAllocatorFactory.getInstance(visitorArgClass);
	}
	/**
	 * 
	 * @return
	 */
	public ResourceAllocator<T> getResourceAllocator() {
		return _resourceAllocator;
	}
	/**
	 * 
	 * @param _resourceAllocator
	 */
	public void setResourceAllocator(ResourceAllocator<T> _resourceAllocator) {
		this._resourceAllocator = _resourceAllocator;
	}
	
	
	/**
	 * 
	 * @param visitor
	 */
	public void accept(ResourceVisitor<T> visitor){
		for(Resource resource : getResources()){// considering use MAP strategy to do this Job.(concurrency programming)
			_resourceAllocator.allocate(resource, visitor);
		}
	}
	/**
	 * 
	 * @return
	 */
	protected Resource[] getResources() {
		Resource[] resources = null; 
		try {
			PathMatchingResourcePatternResolver resourcePatternResolver = 
				new PathMatchingResourcePatternResolver();
		    String packageQuery = resolvePathMatchingResourcePattern();
		    resources = resourcePatternResolver.getResources(packageQuery);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return resources;
	}
	/**
	 * 
	 * @return
	 */
	protected abstract String resolvePathMatchingResourcePattern();
}
