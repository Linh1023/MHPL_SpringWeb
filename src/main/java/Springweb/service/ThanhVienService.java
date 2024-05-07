package Springweb.service;

import Springweb.entity.ThanhVien;
import Springweb.entity.ThongTinSD;
import Springweb.repository.ThanhVienRepository;
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
public class ThanhVienService {

    @Autowired
    private ThanhVienRepository thanhvienRepository;
    @Autowired
    private ThongTinSDRepository thongTinSDRepository;

    @Autowired
    public ThanhVienService(ThanhVienRepository thanhvienRepository) {
        this.thanhvienRepository = thanhvienRepository;
    }

    public ThanhVien findById(Integer id) {
        Optional<ThanhVien> thanhvienOptional = thanhvienRepository.findById(id);
        if (thanhvienOptional.isPresent()) {
            return thanhvienOptional.get();
        } else {
            throw new RuntimeException("Không tìm thấy ThanhVien với ID: " + id);
        }
    }

    // public List<ThietBi> searchThietBi(int category, String keyword) {
    // switch (category) {
    // case 1:
    // return thietBiRepository.findByTenTBContaining(keyword);
    // case 2:
    // return thietBiRepository.findByMaTBEquals(Integer.parseInt(keyword));
    // case 3:
    // return thietBiRepository.findByMoTaTBContaining(keyword);
    // default:
    // throw new IllegalArgumentException("Category không hợp lệ: " + category);
    // }
    // }

    public List<ThanhVien> findAll() {
        return (List<ThanhVien>) thanhvienRepository.findAll();
    }

    public void updateThanhVien(ThanhVien thanhvien) {
        ThanhVien existingThanhVien = findById(thanhvien.getMaTV());
        existingThanhVien.setHoTen(thanhvien.getHoTen());
        existingThanhVien.setKhoa(thanhvien.getKhoa());
        existingThanhVien.setNganh(thanhvien.getNganh());
        existingThanhVien.setSdt(thanhvien.getSdt());
        existingThanhVien.setEmail(thanhvien.getEmail());
        existingThanhVien.setPassword(thanhvien.getPassword());
        thanhvienRepository.save(existingThanhVien);
    }

    @Transactional
    public boolean deleteThanhVienById(int id) {
        // Kiểm tra xem có bất kỳ bản ghi nào trong thongtinsd có MaTB bằng id hay không
        List<ThongTinSD> thongTinSDList = thongTinSDRepository.findByMaTBEquals(id);
        if (!thongTinSDList.isEmpty()) {
            return false;
        } else {
            thanhvienRepository.deleteById(id);
            return true;
        }
    }

    public void saveThanhVien(ThanhVien thanhvien) {
        thanhvienRepository.save(thanhvien);
    }

    public boolean importExcel(ArrayList<ThanhVien> danhsachThanhVien) {
        for (ThanhVien tv : danhsachThanhVien) {
            if (thanhvienRepository.existsById(tv.getMaTV()))
                continue;
            thanhvienRepository.save(tv);
        }
        return true;
    }

    public boolean processSheet(Sheet sheet) {
        ArrayList<ThanhVien> danhsachThanhVien = new ArrayList();
        boolean firstRow = true;
        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue; // Bỏ qua dòng đầu tiên
            }
            if (!isRowEmpty(row)) {
                ThanhVien tv = new ThanhVien();
                int cellIndex = 0;
                for (Cell cell : row) {
                    switch (cellIndex) {
                        case 0:
                            double textString = cell.getNumericCellValue();
                            int demo = (int) textString;
                            tv.setMaTV(demo);
                            break;
                        case 1:
                            tv.setHoTen(cell.getStringCellValue());
                            break;
                        case 2:
                            tv.setKhoa(cell.getStringCellValue());
                            break;
                        case 3:
                            tv.setNganh(cell.getStringCellValue());
                            break;
                        case 4:
                            tv.setSdt(cell.getStringCellValue());
                            break;
                        case 5:
                            tv.setEmail(cell.getStringCellValue());
                            break;
                        case 6:
                            tv.setPassword(cell.getStringCellValue());
                            break;
                        default:
                    }
                    cellIndex++;
                }
                danhsachThanhVien.add(tv);

            }
        }

        return importExcel(danhsachThanhVien);
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