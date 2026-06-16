package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class GerarLog implements IStrategy {

    private static final Logger logger = LoggerFactory.getLogger(GerarLog.class);

    @Override
    public String processar(EntidadeDominio entidade) {
        logger.info("AUDITORIA - Registro na data/hora: {} | Operação na entidade: {} | ID afetado: {}", 
            LocalDateTime.now(), entidade.getClass().getSimpleName(), entidade.getId());
        return null;
    }
}
