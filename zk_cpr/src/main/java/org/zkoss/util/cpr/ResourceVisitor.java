/**
 * 
 */
package org.zkoss.util.cpr;


/**
 * @author Ian YT Tsai(zanyking)
 *
 */
public interface ResourceVisitor<R> {

	/**
	 * 
	 * @param resource
	 */
	public void visit(R resource);
	
}
