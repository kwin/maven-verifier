package org.apache.maven.it;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class Embedded3xLauncherTest
{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    
    private Embedded3xLauncher launcher;
    
    private final String workingDir = Paths.get( "src/test/resources" ).toAbsolutePath().toString();

    @Test
    public void testWithClasspath() throws Exception
    {
        launcher = Embedded3xLauncher.createFromClasspath();
        File logFile = temporaryFolder.newFile( "build.log" );

        int exitCode = launcher.run( new String[]{ "clean" }, new Properties(), workingDir, logFile );

        assertThat( "exit code unexpected, build log: " + System.lineSeparator() +
                String.join(System.lineSeparator(), Files.readAllLines( logFile.toPath() ) ), exitCode, is ( 0 ) );
    }

}