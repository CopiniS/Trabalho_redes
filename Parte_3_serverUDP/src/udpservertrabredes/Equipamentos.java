package udpservertrabredes;

public class Equipamentos {
    private String lugar;
    private boolean status;
    
    public Equipamentos(String lugar, boolean status){
        this.lugar = lugar;
        this.status = status;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
