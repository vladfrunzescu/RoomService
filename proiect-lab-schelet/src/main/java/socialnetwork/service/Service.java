package socialnetwork.service;

import javafx.scene.control.ComboBox;
import socialnetwork.domain.*;
import socialnetwork.repository.Repository;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    private Repository<String, Produs> produsRepo;
    private Repository<Long, Comanda> comandaRepo;
    private Repository<Long, Comanda> cerereRepo;

    public Service( Repository<String, Produs> produsRepo, Repository<Long, Comanda> comandaRepo, Repository<Long, Comanda> cerereRepo) {
        this.produsRepo = produsRepo;
        this.comandaRepo = comandaRepo;
        this.cerereRepo = cerereRepo;
    }

    @Override
    public void addObserver(Observer e){
        observers.add(e);
    }
    @Override
    public void removeObserver(Observer e){
        observers.remove(e);
    }
    @Override
    public void notifyObservers(){
        observers.stream().forEach(x->x.update());
    }
    
    public List<Produs> getAllProduse(){
        List<Produs> list = new ArrayList<Produs>();
        produsRepo.findAll().forEach(list::add);
        return list;
    }

    public Comanda addComand(Comanda comanda){
        Comanda rez = comandaRepo.save(comanda);
        if(rez == null){
            notifyObservers();
        }
        return rez;
    }
    
    public List<Comanda> getAllComanda(){
        List<Comanda> list = new ArrayList<Comanda>();
        comandaRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Comanda> getAllRequests(){
        List<Comanda> list = new ArrayList<Comanda>();
        cerereRepo.findAll().forEach(list::add);
        return list;
    }

    public Comanda addRequest(Comanda comanda){
        Comanda rez = cerereRepo.save(comanda);
        if(rez == null){
            notifyObservers();
        }
        return rez;
    }

    public Comanda deleteRequest(Comanda comanda){
        Comanda rez = cerereRepo.delete(comanda.getId());
        if(rez != null){
            notifyObservers();
        }
        return rez;
    }

    public Produs addProdus(Produs produs){
        Produs rez = produsRepo.save(produs);
        if(rez == null){
            notifyObservers();
        }
        return rez;
    }

    public String getSumaByCamera(Long nrCamera) {
        Double suma = 0d;
        for(Comanda r : getAllRequests()){
            if(r.getNumarCamera().equals(nrCamera))
                suma += r.getPret();
        }
        return suma.toString();
    }

    public List<Produs> getAllComandate(Long nrCamera){
        List<Produs> p = new ArrayList<>();

        for(Comanda r : getAllRequests()){
            if(r.getNumarCamera().equals(nrCamera))
                p.add(produsRepo.findOne(r.getProdus()));
        }
        return p;
    }
/*
    //exp: hotels by location
    //     doi       unu
    public List<Doi> doiByUnu(Long ID){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getUnu_id().equals(ID))
                list.add(e);
        }
        return list;
    }

    public List<Unu> getAllUnu(){
        List<Unu> list = new ArrayList<Unu>();
        unuRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Doi> getAllDoi(){
        List<Doi> list = new ArrayList<Doi>();
        doiRepo.findAll().forEach(list::add);
        return list;
    }

    //exp: hotels by location
    //     doi       unu
    public List<Doi> doiByUnuDates(Long ID, LocalDateTime start, LocalDateTime end){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getUnu_id().equals(ID) && e.getFirst_date().isAfter(start) && e.getSecond_date().isBefore(end))
                list.add(e);
        }
        return list;
    }

    public List<DoiDTO> personalizate(Unu entity){
        List<DoiDTO> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(true){//e.getFirst_date().isAfter(LocalDateTime.now()) && e.getUnu_id().equals(entity.getId())){
                Unu entitate = unuRepo.findOne(e.getUnu_id());
                DoiDTO dto = new DoiDTO(e.getFirst_myenum(), e.getSecond_myenum(), e.getId(), entitate, e.getFirst_string(), e.getSecond_string(), e.getFirst_Integer(), e.getSecond_Integer(), e.getFirst_integer(), e.getSecond_integer(), e.getFirst_Long(), e.getSecond_Long(), e.getFirst_long(), e.getSecond_long(), e.getFirst_Double(), e.getSecond_Double(), e.getFirst_double(), e.getSecond_double(), e.getFirst_date(), e.getSecond_date());
                list.add(dto);
            }
        }
        return list;
    }

 */

}