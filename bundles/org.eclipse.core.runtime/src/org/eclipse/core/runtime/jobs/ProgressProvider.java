/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.core.runtime.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * The progress provider supplies the job manager with progress monitors for
 * running jobs.  There can only be one progress provider at any given time.
 * <p>
 * This class is for internal use by the platform-related plug-ins.
 * Clients outside of the base platform should not reference or subclass this class.
 * </p>
 * 
 * @see IJobManager#setProgressProvider
 * @since 3.0
 */
public abstract class ProgressProvider {
	/**
	 * Provides a new progress monitor instance to be used by the given job.
	 * The job might already have a monitor installed, but the progress
	 * provider may decide to return a different one. 
	 * 
	 * @see #createProgressGroup
	 * @see Job#getProgressMonitor
	 * @see Job#setProgressMonitor
	 * @param job the job to create a progress monitor for
	 * @return a progress monitor, or <code>null</code> if no progress monitoring 
	 * is needed.
	 */
	public abstract IProgressMonitor createMonitor(Job job);
	/**
	 * Returns a progress monitor that can be used to provide
	 * aggregated progress feedback on a set of running jobs.
	 * This method implements <code>IJobManager.createProgressGroup</code>,
	 * and must obey all rules specified in that contract.
	 * <p>
	 * This default implementation returns a new
	 * <code>NullProgressMonitor</code>  Subclasses may override.
	 * 
	 * @see IJobManager#createProgressGroup
	 * @return A progress monitor
	 */
	public IProgressMonitor createProgressGroup() {
		return new NullProgressMonitor();
	}
	/**
	 * Returns a progress monitor to use when none has been provided
	 * by the client running the job.  
	 * <p>
	 * This default implementation returns a new
	 * <code>NullProgressMonitor</code>  Subclasses may override.
	 * 
	 * @return a progress monitor
	 */
	public IProgressMonitor getDefaultMonitor() {
		return new NullProgressMonitor();
	}
}