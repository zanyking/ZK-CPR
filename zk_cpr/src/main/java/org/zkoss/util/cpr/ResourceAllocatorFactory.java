/**
 * 
 */
package org.zkoss.util.cpr;

import java.io.IOException;
import java.io.InputStream;

import org.zkoss.spring.core.io.Resource;

/**
 * @author Ian YT Tsai(zanyking)
 *
 */
public class ResourceAllocatorFactory {

	public static final ResourceAllocator<Resource> RESOURCE_ALLOCATOR = 
		new ResourceAllocator<Resource>() {
		public void allocate(Resource resource, ResourceVisitor<Resource> visitor) {
			visitor.visit(resource);
		}
	};
	
	
	
	public static final ResourceAllocator<InputStream> INPUTSTREAM_ALLOCATOR = 
		new ResourceAllocator<InputStream>() {
		public void allocate(Resource resource, ResourceVisitor<InputStream> visitor) {
			try {
				visitor.visit(resource.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	};
	
	
	
	/**
	 * 
	 * @param <T>
	 * @param clz
	 * @return
	 */
	public static <T> ResourceAllocator<T> getInstance(Class<T> clz){
		
		if(clz.equals(Resource.class)){
			return (ResourceAllocator<T>) RESOURCE_ALLOCATOR;
		}
		if(clz.equals(InputStream.class)){
			return (ResourceAllocator<T>) INPUTSTREAM_ALLOCATOR;
		}
		return null;
	}
	
	
	
}
