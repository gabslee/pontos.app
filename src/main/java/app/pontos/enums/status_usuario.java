//ENUM DE RESPRESENTAÇÃO DE STATUS PARA EXCLUSÃO LÓGICA DO ATRIBUTO


package app.pontos.enums;

public enum status_usuario {
    ATIVO(1),
    INATIVO(2);

    private int status;
    status_usuario(int i) {
        this.status = i;
    }


    public int getStatus() {
        return this.status;
    }

    public static status_usuario toStatus(int i) {
        switch (i){
            case 1: return ATIVO;
            case 2: return INATIVO;
            default: return ATIVO;

        }
    }



}
