package Springweb.repository;

import Springweb.entity.ThongTinSD;
import Springweb.entity.XuLy;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XuLyViPhamRepository extends CrudRepository<XuLy, Integer> {
    public List<XuLy> findByMaTVEquals(int id);
}
