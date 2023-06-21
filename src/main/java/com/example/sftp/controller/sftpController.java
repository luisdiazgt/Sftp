package com.example.sftp.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class SftpController {

    private String remoteHost = "dev.sftp.com";
    private String username = "user";
    private String password = "passw";
    private String localFile = "C:/Temp/temp.txt";
    private String remoteDir = "/ToTU/AWS/";
    private String remoteName = "temp2.txt";
    private int port = 22;

    @GetMapping("/subirArchivo")
    public String getSubirArchivo() throws IOException, URISyntaxException{

        FileSystemManager manager = VFS.getManager();
 
        FileObject local = manager.resolveFile(localFile);

        String userInfo = username + ":" + password;
        String remoteFile = remoteDir + remoteName;

        URI uri = new URI("sftp", userInfo, remoteHost, port, remoteFile, null, null);

        FileObject remote = manager.resolveFile(uri);
    
        remote.copyFrom(local, Selectors.SELECT_SELF);
    
        local.close();
        remote.close();
        
        return "Éxito";

    }

}
