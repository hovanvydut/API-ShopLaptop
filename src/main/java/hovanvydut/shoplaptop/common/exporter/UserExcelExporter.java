package hovanvydut.shoplaptop.common.exporter;

import hovanvydut.shoplaptop.model.Role;
import hovanvydut.shoplaptop.model.User;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 5/28/21
 */

public class UserExcelExporter extends AbstractExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public UserExcelExporter() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        this.sheet = this.workbook.createSheet("Users");
        XSSFRow row = this.sheet.createRow(0);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "User ID", cellStyle);
        createCell(row, 1, "Email", cellStyle);
        createCell(row, 2, "First Name", cellStyle);
        createCell(row, 3, "Last Name", cellStyle);
        createCell(row, 4, "Roles", cellStyle);
        createCell(row, 5, "Enable", cellStyle);
    }

    public void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {
        XSSFCell cell = row.createCell(columnIndex);

        if (value instanceof Integer)
            cell.setCellValue((Integer) value);
        else if (value instanceof Boolean)
            cell.setCellValue((Boolean) value);
        else
            cell.setCellValue((String) value);

        cell.setCellStyle(style);
    }

    public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/octet-stream", ".xlsx");

        writeHeaderLine();
        writeDataLines(listUsers);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeDataLines(List<User> listUsers) {
        int rowIndex = 1;

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);

        for (User user : listUsers) {
            XSSFRow row = this.sheet.createRow(rowIndex++);
            int columnIdx = 0;

            createCell(row, columnIdx++, user.getId(), cellStyle);
            createCell(row, columnIdx++, user.getEmail(), cellStyle);
            createCell(row, columnIdx++, user.getFirstName(), cellStyle);
            createCell(row, columnIdx++, user.getLastName(), cellStyle);

            StringBuffer str = new StringBuffer();
            for (Role role : user.getRoles()) {
                str.append(role.getName());
                str.append(", ");
            }
            if (str.length() > 0)
                str.replace(str.length() - 2, str.length(), "");
            createCell(row, columnIdx++, str.toString(), cellStyle);

            createCell(row, columnIdx, user.isEnabled(), cellStyle);
        }
    }
}
