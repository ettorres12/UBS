package com.example.UBS.controller;

import com.example.UBS.model.Consulta;
import com.example.UBS.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Consultas")
@CrossOrigin(origins = "*") // Permite acesso do frontend (ajuste conforme necessário)
public class ConsultaController {

    @Autowired
    private ConsultaService ConsultaService;

    @GetMapping
    public List<Consulta> listarTodos() {
        return ConsultaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return ConsultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<Consulta> salvar(@RequestBody Consulta Consulta) {
    Consulta salvo = ConsultaService.salvar(Consulta);
    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta Consulta) {
        try {
            Consulta atualizado = ConsultaService.atualizar(id, Consulta);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ConsultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
