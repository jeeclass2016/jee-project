package com.vietsci.cms.frontend.etopup.common.util;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EtopupFileUtil {

  public static String removeExtension(String fileName) {
    if(fileName == null || fileName.trim().length() == 0) return null;

    int index = fileName.lastIndexOf(".");
    return fileName.substring(0, index);
  }

  /**
   * Read a file, line by line
   * @param uploadedFile
   * @return content of file, an element is a line
   * @throws IOException
   *
   * @author hong.nguyenmai
   */
  public static List<String> readFile(UploadedFile uploadedFile) throws IOException {
    InputStream is = null;
    BufferedReader reader = null;

    String line = "";
    List<String> content = new ArrayList();

    is = uploadedFile.getInputstream();
    if(is == null) return content;

    reader = new BufferedReader(new InputStreamReader(is));

    while((line = reader.readLine()) != null) {
      if(StringUtils.isBlank(line)) {
        continue;
      }
      if ( line.startsWith("/*") || line.endsWith("*/") ) {
        continue;
      }
      content.add(line.trim());
    }
    return content;
  }

  public static boolean isTxtFile(String fileName) {
    if(fileName.endsWith(Constants.FileManagement.TXT_EXTENSION)) {
      return true;
    }

    return false;
  }
}
