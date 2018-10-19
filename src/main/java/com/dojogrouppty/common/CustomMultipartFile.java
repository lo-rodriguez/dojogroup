/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lrodriguezn
 */
public class CustomMultipartFile implements MultipartFile {

private final byte[] fileContent;

private final String fileName;

private final File file;

private final String destPath = System.getProperty("LOCATION_PHOTO");

private FileOutputStream fileOutputStream;

public CustomMultipartFile(byte[] fileData, String name) {
    this.fileContent = fileData;
    this.fileName = name;
    file = new File(destPath + fileName);

}

@Override
public void transferTo(File dest) throws IOException, IllegalStateException {
    fileOutputStream = new FileOutputStream(dest);
    fileOutputStream.write(fileContent);
}

public void clearOutStreams() throws IOException {
if (null != fileOutputStream) {
        fileOutputStream.flush();
        fileOutputStream.close();
        file.deleteOnExit();
    }
}

@Override
public byte[] getBytes() throws IOException {
    return fileContent;
}

@Override
public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(fileContent);
}

    @Override
    public String getName() {
      return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
      return !(fileContent==null || fileContent.length <=0); 
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }


}
