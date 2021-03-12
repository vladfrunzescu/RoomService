package socialnetwork.repository.file;

import socialnetwork.domain.Comanda;
import socialnetwork.domain.validators.Validator;
import socialnetwork.utils.Constants;
import socialnetwork.utils.My_Enum;

import java.time.LocalDateTime;
import java.util.List;

public class ComandaFile extends AbstractFileRepository<Long, Comanda> {
    public ComandaFile(String fileName, Validator<Comanda> validator) {
        super(fileName, validator);
    }

    @Override
    public Comanda extractEntity(List<String> attributes) {
        Long ID = Long.parseLong(attributes.get(0));
        Long first_Long = Long.parseLong(attributes.get(1));
        String second_string = attributes.get(2);
        Double first_Double = Double.parseDouble(attributes.get(3));
        LocalDateTime first_date = LocalDateTime.parse(attributes.get(4), Constants.DATE_TIME_FORMATTER);

        Comanda entity = new Comanda(ID, first_Long, second_string, first_Double, first_date);
        return entity;
    }

    @Override
    protected String createEntityAsString(Comanda entity) {
        return entity.getId()+";"+entity.getNumarCamera()+";"+entity.getProdus()+";"+entity.getPret()+";"+entity.getData().format(Constants.DATE_TIME_FORMATTER);
    }
}
