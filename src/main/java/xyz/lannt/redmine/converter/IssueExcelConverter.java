package xyz.lannt.redmine.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.AllArgsConstructor;
import xyz.lannt.client.request.issue.Issue;
import xyz.lannt.redmine.domain.model.Issues;

@AllArgsConstructor
public class IssueExcelConverter {

  private ConverterSetting setting;

  public Issues convert(String fileLocation) {
    this.readExcel(fileLocation);
    return null;
  }

  private void readExcel(String fileLocation) {
    try {
      FileInputStream excelFile = new FileInputStream(new File(fileLocation));
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      Iterator<Row> iterator = datatypeSheet.iterator();
      Issues issues = new Issues();
      while (iterator.hasNext()) {
        issues.add(rowToIssue(iterator.next(), setting));
      }

      workbook.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Issue rowToIssue(Row row, ConverterSetting setting) {
    Issue issue = Issue.builder().build();
    Iterator<Cell> cellIterator = row.iterator();
    String title = "";
    while (cellIterator.hasNext()) {
      Cell currentCell = cellIterator.next();
      int colIndex = currentCell.getAddress().getColumn();
      if (colIndex < setting.getTitleCols()) {
        title += "【" + currentCell.getStringCellValue() + "】";
      }

      if (colIndex == setting.getTitleCols()) {
        title += "【" + currentCell.getStringCellValue() + "】";
        issue.setSubject(title);
      }

      if (colIndex == setting.getDesCols()) {
        issue.setDescription(currentCell.getStringCellValue());
      }

      if (colIndex == setting.getAssignCols()) {
        issue.setAssignedToId(Integer.getInteger(currentCell.getStringCellValue()));
      }

      if (colIndex == setting.getStartCols()) {

      }
    }
    return issue;
  }
}
