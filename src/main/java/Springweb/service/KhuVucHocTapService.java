package Springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.repository.ThietBiRepository;
import Springweb.repository.ThongTinSDRepository;

@Service
public class KhuVucHocTapService {
    ThongTinSDRepository ttsdReposity;

    @Autowired
    public KhuVucHocTapService(ThongTinSDRepository ttsdReposity) {
        this.ttsdReposity = ttsdReposity;
    }

    public ThongTinSD findById(Integer id) {
        Optional<ThongTinSD> tOptional = ttsdReposity.findById(id);
        if (tOptional.isPresent()) {
            return tOptional.get();
        } else {
            throw new RuntimeException("Không tìm thấy ThietBi với ID: " + id);
        }
    }

    public Iterable<ThongTinSD> searchTTSD(int category, String keyword) {
        switch (category) {
            case 1:
                return ttsdReposity.findByMaTV(Integer.parseInt(keyword.trim()));
            case 2:
                return ttsdReposity.findByKhoa(keyword.trim());
            case 3:
                return ttsdReposity.findByName(keyword.trim());
            default:
                throw new IllegalArgumentException("Category không hợp lệ: " + category);
        }
    }

    public List<ThongTinSD> findAll() {
        return (List<ThongTinSD>) ttsdReposity.findAll();
    }

    public void saveThietBi(ThongTinSD ttsd) {
        ttsdReposity.save(ttsd);
    }
}
