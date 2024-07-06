package ECService.demo.repository;

import ECService.demo.entity.Apply;
import ECService.demo.repository.mapping.ListInfoMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplyRepository extends CrudRepository<Apply, Long> {
    Optional<Apply> findAllBy();
    Optional<Apply> findByPhoneNumber(String phoneNumber);
}
