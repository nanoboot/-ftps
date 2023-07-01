# ftps

Ftps is a tool to communicate with a SFTP server

Following patch may be needed in future to be applied: https://eng.wealthfront.com/2016/06/10/connecting-to-an-ftps-server-with-ssl-session-reuse-in-java-7-and-8/

## Usage

You run: ftps user:password@host:port/workindDirectory upload fileName

## How to setup your environment on Linux

Example: 

    alias ftps='java -jar /rv/data/desktop/code/code.nanoboot.org/nanoboot/ftps/target/ftps-0.0.0-SNAPSHOT-jar-with-dependencies.jar'

