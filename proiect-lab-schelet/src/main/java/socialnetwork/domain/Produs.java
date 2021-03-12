package socialnetwork.domain;

import java.util.Objects;

public class Produs extends Entity<String>{
    private Double pret;

    public Produs(String nume, Double pret) {
        this.setId(nume);
        this.pret = pret;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return Objects.equals(pret, produs.pret)
                && Objects.equals(produs.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), pret);
    }
}
