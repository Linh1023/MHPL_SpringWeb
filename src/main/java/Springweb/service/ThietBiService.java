package Springweb.service;

import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.repository.ThietBiRepository;

import Springweb.repository.ThongTinSDRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


@Service
public class ThietBiService {



    @Autowired
    private ThietBiRepository thietBiRepository;
    @Autowired
    private ThongTinSDRepository thongTinSDRepository;

    @Autowired
    public ThietBiService(ThietBiRepository thietBiRepository) {
        this.thietBiRepository = thietBiRepository;
    }

    public ThietBi findById(Integer id) {
        Optional<ThietBi> thietBiOptional = thietBiRepository.findById(id);
        if (thietBiOptional.isPresent()) {
            return thietBiOptional.get();
        } else {
            throw new RuntimeException("Không tìm thấy ThietBi với ID: " + id);
        }
    }

    public List<ThietBi> searchThietBi(int category, String keyword) {
        switch (category) {
            case 1:
                return thietBiRepository.findByTenTBContaining(keyword);
            case 2:
                return thietBiRepository.findByMaTBEquals(Integer.parseInt(keyword));
            case 3:
                return thietBiRepository.findByMoTaTBContaining(keyword);
            default:
                throw new IllegalArgumentException("Category không hợp lệ: " + category);
        }
    }

 public List<ThietBi> findAll() {
        return (List<ThietBi>) thietBiRepository.findAll();
    }
 
 
    public void updateThietBi(ThietBi thietbi) {
        ThietBi existingThietBi = findById(thietbi.getMaTB());
        existingThietBi.setTenTB(thietbi.getTenTB());
        existingThietBi.setMoTaTB(thietbi.getMoTaTB());
        thietBiRepository.save(existingThietBi);
    }

  


    @Transactional
    public boolean deleteThietBiById(int id) {
        // Kiểm tra xem có bất kỳ bản ghi nào trong thongtinsd có MaTB bằng id hay không
        List<ThongTinSD> thongTinSDList = thongTinSDRepository.findByMaTBEquals(id);
        if (!thongTinSDList.isEmpty()) {
            return false;
        } else {
            thietBiRepository.deleteById(id);
            return true;
        }
    }

    public void saveThietBi(ThietBi thietbi) {
        thietBiRepository.save(thietbi);
    }

    public boolean importExcel(ArrayList<ThietBi> danhsachThietBi) {
        for (ThietBi tb : danhsachThietBi) {
            if(thietBiRepository.existsById(tb.getMaTB()))
                continue;
            thietBiRepository.save(tb);
        }
        return true;
    }

    public boolean processSheet(Sheet sheet) {
        ArrayList<ThietBi> danhsachThietBi = new ArrayList();
        boolean firstRow = true;
        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue; // Bỏ qua dòng đầu tiên
            }
            if (!isRowEmpty(row)) {
                ThietBi tb = new ThietBi();
                int cellIndex = 0;
                for (Cell cell : row) {
                    switch (cellIndex) {
                        case 0:
                            double textString = cell.getNumericCellValue();
                            int demo = (int) textString;
                            tb.setMaTB(demo);
                            break;
                        case 1:
                            tb.setTenTB(cell.getStringCellValue());
                            break;
                        case 2:
                            tb.setMoTaTB(cell.getStringCellValue());
                            break;
                        default:
                    }
                    cellIndex++;
                }
                danhsachThietBi.add(tb);

            }
        }
        
        return importExcel(danhsachThietBi);
    }

    private boolean isRowEmpty(Row row) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

}
