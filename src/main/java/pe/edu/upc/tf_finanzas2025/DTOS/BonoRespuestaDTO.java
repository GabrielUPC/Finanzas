package pe.edu.upc.tf_finanzas2025.DTOS;

import java.util.List;

public class BonoRespuestaDTO {
    private BonoDTO bono;
    private List<FlujoDTO> flujos;
    private ResultadoDTO resultado;

    public BonoDTO getBono() {
        return bono;
    }

    public void setBono(BonoDTO bono) {
        this.bono = bono;
    }

    public List<FlujoDTO> getFlujos() {
        return flujos;
    }

    public void setFlujos(List<FlujoDTO> flujos) {
        this.flujos = flujos;
    }

    public ResultadoDTO getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoDTO resultado) {
        this.resultado = resultado;
    }
}
