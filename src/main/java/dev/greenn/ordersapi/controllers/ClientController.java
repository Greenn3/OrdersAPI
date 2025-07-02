package dev.greenn.ordersapi.controllers;

import dev.greenn.ordersapi.domain.Client;
import dev.greenn.ordersapi.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client savedClient = clientService.addClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
}
}
