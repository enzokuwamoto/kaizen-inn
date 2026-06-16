package fatec.esiii.clienthub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "politicas_cancelamento")
public class PoliticaCancelamento extends EntidadeDominio {

    private String descricao;
    private int horasLimiteSemMulta = 48;
    private BigDecimal percentualMulta = new BigDecimal("50.0");

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getHorasLimiteSemMulta() { return horasLimiteSemMulta; }
    public void setHorasLimiteSemMulta(int horasLimiteSemMulta) { this.horasLimiteSemMulta = horasLimiteSemMulta; }

    public BigDecimal getPercentualMulta() { return percentualMulta; }
    public void setPercentualMulta(BigDecimal percentualMulta) { this.percentualMulta = percentualMulta; }
}
