package uz.gigalab.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gigalab.shop.admin.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
