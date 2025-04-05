package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.domain.entity.SalesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

    Page<SalesEntity> findAll(Specification<SalesEntity> specification, Pageable pageable);
}
