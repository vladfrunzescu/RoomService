package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comanda extends Entity<Long>{
    private Long numarCamera;
    private String produs;
    private Double pret;
    private LocalDateTime data;

    public Comanda(Long numarCamera, String produs, Double pret, LocalDateTime data) {
        this.setId(generateRandomId());
        this.numarCamera = numarCamera;
        this.produs = produs;
        this.pret = pret;
        this.data = data;
    }

    public Comanda(Long id, Long numarCamera, String produs, Double pret, LocalDateTime data) {
        this.setId(id);
        this.setId(generateRandomId());
        this.numarCamera = numarCamera;
        this.produs = produs;
        this.pret = pret;
        this.data = data;
    }

    private Long generateRandomId() {
        long leftLimit = 1L;
        long rightLimit = 1000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public Long getNumarCamera() {
        return numarCamera;
    }

    public void setNumarCamera(Long numarCamera) {
        this.numarCamera = numarCamera;
    }

    public String getProdus() {
        return produs;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Objects.equals(numarCamera, comanda.numarCamera) &&
                Objects.equals(produs, comanda.produs) &&
                Objects.equals(pret, comanda.pret) &&
                Objects.equals(data, comanda.data) &&
                Objects.equals(comanda.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), numarCamera, produs, pret, data);
    }
}
