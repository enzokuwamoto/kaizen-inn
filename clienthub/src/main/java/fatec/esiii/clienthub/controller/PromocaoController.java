package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.facade.IFachada;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Promocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/promocoes")
public class PromocaoController {

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
    public ResponseEntity<?> salvar(@RequestBody Promocao promocao) {
        String msg = fachada.salvar(promocao);
        return processarRetorno(msg, promocao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Promocao promocao) {
        promocao.setId(id);
        String msg = fachada.alterar(promocao);
        return processarRetorno(msg, promocao, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Promocao p = new Promocao();
        p.setId(id);
        String msg = fachada.excluir(p);
        return processarRetorno(msg, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Promocao>> consultar() {
        List<EntidadeDominio> resultados = fachada.consultar(new Promocao());
        List<Promocao> promocoes = resultados.stream().map(e -> (Promocao) e).collect(Collectors.toList());
        return ResponseEntity.ok(promocoes);
    }
}
