package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.ReferencePersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferencePersonRepository extends JpaRepository<ReferencePersonEntity, Long> {
}
