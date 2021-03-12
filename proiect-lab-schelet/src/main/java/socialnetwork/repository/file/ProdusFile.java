package socialnetwork.repository.file;

import socialnetwork.domain.Produs;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;

import java.time.LocalDateTime;
import java.util.List;

public class ProdusFile extends AbstractFileRepository<String, Produs> {
    public ProdusFile(String fileName, Validator<Produs> validator) {
        super(fileName, validator);
    }

    @Override
    public Produs extractEntity(List<String> attributes) {
        String ID = attributes.get(0);
        Double first_Double = Double.parseDouble(attributes.get(1));

        Produs entity = new Produs(ID, first_Double);
        return entity;
    }

    @Override
    protected String createEntityAsString(Produs entity) {
        return entity.getId()+";"+entity.getPret().toString();
    }
}
