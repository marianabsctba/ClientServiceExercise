package edu.br.infnet.clientservice.repository;

import edu.br.infnet.clientservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
