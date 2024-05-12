/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.service.ChartResponse;
import Springweb.service.ThietBiService;
import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.entity.XuLy;
import Springweb.service.ThongKeService;
import Springweb.service.ThongTinSDService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author quang
 */
@Controller
public class ThongKeController {

    @Autowired
    private ThietBiService thietbiService;
    
    @Autowired
    private ThongKeService thongKeService;
    
    @Autowired
    private ThongTinSDService thongTinSDService;


    @GetMapping("/admin/thongke/thietbi_dm")
    private String CreateDataChartTB_DM(Model m,
            @RequestParam(name = "datebegin", required = false) String dateBeginString,
            @RequestParam(name = "dateend", required = false) String dateEndString
    ) throws ParseException {

        List<ThongTinSD> list = (List<ThongTinSD>) thongTinSDService.findAll();
        List<ThongTinSD> listNew = new ArrayList<>();

        if (dateEndString != null && dateBeginString != null) {
            if (dateEndString != "" && dateBeginString != "") {

                //Format thời gian
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

                Date dateBegin = outputFormat.parse(outputFormat.format(inputFormat.parse(dateBeginString)));
                Date dateEnd = outputFormat.parse(outputFormat.format(inputFormat.parse(dateEndString)));

                for (ThongTinSD ttsd : list) {
                    Date tgm = ttsd.gettGMuon();
                    if (tgm != null) {
                        if (tgm.compareTo(dateBegin) > 0 && tgm.compareTo(dateEnd) < 0) {
                            listNew.add(ttsd);
                        }
                    }
                }
            }
        } else {
            for (ThongTinSD ttsd : list) {
                listNew.add(ttsd);
            }
        }

        //lấy Dữ Liệu
        List<Integer> maTBs = new ArrayList<>();
        // Tạo list ma
        for (ThongTinSD ttsd : listNew) {
            if (ttsd.getMaTB() != null) {
                maTBs.add(ttsd.getMaTB());
            }
        }
        //tạo list số lượng
        List<Integer> counts = calculateDeviceCounts(maTBs);
        m.addAttribute("dataChart", CreateChartResponseTB(counts));
        m.addAttribute("templateName", "admin/thongke/chart");
        m.addAttribute("datebegin", dateBeginString);
        m.addAttribute("dateend", dateEndString);
        m.addAttribute("showform", true);
        return "admin/sample";
    }

    @GetMapping("/admin/thongke/thietbi_dam")
    private String CreateDataChartTB_DaM(Model m, @RequestParam(name = "datebegin", required = false) String dateBeginString,
            @RequestParam(name = "dateend", required = false) String dateEndString
    ) throws ParseException {
        List<ThongTinSD> list = (List<ThongTinSD>) thongTinSDService.findAll();
        List<ThongTinSD> listNew = new ArrayList<>();

        if (dateEndString != null && dateBeginString != null) {
            if (dateEndString != "" && dateBeginString != "") {

                //Format thời gian
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

                Date dateBegin = outputFormat.parse(outputFormat.format(inputFormat.parse(dateBeginString)));
                Date dateEnd = outputFormat.parse(outputFormat.format(inputFormat.parse(dateEndString)));
                for (ThongTinSD ttsd : list) {
                    Date tgm = ttsd.gettGMuon();
                    if (tgm != null) {
                        if (tgm.compareTo(dateBegin) > 0 && tgm.compareTo(dateEnd) < 0) {
                            listNew.add(ttsd);
                        }
                    }
                }
            }
        } else {
            for (ThongTinSD ttsd : list) {
                if (ttsd.gettGMuon() != null) {
                    listNew.add(ttsd);
                }
            }
        }
        List<Integer> maTBs = new ArrayList<>();
        // Tạo list ma
        for (ThongTinSD ttsd : listNew) {
            if (ttsd.getMaTB() != null) {
                maTBs.add(ttsd.getMaTB());
            }
        }
        //tạo list số lượng
        List<Integer> counts = calculateDeviceCounts(maTBs);
        m.addAttribute("dataChart", CreateChartResponseTB(counts));
        m.addAttribute("templateName", "admin/thongke/chart");
        m.addAttribute("datebegin", dateBeginString);
        m.addAttribute("dateend", dateEndString);
        m.addAttribute("showform", true);
        return "admin/sample";
    }

    @GetMapping("/admin/thongke/thietbi")
    private String CreateDataChartTBAll(Model m) {
        //lấy Dữ Liệu
        List<ThietBi> list = thietbiService.findAll();
        List<Integer> maTBs = new ArrayList<>();
        // Tạo list ma
        for (ThietBi tb : list) {
            maTBs.add(tb.getMaTB());
        }
        //tạo list số lượng
        List<Integer> counts = calculateDeviceCounts(maTBs);
        m.addAttribute("dataChart", CreateChartResponseTB(counts));
        m.addAttribute("templateName", "admin/thongke/chart");
        m.addAttribute("showform", false);
        return "admin/sample";
    }

    @GetMapping("/admin/thongke/thanhvien")
    private String CreateDataChartTV(Model m,
            @RequestParam(name = "datebegin", required = false) String dateBeginString,
            @RequestParam(name = "dateend", required = false) String dateEndString) throws ParseException {
        List<Object[]> thanhVienTheoTG = null;
        if (dateEndString != null && dateBeginString != null && dateEndString != "" && dateBeginString != "") {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            thanhVienTheoTG = thongKeService.countThanhVienTheoThoiGianLoc(inputFormat.parse(dateBeginString), inputFormat.parse(dateEndString));
        } else {
            thanhVienTheoTG = thongKeService.countThanhVienTheoTG();
        }
        List<Integer> counts = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        for (Object[] tt : thanhVienTheoTG) {
            Long sl = (Long) tt[0];
            counts.add(sl.intValue());
            Date date = (Date) tt[1];
            dates.add(date.toString());
        }

        List<Integer> counts1 = new ArrayList<>();
        List<String> labels1 = new ArrayList<>();
        List<Object[]> thanhVienTheoKhoa = thongKeService.countThanhVienTheoKhoa();
        for (Object[] tt : thanhVienTheoKhoa) {
            Long sl = (Long) tt[0];
            String label = (String) tt[1];
            counts1.add(sl.intValue());
            labels1.add(label);

        }

        List<Integer> counts2 = new ArrayList<>();
        List<String> labels2 = new ArrayList<>();
        List<Object[]> thanhVienTheoNganh = thongKeService.countThanhVienTheoNganh();
        for (Object[] tt : thanhVienTheoNganh) {
            Long sl = (Long) tt[0];
            String label = (String) tt[1];
            counts2.add(sl.intValue());
            labels2.add(label);
        }

        m.addAttribute("templateName", "admin/thongke/charttv");
        m.addAttribute("dataChart", createChartResponseLabels(counts, dates));
        m.addAttribute("dataChart1", createChartResponsePie(counts1, labels1, "Số lượng thành viên"));
        m.addAttribute("dataChart2", createChartResponsePie(counts2, labels2, "Số lượng thành viên"));
        m.addAttribute("datebegin", dateBeginString);
        m.addAttribute("dateend", dateEndString);
        m.addAttribute("showform", true);

        return "admin/sample";
    }

    @GetMapping("/admin/thongke/vipham")
    private String createDataChartVP(Model m) {

        List<Object[]> ttxl = thongKeService.countByGroupByTrangThaiXL();
        List<Integer> counts = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (Object[] tt : ttxl) {
            Long sl = (Long) tt[0];
            int trangthai = (int) tt[1];
            String label;
            if (trangthai == 1) {
                label = "Đã được xử lý";
            } else {
                label = "Đang được xử lý";
            }
            System.out.println(sl + "-" + trangthai);
            counts.add(sl.intValue());
            labels.add(label);
        }

        List<XuLy> list = thongKeService.findByTrangThaiXL();
        long sotien = thongKeService.tinhTongTien(list);
        
        String labelTongtien = "Tổng tiền: "  + sotien+"đ";

        m.addAttribute("templateName", "admin/thongke/chartvp");
        m.addAttribute("dataChart2", createChartResponsePie(counts, labels, "Số lượng trường hợp"));
        if (sotien != 0) {
            m.addAttribute("list", list);  
            m.addAttribute("tongtien",labelTongtien);
        }
        return "admin/sample";
    }

    private List<Integer> calculateDeviceCounts(List<Integer> maTBs) {
        Integer[] counts = new Integer[7];
        List<Integer> deviceCounts = new ArrayList<>();

        //Khởi tạo giá trị ban đầu bằng 0
        for (int i = 0; i < 6; i++) {
            counts[i] = 0;
            deviceCounts.add(counts[i]);
        }

        for (Integer maTB : maTBs) {
            int firstDigit = 0;
            while (maTB != 0) {
                firstDigit = maTB % 10;
                maTB = maTB / 10;
            }
            counts[firstDigit - 1]++;
        }
        return Arrays.asList(counts);
    }

// Phương thức tạo và trả về ChartResponse với dữ liệu đã tính toán
    public ChartResponse CreateChartResponseTB(List<Integer> Counts) {
        ChartResponse chartResponse = new ChartResponse();

        // Thêm labels
        chartResponse.labels = List.of(
                "Micro",
                "Máy chiếu",
                "Máy ảnh",
                "Cassette",
                "Tivi",
                "Quạt Đứng"
        );

        // Thêm datasets
        ChartResponse.DataSet dataSet = new ChartResponse.DataSet();
        dataSet.label = "Số Lượng Thiết Bị";

        dataSet.data = Counts;

        dataSet.backgroundColor = List.of(
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)"
        );
        dataSet.borderColor = List.of(
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)"
        );
        dataSet.borderWidth = 1;

        chartResponse.datasets = List.of(dataSet);

        return chartResponse;
    }

    public ChartResponse createChartResponseLabels(List<Integer> counts, List<String> labels) {
        ChartResponse chartResponse = new ChartResponse();
        chartResponse.labels = labels;
        ChartResponse.DataSet dataSet = new ChartResponse.DataSet();
        dataSet.data = counts;
        chartResponse.datasets = List.of(dataSet);

        dataSet.borderWidth = 1;

        dataSet.label = "Số lượng thành viên";
        return chartResponse;
    }

    public ChartResponse createChartResponsePie(List<Integer> counts, List<String> labels, String label) {
        ChartResponse chartResponse = new ChartResponse();
        chartResponse.labels = labels;
        ChartResponse.DataSet dataSet = new ChartResponse.DataSet();
        dataSet.data = counts;
        dataSet.hoverOffset = 4;
        dataSet.label = label;
        chartResponse.datasets = List.of(dataSet);
        return chartResponse;

    }
}
