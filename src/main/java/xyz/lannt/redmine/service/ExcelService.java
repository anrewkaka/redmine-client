package xyz.lannt.redmine.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import xyz.lannt.redmine.converter.ConverterSetting;
import xyz.lannt.redmine.converter.IssueExcelConverter;
import xyz.lannt.redmine.domain.model.Issues;

@Service
public class ExcelService {

  public String upload(MultipartFile file) throws IOException {
    InputStream in = file.getInputStream();
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
    FileOutputStream f = new FileOutputStream(fileLocation);
    int ch = 0;
    while ((ch = in.read()) != -1) {
      f.write(ch);
    }
    f.flush();
    f.close();

    return fileLocation;
  }

  public Issues read(String fileLocation, ConverterSetting converterSetting) {
    return new IssueExcelConverter(converterSetting).convert(fileLocation);
  }

}
