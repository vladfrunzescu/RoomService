package socialnetwork;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.controller.AdminController;
import socialnetwork.controller.MainController;
import socialnetwork.domain.Comanda;
import socialnetwork.domain.Produs;
import socialnetwork.domain.validators.*;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.ComandaFile;
import socialnetwork.repository.file.ProdusFile;
import socialnetwork.repository.memory.InMemoryRepository;
import socialnetwork.service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    private static List<String> argumente = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Repository<String, Produs> produsRepo = new ProdusFile("data/produs.txt", new ProdusValidator());
        Repository<Long, Comanda> comandaRepo = new ComandaFile( "data/comanda.txt", new ComandaValidator(produsRepo));
        Repository<Long, Comanda> cerereRepo = new InMemoryRepository<>(new ComandaValidator(produsRepo));

        Service service = new Service(produsRepo, comandaRepo, cerereRepo);

        //fereastrele pentru clienti

        for(String s : argumente) {
            List<String> attr = Arrays.asList(s.split(":"));
            Stage stage = new Stage();
            try {
                if(!attr.get(1).equals("1")) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/mainView.fxml"));
                    AnchorPane root = loader.load();
                    MainController controller = loader.getController();
                    service.addObserver(controller);
                    controller.setPage(service, Long.parseLong(attr.get(1)));
                    stage.setTitle("Client " + attr.get(1));
                    stage.setScene(new Scene(root, 632, 405));
                    stage.show();
                }else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/adminView.fxml"));
                    AnchorPane root = loader.load();
                    AdminController controller = loader.getController();
                    service.addObserver(controller);
                    controller.setPage(service);
                    stage.setTitle("Admin");
                    stage.setScene(new Scene(root, 632, 405));
                    stage.show();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(String s:args){
            argumente.add(s);
        }
        launch(args);
    }
}



