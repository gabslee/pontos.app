package app.pontos.enums;

public enum status_empresa {

    ATIVO(1),
    INATIVO(2);

    private int status;
    status_empresa(int i) {
        this.status = i;
    }


    public int getStatus() {
        return this.status;
    }

    public static status_empresa toStatus(int i) {
        switch (i){
            case 1: return ATIVO;
            case 2: return INATIVO;
            default: return ATIVO;

        }
    }


}

