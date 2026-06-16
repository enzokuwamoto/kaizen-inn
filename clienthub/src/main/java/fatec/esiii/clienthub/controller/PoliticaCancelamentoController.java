package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.facade.IFachada;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.PoliticaCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/politicas-cancelamento")
public class PoliticaCancelamentoController {

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
    public ResponseEntity<?> salvar(@RequestBody PoliticaCancelamento p) {
        String msg = fachada.salvar(p);
        return processarRetorno(msg, p, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody PoliticaCancelamento p) {
        p.setId(id);
        String msg = fachada.alterar(p);
        return processarRetorno(msg, p, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        PoliticaCancelamento p = new PoliticaCancelamento();
        p.setId(id);
        String msg = fachada.excluir(p);
        return processarRetorno(msg, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PoliticaCancelamento>> consultar() {
        List<EntidadeDominio> resultados = fachada.consultar(new PoliticaCancelamento());
        List<PoliticaCancelamento> politicas = resultados.stream().map(e -> (PoliticaCancelamento) e).collect(Collectors.toList());
        return ResponseEntity.ok(politicas);
    }
}
