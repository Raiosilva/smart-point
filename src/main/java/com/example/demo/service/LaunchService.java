package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.entities.Launch;

import java.util.Optional;

public interface LaunchService {

    Page<Launch> searchByEmployeeId(Long employeeId, PageRequest pageRequest);

    Optional<Optional<Launch>> searchById(Long id);

    Launch post(Launch launch);

    void remove(Long id);

}
