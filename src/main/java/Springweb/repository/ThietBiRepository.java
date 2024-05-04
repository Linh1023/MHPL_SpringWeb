
package Springweb.repository;

import Springweb.entity.ThietBi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThietBiRepository extends CrudRepository<ThietBi, Integer>{}
