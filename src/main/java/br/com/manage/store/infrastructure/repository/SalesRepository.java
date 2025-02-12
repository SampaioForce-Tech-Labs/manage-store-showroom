package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
}
