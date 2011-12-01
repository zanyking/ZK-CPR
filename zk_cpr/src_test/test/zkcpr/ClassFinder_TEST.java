/* ClassFinder_TEST.java

	Purpose:
		
	Description:
		
	History:
		Dec 1, 2011, Created by Ian Tsai(Zanyking)

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under ZOL in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package test.zkcpr;


import java.awt.Component;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.zkoss.spring.core.io.Resource;
import org.zkoss.spring.util.ClassUtils;
import org.zkoss.util.cpr.ClassFinder;
import org.zkoss.util.cpr.ResourceAllocator;
import org.zkoss.util.cpr.ResourceVisitor;

import test.zkcpr.testdata.correct.TestInterface;

/**
 * @author Ian Y.T Tsai(zanyking)
 *
 */
public class ClassFinder_TEST {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testClassFinder(){
		ClassFinder<Class<? extends TestInterface>> finder = 
			new ClassFinder<Class<? extends TestInterface>>("test.zkcpr.testdata.correct", 
					new ResourceAllocator<Class<? extends TestInterface>>(){
						public void allocate(
								Resource resource,
								ResourceVisitor<Class<? extends TestInterface>> visitor) {
							
							try {
								InputStream in = resource.getInputStream();
								try {
									ClassReader reader = new ClassReader(in);
									ClassNode clzNode = new ClassNode();
									reader.accept(clzNode, ClassReader.SKIP_DEBUG);
									
									String fqcn = ClassUtils.convertResourcePathToClassName(clzNode.name);
									Class<?> clz = (Class<?>) Class.forName(fqcn);
									try{
										visitor.visit(clz.asSubclass(TestInterface.class));	
									}catch(ClassCastException e){
										//do nothing, this is not a TestInterface...
									}
									
								}finally{
									if(in!=null)in.close();
								}
							} catch (Exception e1) {
								throw new RuntimeException(e1);
							}
							
							
						}}, true);
		
		finder.accept(new ResourceVisitor<Class<? extends TestInterface>>() {
			public void visit(Class<? extends TestInterface> clz) {
				System.out.println(" Class: "+ clz.getCanonicalName());
				
			}
		});
		System.out.println("done..." );
		
	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
