package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.SalesProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesProductRepository extends JpaRepository<SalesProductEntity, Long> {
}
