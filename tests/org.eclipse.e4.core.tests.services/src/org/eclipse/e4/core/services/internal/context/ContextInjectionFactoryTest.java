/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.e4.core.services.internal.context;

import junit.framework.TestCase;
import org.eclipse.e4.core.services.context.EclipseContextFactory;
import org.eclipse.e4.core.services.context.IEclipseContext;
import org.eclipse.e4.core.services.context.spi.ContextInjectionFactory;

public class ContextInjectionFactoryTest extends TestCase {

	private TestObject testObject;

	private IEclipseContext context;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		testObject = new TestObject();
		context = EclipseContextFactory.create();
	}

	public void testInvoke() throws Exception {
		ContextInjectionFactory.invoke(testObject, "execute", context, null);

		assertEquals(1, testObject.getExecuted());
		assertEquals(0, testObject.getExecutedWithParams());
	}

	public void testInvokeWithParameters() throws Exception {
		context.set("java.lang.String", "");

		ContextInjectionFactory.invoke(testObject, "executeWithParams", context, null);

		assertEquals(0, testObject.getExecuted());
		assertEquals(1, testObject.getExecutedWithParams());
	}

	static class TestObject {

		private int executed = 0;

		private int executedWithParams = 0;

		public void execute() {
			executed++;
		}

		public void executeWithParams(String string) {
			executedWithParams++;
		}

		public int getExecuted() {
			return executed;
		}

		public int getExecutedWithParams() {
			return executedWithParams;
		}

	}

}