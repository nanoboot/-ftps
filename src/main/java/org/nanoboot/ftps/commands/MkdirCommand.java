///////////////////////////////////////////////////////////////////////////////////////////////
// ftps: Tool generating documentation.
// Copyright (C) 2023-2023 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.ftps.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;
import org.nanoboot.ftps.Command;
import org.nanoboot.ftps.FtpsArgs;
import org.nanoboot.ftps.FtpsCredentials;

/**
 *
 * @author pc00289
 */
public class MkdirCommand implements Command {

    public MkdirCommand() {
    }

    @Override
    public String getName() {
        return "mkdir";
    }

    @Override
    public void run(FtpsArgs ftpsArgs) {
        System.out.println("mkdir");
        if(ftpsArgs.getArgs().size() < 2) {
            throw new RuntimeException("Upload argument expects credentials and a directory name as the arguments.");
        }
        FtpsCredentials config = new FtpsCredentials(ftpsArgs.getArgs().get(0));
        String dirName = ftpsArgs.getArgs().get(1);
        
        System.out.println("Going to create directory: " + dirName);
        FTPSClient ftps = new FTPSClient();
        try {
            ftps.connect(config.getHost(), config.getPort());
            ftps.login(config.getUser(), config.getPassword());
            boolean changeDirResult = ftps.changeWorkingDirectory(config.getWorkingDir());
            if(!changeDirResult) {
                throw new RuntimeException("Changing directory failed: " + config.getWorkingDir());
            }
            
            ftps.enterLocalPassiveMode();
            ftps.setFileType(FTP.BINARY_FILE_TYPE);
            boolean mkdirWasSuccessful=ftps.makeDirectory(dirName);
            
            System.out.println("Creating directory was " + (mkdirWasSuccessful ? "successful" : "unsuccessful"));
            
            ftps.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }

    }
    
}
