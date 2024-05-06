package Springweb.repository;

import Springweb.entity.XuLy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XuLyViPhamRepository extends CrudRepository<XuLy, Integer> {}
