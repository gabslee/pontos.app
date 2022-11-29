//ENUM PARA VALIDAÇÃO DO TIPO DE PONTO CRIADO/ALTERADO



package app.pontos.enums;

public enum tipo_ponto {

    ENTRADA(1),
    SAIDA(2);

    private final int tipo;

    tipo_ponto(int i) {
        this.tipo = i;
    }

    public int getTipo() {
        return tipo;
    }

    public static tipo_ponto toTipo(int i){
        switch (i){
            case 1: return ENTRADA;
            case 2: return SAIDA;
            default: return ENTRADA;
        }
    }

}
