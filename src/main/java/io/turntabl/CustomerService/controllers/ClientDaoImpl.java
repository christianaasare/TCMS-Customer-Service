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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clients")
    @Override
    public List<ClientTO> getAllClients() {
        return this.jdbcTemplate.query("select * from customers", BeanPropertyRowMapper.newInstance(ClientTO.class));
    }
    
    
    @ApiOperation("Search Client By Name")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clients/search/{name}")
    @Override
    public List<ClientTO> getClientByName(@PathVariable String name) {
        return this.jdbcTemplate.query("select * from customers where name like ?",
                new Object[]{name + "%"},
                BeanPropertyRowMapper.newInstance(ClientTO.class));
    }
//
    
    @ApiOperation("Add Client")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/clients/addAClient")
    @Override
    public void addClient(@RequestBody Map<String,String> addClient) {
        jdbcTemplate.update(
                "insert into customers(name,address,phone,email) values(?,?,?,?)",
                addClient.get("name"), addClient.get("address"), addClient.get("phone"), addClient.get("email"));


    }
    
    
    @ApiOperation("Delete Client By ID")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/clients/delete/{id}")
    @Override
    public void deleteClient(@PathVariable("id") Integer customer_id) {
        jdbcTemplate.update(
                "delete from customers where customer_id = ?", customer_id);

    }
    
    
    @ApiOperation("Update a client")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/clients/updateClient/{id}")
    @Override
    public void updateClient(Integer clientID, ClientTO client) {
        this.jdbcTemplate.update(
                "update customers set name = ?, phone = ?, address = ?, email = ?, where customer_id = ?",
                client.getName(), client.getPhone(),client.getAddress(), client.getEmail());
    }


}
