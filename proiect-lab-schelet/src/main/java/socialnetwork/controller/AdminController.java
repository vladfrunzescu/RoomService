package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.*;
import socialnetwork.domain.Comanda;
import socialnetwork.service.Service;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AdminController implements Observer {

    private Service service;

    private ObservableList<Comanda> comandaModel = FXCollections.observableArrayList();

    /*
    @FXML
    private TableView<DoiDTO> tableView;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn1;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn11;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn12;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn13;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn14;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn15;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn16;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn17;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn18;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn19;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn110;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn111;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn112;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn113;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn114;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn115;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn116;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn117;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn118;
    @FXML
    private TableColumn<DoiDTO,String> tableColumn119;

    @FXML
    private TextField textField;
    @FXML
    private Label labelStatus;

     */
    @FXML
    private Label labelNume;

    @FXML
    private ListView<Comanda> listView;

    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPret;

    @Override
    public void update() {
        this.initModel();
    }

    public void setPage( Service service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        /*
        tableColumn1.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_myenum"));
        tableColumn11.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_myenum"));
        tableColumn12.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("ID"));
        tableColumn13.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("unu_id"));
        tableColumn14.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_string"));
        tableColumn15.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_string"));
        tableColumn16.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_Integer"));
        tableColumn17.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_Integer"));
        tableColumn18.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_integer"));
        tableColumn19.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_integer"));
        tableColumn110.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_Long"));
        tableColumn111.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_Long"));
        tableColumn112.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_long"));
        tableColumn113.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_long"));
        tableColumn114.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_Double"));
        tableColumn115.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_Double"));
        tableColumn116.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_double"));
        tableColumn117.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_double"));
        tableColumn118.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("first_date"));
        tableColumn119.setCellValueFactory(new PropertyValueFactory<DoiDTO, String>("second_date"));

        tableView.setItems(doiModel);

         */
    }

    private void initModel() {

        // labelNume.setText(unu.getSecond_string());

        listView.setCellFactory(lv -> new ListCell<Comanda>() {
            @Override
            public void updateItem(Comanda entity, boolean empty) {
                super.updateItem(entity, empty) ;
                setText(empty ? null : "Comanda: " + entity.getId() + "; Pret: " + entity.getPret().toString());
            }
        });

        comandaModel = FXCollections.observableArrayList(service.getAllRequests());
        listView.setItems(comandaModel);
    }


    @FXML
    public void handleRezolvaButton(){
        Comanda c = listView.getSelectionModel().getSelectedItem();
        try {
            if (c != null) {
                service.addComand(c);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Comanda rezolvata!", "Comanda dvs a fost scrisa in fisier.");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a selectat o comanda!");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleAdaugaProdus(){
        try {
            if (textFieldNume.getText() != null && textFieldPret.getText() != null) {
                String nume = textFieldNume.getText();
                Double pret = Double.parseDouble(textFieldPret.getText());

                Produs produs = new Produs(nume, pret);
                service.addProdus(produs);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Produs adaugat!", "Produsul introdus a fost adaugat!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a salvat produsul!");
            }
        }catch(Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }

    }
/*
    @FXML
    public void handleComandaButton(){
        try {
            Comanda p = listView.getSelectionModel().getSelectedItem();
            if (p != null) {
                Comanda comanda = new Comanda(nrCamera, p.getId(), p.getPret(), LocalDateTime.now());
                service.addComand(comanda);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Comanda trimisa!", "Comanda dvs a fost trimisa!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a salvat comanda");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleTrimiteButton() {
        try {
            String text_message = textField.getText();
            List<Long> all_entities = new ArrayList<>();
            for(Unu e : service.getAllUnu()){
                if(!e.equals(unu)){
                    all_entities.add(e.getId());
                }
            }

            //service.addEntity();
            //MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Mesaj", "Mesajul a fost trimis cu succes!");
            textField.setText("");
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRetragButton(){
        try{
            //if(service.action() == null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
                MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRevinButton(){
        try{
            //if(service.action() == null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

 */
}
