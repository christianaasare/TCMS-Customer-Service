package io.turntabl.CustomerService.DAO;
import io.turntabl.CustomerService.models.ClientTO;

import java.util.List;
import java.util.Map;

    public interface ClientDAO {
        List<ClientTO> getAllClients();
        List<ClientTO> getClientByName(String customerName);
        void addClient(Map<String,String> addClient);
        void deleteClient( String clientID);

    }

