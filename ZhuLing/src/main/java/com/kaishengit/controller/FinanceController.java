package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/23.
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;

    /**
     * 显示列表
     * @return
     */
    @RequestMapping("/day")
    public String dayreport(){
        return "finance/report/day";
    }
    @RequestMapping("/day/load")
    @ResponseBody
    public DataTablesResult dayLoad(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String day = request.getParameter("day");

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);
        queryParam.put("day",day);

        List<Finance> financeList = financeService.findByQueryParam(queryParam);
        Long count = financeService.count();
        Long filteredCount = financeService.countByQueryParam(queryParam);

        return new DataTablesResult(draw,count,filteredCount,financeList);

    }
    /**
     * 财务确认
     */
    @PostMapping("/confirm/{id:\\d+}")
    @ResponseBody
    public JsonResult confirmFinance(@PathVariable Integer id) {
        financeService.confirmById(id);
        return new JsonResult(JsonResult.SUCCESS);
    }
    /**
     * 将数据导出为Excel文件
     */
    @GetMapping("/day/{date}/data.xls")
    public void exportExcel(@PathVariable String date, HttpServletResponse response) throws IOException {
        List<Finance> financeList = financeService.findByCreatedate(date);
        //1，创建工作表
        Workbook workbook = new HSSFWorkbook();
        //2,创建标签页
        Sheet sheet = workbook.createSheet("2017-02-23财务流水");
            //单元格样式（可选）
            /*CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.*/
        //3,创建行，从0开始
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("业务流水号");
        row.createCell(1).setCellValue("创建日期");
        row.createCell(2).setCellValue("类型");
        row.createCell(3).setCellValue("金额");
        row.createCell(4).setCellValue("业务模块");
        row.createCell(5).setCellValue("业务流水号");
        row.createCell(6).setCellValue("状态");
        row.createCell(7).setCellValue("备注");
        row.createCell(8).setCellValue("创建人");
        row.createCell(9).setCellValue("确认人");
        row.createCell(10).setCellValue("确认日期");

        for(int i = 0;i < financeList.size();i++) {
            Finance finance = financeList.get(i);

            Row dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(finance.getSerialnumber());
            dataRow.createCell(1).setCellValue(finance.getCreatedate());
            dataRow.createCell(2).setCellValue(finance.getType());
            dataRow.createCell(3).setCellValue(finance.getMoney());
            dataRow.createCell(4).setCellValue(finance.getModule());
            dataRow.createCell(5).setCellValue(finance.getModuleserialnumber());
            dataRow.createCell(6).setCellValue(finance.getState());
            dataRow.createCell(7).setCellValue(finance.getMark());
            dataRow.createCell(8).setCellValue(finance.getCreateuser());
            dataRow.createCell(9).setCellValue(finance.getConfirmuser());
            dataRow.createCell(10).setCellValue(finance.getConfirmdate());
        }

        //自动改变对应列格子的大小
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(10);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename=\""+date+".xls\"");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
