package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer>   {
}
