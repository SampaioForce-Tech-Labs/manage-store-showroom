package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByCpf(String cpf);

    Page<ClientEntity> findAll(Specification<ClientEntity> specification, Pageable pageable);

}
