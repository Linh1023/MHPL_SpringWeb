/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.service.ChartResponse;
import Springweb.service.ThietBiService;
import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.repository.ThongTinSDRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *
 * @author quang
 */
@Controller
public class ThongKeController {

    @Autowired
    private ThietBiService thietbiService;
    @Autowired
    private ThongTinSDRepository thongTinSDRepository;

    @GetMapping("/admin/thongke/thietbi_dm")
    private String CreateDataChartTB_DM(Model m) {
        List<ThongTinSD> list = (List<ThongTinSD>) thongTinSDRepository.findAll();
        List<ThongTinSD> listNew = new ArrayList<>();
        for (ThongTinSD ttsd : list) {
            if (ttsd.gettGMuon()!= null && ttsd.gettGTra() == null) {
                listNew.add(ttsd);
            }
        }
        //lấy Dữ Liệu
        List<Integer> maTBs = new ArrayList<>();
        // Tạo list ma
        for (ThongTinSD ttsd : listNew) {
            maTBs.add(ttsd.getMaTB());
        }
        //tạo list số lượng
        List<Integer> counts = calculateDeviceCounts(maTBs);
        m.addAttribute("dataChart", CreateChartResponseTB(counts));
        m.addAttribute("templateName", "admin/thongke/chart");
        return "admin/sample";
    }
    @GetMapping("/admin/thongke/thietbi_dam")
    private String CreateDataChartTB_DaM(Model m) {
        List<ThongTinSD> list = (List<ThongTinSD>) thongTinSDRepository.findAll();
        List<ThongTinSD> listNew = new ArrayList<>();
        for (ThongTinSD ttsd : list) {
            if (ttsd.gettGMuon() != null) {
                listNew.add(ttsd);
            }
        }
        List<Integer> maTBs = new ArrayList<>();
        // Tạo list ma
        for (ThongTinSD ttsd : listNew) {
            maTBs.add(ttsd.getMaTB());
        }
        //tạo list số lượng
        List<Integer> counts = calculateDeviceCounts(maTBs);
        m.addAttribute("dataChart", CreateChartResponseTB(counts));
        m.addAttribute("templateName", "admin/thongke/chart");
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
}
