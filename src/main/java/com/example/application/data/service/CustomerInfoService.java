package com.example.application.data.service;

import java.util.List;
import com.example.application.data.entity.CustomerInfo;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@AnonymousAllowed
public class CustomerInfoService{


    private final CustomerInfoRepository repository;


    @PostConstruct
    void init() {

        findAll();
    }

    public CustomerInfoService(CustomerInfoRepository repository) {
        this.repository = repository;
    }

    public  List<CustomerInfo> findAll() {
        return repository.findAll();
    }

    public CustomerInfo save(CustomerInfo customer) {
        return repository.save(customer);
    }
}
