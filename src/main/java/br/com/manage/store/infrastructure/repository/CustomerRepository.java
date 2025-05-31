package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByCpf(String cpf);
    Optional<CustomerEntity> findByEmail(String email);
    Page<CustomerEntity> findAll(Specification<CustomerEntity> specification, Pageable pageable);

}
