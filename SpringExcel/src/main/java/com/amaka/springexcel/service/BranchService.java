package com.amaka.springexcel.service;

import com.amaka.springexcel.data.Branch;
import com.amaka.springexcel.repository.BranchRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> saveBranch(String name) throws IOException {
        List<Branch> branchList = new ArrayList<>();
        //Reading file from local directory
        FileInputStream file = new FileInputStream(name);
        //create workbook instance holding reference to .xls file
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);


        int rowNum = sheet.getLastRowNum();
        for (int i = 1; i <= rowNum; i++) {
            Branch br = new Branch();

            br.setName(getCellDAta(sheet, "NAME", i));
            br.setCode(getCellDAta(sheet, "CODE", i).replaceAll(".0", ""));
            br.setCity(getCellDAta(sheet, "CITY", i));
            br.setAddress(getCellDAta(sheet, "ADDRESS", i));
            br.setEmail(getCellDAta(sheet, "ADDRESS", i));

            String head_Office = getCellDAta(sheet, "HEAD_OFFICE", i);
            br.setHeadOffice(head_Office.equals("1"));

            String reg_Office = getCellDAta(sheet, "REGIONAL_OFFICE", i);
            br.setRegionFg(reg_Office.equals("1"));

            br.setPhoneNumber(getCellDAta(sheet, "PHONE NUMBER", i));
            br.setSwiftCode(getCellDAta(sheet, "SWIFT_BICC", i));

            Branch createdBranch = branchRepository.save(br);
            branchList.add(createdBranch);
        }
        return branchList;
    }

    private static String getCellDAta(Sheet sheet, String columnName, int rowNumber) {
        Row row = sheet.getRow(0);
        Cell cell;
        int lastCellNumber = row.getLastCellNum();
        int colNum = -1;
        for (int i = 0; i < lastCellNumber; i++) {
            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName)) {
                colNum = i;
            }
        }
        row = sheet.getRow(rowNumber);
        cell = row.getCell(colNum);
        return cell != null ? getCellStringValue(cell) : "";
    }

    private static String getCellStringValue(Cell cell) {
        String val;
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    val = String.valueOf(cell.getDateCellValue());
                } else {
                    val = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            case STRING:
                val = String.valueOf(cell.getStringCellValue());
                break;
            default:
                val = "";
                break;
        }
        return val;
    }
}
