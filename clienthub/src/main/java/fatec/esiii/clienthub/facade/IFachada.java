package fatec.esiii.clienthub.facade;

import fatec.esiii.clienthub.model.EntidadeDominio;
import java.util.List;

public interface IFachada {
    String salvar(EntidadeDominio entidade);
    String alterar(EntidadeDominio entidade);
    String excluir(EntidadeDominio entidade);
    List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
