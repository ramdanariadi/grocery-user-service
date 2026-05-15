package id.grocery.tunas.utils;

import java.io.ByteArrayOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class ExportUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportUtil.class);

    public static byte[] exportExel(String filename, String sheetName, String[] header, Object[][] content){

        try(ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(Strings.isNullOrEmpty(sheetName) ? "Sheet1" : sheetName);
            Row row = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                row.createCell(i).setCellValue(header[i]);
            }

            for (int i = 0; i < content.length; i++) {
                Row row1 = sheet.createRow(i + 1);
                for (int i1 = content[i].length - 1; i1 >= 0; i1--) {
                    row1.createCell(i1).setCellValue(null == content[i][i1] ? "" : content[i][i1].toString());
                }
            }
            workbook.write(out);
            return out.toByteArray();
        }catch (Exception e){
            LOGGER.error("creating-workbook-file-error: {}",e.getMessage());
        }
        return new byte[]{};
    }
}
