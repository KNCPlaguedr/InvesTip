/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atv.pi.controller;

import com.atv.pi.model.cadastro;
import com.atv.pi.repository.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class CalcRestController {
    
    @Autowired
    private CalculoRepository calculoRepository;
    
    
    @PostMapping("/cadastrar")
    public ResponseEntity<cadastro> salvarFilme(@RequestBody cadastro Cadastro) {
        try {
            cadastro savedCadastro = calculoRepository.save(Cadastro);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCadastro);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
