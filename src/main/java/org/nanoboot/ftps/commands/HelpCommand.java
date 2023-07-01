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

import org.nanoboot.ftps.Command;
import org.nanoboot.ftps.FtpsArgs;

/**
 *
 * @author pc00289
 */
public class HelpCommand implements Command {

    public HelpCommand() {
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void run(FtpsArgs ftpsArgs) {
        String str = """
    NAME
        ftps - Communication via FTPS protocol.
                           
    SYNOPSIS
        ftps [command] [user[:password]@]host[:port]/[url-path] [options]
        If no password is provided, then application will ask for password via console.
                           
    DESCRIPTION
        Ftps is used for communication with a FTPS server.
                           
    COMMAND
        upload         Uploads a file to the FTPS server.
                        OPTIONS
                            fileName={path to file}
                                Mandatory. No default value.
        help        Display help information
        version     Display version information                           
""";
        System.out.println(str);

    }

}
