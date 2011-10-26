/*******************************************************************************
 * Copyright (c) 2010, 2011 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sonatype, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.aether.demo;

import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.collection.CollectResult;
import org.eclipse.aether.demo.util.Booter;
import org.eclipse.aether.demo.util.ConsoleDependencyGraphDumper;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.util.artifact.DefaultArtifact;


/**
 * Collects the transitive dependencies of an artifact.
 */
public class GetDependencyTree
{

    public static void main( String[] args )
        throws Exception
    {
        System.out.println( "------------------------------------------------------------" );
        System.out.println( GetDependencyTree.class.getSimpleName() );

        RepositorySystem system = Booter.newRepositorySystem();

        RepositorySystemSession session = Booter.newRepositorySystemSession( system );

        Artifact artifact = new DefaultArtifact( "org.apache.maven:maven-aether-provider:3.0.2" );

        RemoteRepository repo = Booter.newCentralRepository();

        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setRoot( new Dependency( artifact, "" ) );
        collectRequest.addRepository( repo );

        CollectResult collectResult = system.collectDependencies( session, collectRequest );

        collectResult.getRoot().accept( new ConsoleDependencyGraphDumper() );
    }

}
