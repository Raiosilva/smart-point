package com.example.demo.service.impl;

import com.example.demo.repositories.LaunchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.example.demo.entities.Launch;
import com.example.demo.service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaunchServiceImpl implements LaunchService {

    private static final Logger log = LoggerFactory.getLogger(LaunchServiceImpl.class);

    @Autowired
    private LaunchRepository launchRepository;

    @Override
    public Page<Launch> searchByEmployeeId(Long employeeId, PageRequest pageRequest) {
        log.info("Buscando lançamentos para o funcionário ID {}", employeeId);
        return this.launchRepository.findByEmployeeId(employeeId, pageRequest);
    }

    @Cacheable("lancamentoPorId")
    public Optional<Optional<Launch>> searchById(Long id) {
        log.info("Buscando um lançamento pelo ID {}", id);
        return Optional.ofNullable(this.launchRepository.findById(id));
    }

    @CachePut("lancamentoPorId")
    public Launch post(Launch launch) {
        log.info("Persistindo o lançamento: {}", launch);
        return this.launchRepository.save(launch);
    }

    @Override
    public void remove(Long id) {
        log.info("Removendo o lançamento ID {}", id);
        this.launchRepository.deleteById(id);
    }
}
