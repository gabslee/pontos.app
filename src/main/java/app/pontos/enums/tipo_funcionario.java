//ENUM PARA VALIDAÇÃO DO TIPO DE FUNCIONARIO CRIADO/ALTERADO


package app.pontos.enums;

public enum tipo_funcionario {

    COLABORADOR(1),
    ADMINISTRADOR(2);

    private final int tipo;

    tipo_funcionario(int i) {
        this.tipo = i;
    }

    public int getTipo() {
        return tipo;
    }

    public static tipo_funcionario toTipo(int i){
        switch (i){
            case 1: return COLABORADOR;
            case 2: return ADMINISTRADOR;
            default: return COLABORADOR;
        }
    }
}
