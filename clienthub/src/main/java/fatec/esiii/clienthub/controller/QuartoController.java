package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.facade.IFachada;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Quarto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    @Autowired
    private IFachada fachada;

    private ResponseEntity<?> processarRetorno(String msgErro, Object data, HttpStatus statusSucesso) {
        if (msgErro == null) {
            return ResponseEntity.status(statusSucesso).body(data);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("erro", msgErro);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Quarto quarto) {
        String msg = fachada.salvar(quarto);
        return processarRetorno(msg, quarto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Quarto quarto) {
        quarto.setId(id);
        String msg = fachada.alterar(quarto);
        return processarRetorno(msg, quarto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        Quarto quarto = new Quarto();
        quarto.setId(id);
        String msg = fachada.excluir(quarto);
        return processarRetorno(msg, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> consultar() {
        List<EntidadeDominio> resultados = fachada.consultar(new Quarto());
        List<Quarto> quartos = resultados.stream().map(e -> (Quarto) e).collect(Collectors.toList());
        return ResponseEntity.ok(quartos);
    }
}
