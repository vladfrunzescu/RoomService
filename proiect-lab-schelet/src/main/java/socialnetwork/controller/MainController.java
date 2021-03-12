package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import socialnetwork.domain.Comanda;
import socialnetwork.domain.Produs;
import socialnetwork.service.Service;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDateTime;

public class MainController implements Observer {

    private Long nrCamera;
    private Service service;

    private ObservableList<Produs> produsModel = FXCollections.observableArrayList();
    private ObservableList<Produs> comandaModel = FXCollections.observableArrayList();

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
    private ListView<Produs> listView;
    @FXML
    private Label labelPret;
    
    @FXML
    private ListView<Produs> listViewComandate;
    
    @Override
    public void update() {
        this.initModel();
    }

    public void setPage( Service service, Long nrCamera) {
        this.service = service;
        this.nrCamera = nrCamera;
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

        listView.setCellFactory(lv -> new ListCell<Produs>() {
            @Override
            public void updateItem(Produs entity, boolean empty) {
                super.updateItem(entity, empty) ;
                setText(empty ? null : "Produs: " + entity.getId() + "; Pret: " + entity.getPret().toString());
            }
        });

        produsModel = FXCollections.observableArrayList(service.getAllProduse());
        listView.setItems(produsModel);
        labelPret.setText(service.getSumaByCamera(nrCamera));

        listViewComandate.setCellFactory(lv -> new ListCell<Produs>() {
            @Override
            public void updateItem(Produs entity, boolean empty) {
                super.updateItem(entity, empty) ;
                setText(empty ? null : "Produs: " + entity.getId() + "; Pret: " + entity.getPret().toString());
            }
        });

        comandaModel = FXCollections.observableArrayList(service.getAllComandate(nrCamera));
        listViewComandate.setItems(comandaModel);
    }

    @FXML
    public void handleComandaButton(){
        try {
            Produs p = listView.getSelectionModel().getSelectedItem();
            if (p != null) {
                Comanda comanda = new Comanda(nrCamera, p.getId(), p.getPret(), LocalDateTime.now());
                service.addRequest(comanda);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Comanda trimisa!", "Comanda dvs a fost trimisa!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a salvat comanda");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }
/*
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
