package socialnetwork.domain.validators;

import socialnetwork.domain.Produs;

public class ProdusValidator implements Validator<Produs> {
    public ProdusValidator() {}

    @Override
    public void validate(Produs entity) throws ValidationException {
        String errors = "";

        if(entity.getId() == null|| entity.getId().equals("")){
            errors += "Nume produs invalid!\n";
        }

        if(entity.getPret() == null || entity.getPret() < 0){
            errors += "Pret invalid!\n";
        }

        if(!errors.equals("")){
            throw new ValidationException(errors);
        }
    }
}
