package io.turntabl.CustomerService.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.CustomerService.DAO.ClientDAO;
import io.turntabl.CustomerService.models.ClientTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@Api
@RestController
public class ClientDaoImpl implements ClientDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperation("Get All Clients")
    @GetMapping("/getAllClients")
    public List<ClientTO> getAllClients() {
        return this.jdbcTemplate.query("select * from customers", BeanPropertyRowMapper.newInstance(ClientTO.class));
    }

    @ApiOperation("Search Client By Name")
    @Override
    @GetMapping("/{name}")
    public List<ClientTO> getClientByName(String customerName) {
        return this.jdbcTemplate.query("select * from customers where name like ?", new Object[]{customerName},BeanPropertyRowMapper.newInstance(ClientTO.class));
    }

    @ApiOperation("Add Client")
    @Override
    @PostMapping("/")
    public void addClient(@RequestBody Map<String,String> addClient) {
        jdbcTemplate.update(
                "insert into customers(name,address,phone,email) values(?,?,?,?)",
                addClient.get("name"), addClient.get("address"), addClient.get("phone"), addClient.get("email"));
        System.out.println("Client has been added successfully");

    }
    @ApiOperation("Delete Client By ID")
    @Override
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String clientID) {
        jdbcTemplate.update(
                "delete from customers where client_id = ?",
                clientID);
        System.out.println("Client Deleted Successfully");
    }
}
//    @ApiOperation("customer")
//    @GetMapping("/customer")
//    public ClientTO customer(
//
//            @RequestParam(name = "name",defaultValue = "Name of Client")
//            String name,
//            @RequestParam(name ="address",defaultValue = "Address of Client")
//                    String address,
//            @RequestParam(name = "email",defaultValue = "Email of of Client")
//                    String email,
//            @RequestParam(name = "telephone",defaultValue = "Telephone of Client")
//                    String telephone){
//        ClientTO myCustomer = new ClientTO();
//        myCustomer.setName(name);
//        myCustomer.setAddress(address);
//        myCustomer.setEmail(email);
//        myCustomer.setPhone(telephone);
//
//        return myCustomer;
//    }


