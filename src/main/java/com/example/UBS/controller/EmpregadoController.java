package com.example.UBS.controller;

import com.example.UBS.model.Empregado;
import com.example.UBS.service.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empregados")
@CrossOrigin(origins = "*") 
public class EmpregadoController {

    @Autowired
    private EmpregadoService EmpregadoService;
  
    @GetMapping
    public List<Empregado> listarTodos() {
        return EmpregadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empregado> buscarPorId(@PathVariable Long id) {
        return EmpregadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<Empregado> salvar(@RequestBody Empregado Empregado) {
    Empregado salvo = EmpregadoService.salvar(Empregado);
    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empregado> atualizar(@PathVariable Long id, @RequestBody Empregado Empregado) {
        try {
            Empregado atualizado = EmpregadoService.atualizar(id, Empregado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        EmpregadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
