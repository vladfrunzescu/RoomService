package socialnetwork.domain.validators;

import socialnetwork.domain.Comanda;
import socialnetwork.domain.Produs;
import socialnetwork.repository.Repository;

public class ComandaValidator implements Validator<Comanda> {

    private Repository<String, Produs> repoProdus;
    public ComandaValidator(Repository<String, Produs> repoProdus) {
        this.repoProdus = repoProdus;
    }

    @Override
    public void validate(Comanda entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null || entity.getId() < 0){
            errors += "Id invalid!\n";
        }

        if(entity.getNumarCamera() == null || entity.getNumarCamera() < 0){
            errors += "Nr camera invalid!\n";
        }

        if(entity.getProdus() == null || entity.getProdus().equals("")){
            errors += "Produs invalid!\n";
        }

        if(repoProdus.findOne(entity.getProdus()) == null){
            errors += "Produs inexistent!\n";
        }

        if(entity.getPret() == null || entity.getPret() < 0){
            errors += "Pret invalid!\n";
        }

        if(entity.getData().getYear() < 1800){
            errors += "Data invalida!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
