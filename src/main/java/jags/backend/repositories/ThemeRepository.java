package jags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jags.backend.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>  {

}
