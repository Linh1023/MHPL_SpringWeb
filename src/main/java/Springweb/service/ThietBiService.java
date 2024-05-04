package Springweb.service;

import Springweb.entity.ThietBi;
import Springweb.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ThietBiService {

    private ThietBiRepository thietBiRepository;

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

    public void deleteThietBiById(int id) {
        ThietBi thietbi = findById(id);
        thietBiRepository.delete(thietbi);
    }

    public void saveThietBi(ThietBi thietbi) {
        thietBiRepository.save(thietbi);
    }
}
