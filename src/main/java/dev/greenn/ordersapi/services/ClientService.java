package dev.greenn.ordersapi.services;

import dev.greenn.ordersapi.domain.Client;
import dev.greenn.ordersapi.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Client addClient(Client client){
      return  clientRepository.save(client);
    }
}
