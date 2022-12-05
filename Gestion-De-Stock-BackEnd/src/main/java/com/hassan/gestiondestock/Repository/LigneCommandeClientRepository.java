package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.ligneCommandeClients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository<ligneCommandeClients, Long>  {
}
