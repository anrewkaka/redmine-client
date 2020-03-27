package xyz.lannt.redmine.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import xyz.lannt.client.BittrexMarketClient;
import xyz.lannt.client.MarketClientSetting;
import xyz.lannt.client.request.issue.Issue;
import xyz.lannt.redmine.converter.TeecoinConverterSetting;
import xyz.lannt.redmine.service.ExcelService;

@RestController
@RequestMapping(path = "/redmine")
public class ClientController {

  @Autowired
  private ExcelService fileService;

  @GetMapping
  public ResponseEntity<?> get() {
    MarketClientSetting setting = new MarketClientSetting("https://sv-01.rikkeisys.com/redmine",
        "Basic bGFubnQ6QWJjMTIzNDVAcms=");
    BittrexMarketClient client = new BittrexMarketClient(setting);
    return ResponseEntity.ok().body(client.get("issues.json", Issue.builder().projectId(4).build()));
  }

  @PostMapping
  public ResponseEntity<?> regist(MultipartFile file) throws IOException {
    String path = this.fileService.upload(file);
    this.fileService.read(path, new TeecoinConverterSetting());
    return ResponseEntity.ok("File: " + file.getOriginalFilename() + " has been uploaded successfully!");
  }
}
