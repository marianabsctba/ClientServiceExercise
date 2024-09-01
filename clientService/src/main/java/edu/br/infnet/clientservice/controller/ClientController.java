package edu.br.infnet.clientservice.controller;

import edu.br.infnet.clientservice.model.Client;
import edu.br.infnet.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(id);
        Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
