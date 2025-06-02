package br.com.manage.store.infrastructure.repository;

import br.com.manage.store.domain.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Long> {
}
