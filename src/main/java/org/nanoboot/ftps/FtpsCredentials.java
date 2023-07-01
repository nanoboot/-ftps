/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.nanoboot.ftps;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.Data;

/**
 *
 * @author robertvokac
 */
@Data
public class FtpsCredentials {

    private String host;
    private int port;
    private String user;
    private String password;
    private final String workingDir;

    public FtpsCredentials(String credentialsAsString) {
        String[] array = credentialsAsString.split("@");
        String[] array1 = array[0].split(":");
        String[] array2 = array[1].split(":");
        String[] array20 = array2[1].split("/");
        this.user = array1[0];
        this.password = array1.length > 1 ? array1[1] : "";
        if (this.password.equals("")) {

            Console console = System.console();
            if (console == null) {
                System.out.println("Couldn't get Console instance");
                System.exit(0);
            }
            char[] passwordArray = console.readPassword("Please, type the password: ");
            for (int i = 0; i < passwordArray.length; i++) {
                System.out.print("*");
            }

            this.password = new String(passwordArray);

            System.out.print("\nPassword was entered.");
        }

        this.host = array2[0];
        this.port = Integer.valueOf(array20[0]);
        this.workingDir = array20.length < 2 ? "/" : array20[1];
    }

}
