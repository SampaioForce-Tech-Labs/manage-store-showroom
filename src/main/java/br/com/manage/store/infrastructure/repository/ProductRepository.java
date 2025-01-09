package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByCode(String code);
}
