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
public class UploadCommand implements Command {

    public UploadCommand() {
    }

    @Override
    public String getName() {
        return "upload";
    }

    @Override
    public void run(FtpsArgs ftpsArgs) {
        System.out.println("upload");
        if(ftpsArgs.getArgs().size() < 2) {
            throw new RuntimeException("Upload argument expects credentials and afile name as the arguments.");
        }
        FtpsCredentials config = new FtpsCredentials(ftpsArgs.getArgs().get(0));
        String fileName = ftpsArgs.getArgs().get(1);
        File file = new File(fileName);
        FTPSClient ftps = new FTPSClient();
        try {
            ftps.connect(config.getHost(), config.getPort());
            ftps.login(config.getUser(), config.getPassword());
            ftps.changeWorkingDirectory(config.getWorkingDir());
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ftps.enterLocalPassiveMode();
            ftps.setFileType(FTP.BINARY_FILE_TYPE);
            boolean uploadWasSuccessful=ftps.storeFile(file.getName(), fileInputStream);
            fileInputStream.close();
            //System.out.println(config.toString());
            System.out.println("Upload was " + (uploadWasSuccessful ? "successful" : "unsuccessful"));
            
            ftps.disconnect();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
}
