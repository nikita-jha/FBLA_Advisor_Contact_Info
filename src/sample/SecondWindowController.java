    package sample;

    import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

    public class SecondWindowController implements Initializable {


        //configure the table
        @FXML private TableView<Advisor> tableView;
        @FXML private TableColumn<Advisor, String> advisorNumberColumn;
        @FXML private TableColumn<Advisor, String> nameColumn;
        @FXML private TableColumn<Advisor, String> phoneColumn;
        @FXML private TableColumn<Advisor, String> faxColumn;
        @FXML private TableColumn<Advisor, String> primEmailColumn;
        @FXML private TableColumn<Advisor, String> secEmailColumn;
        @FXML private TableColumn<Advisor, String> addressColumn;
        @FXML private TableColumn<Advisor, String> cityStateZipColumn;
        @FXML private TableColumn<Advisor, CheckBox> archiveColumn;
        @FXML private TableColumn<Advisor, CheckBox> primaryAdvisorColumn;

        DBConnection mydbconnection = new DBConnection();
        //This method is for connecting the table with the insert advisor button


        @FXML private TextField advisorNumberTextField;
        @FXML private TextField nameTextField;
        @FXML private TextField phoneTextField;
        @FXML private TextField faxTextField;
        @FXML private TextField primaryEmailTextField;
        @FXML private TextField secondaryEmailTextField;
        @FXML private TextField addressTextField;
        @FXML private TextField cityStateZipTextField;
        @FXML private CheckBox primaryAdvisorCheckBox;
        @FXML private CheckBox showArchiveCheckBox;
        @FXML private Button buttonAddAdvisor;
        @FXML private Button buttonDeleteAdvisor;
        @FXML private Button buttonSearch;
        @FXML private Button buttonArchive;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
        final FileChooser fileChooser = new FileChooser();


        @Override
        public void initialize(URL url, ResourceBundle rb) {
            advisorNumberColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("advisorID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("name"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("phoneNumber"));
            faxColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("fax"));
            primEmailColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("primaryEmail"));
            secEmailColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("secondaryEmail"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("address"));
            cityStateZipColumn.setCellValueFactory(new PropertyValueFactory<Advisor, String>("cityStateZip"));
            archiveColumn.setCellValueFactory(new PropertyValueFactory<Advisor, CheckBox>("archive"));
            primaryAdvisorColumn.setCellValueFactory(new PropertyValueFactory<Advisor, CheckBox>("primaryAdvisor"));

            tableView.setItems(getAdvisors(null));

            //Update the table to allow for the fields to be editable
            tableView.setEditable(false);


            //This will allow the table to select only one row at once
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


            tableView.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() > 1) {
                    onEdit();
                }
            });

        }


        public void onEdit() {
            // check the table's selected item and get selected item
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                //disable addAdvisor Search and Delete


                advisorNumberTextField.setEditable(false);
                nameTextField.setEditable(false);
                buttonAddAdvisor.setText("Save");
                buttonAddAdvisor.setDisable(false);
                buttonDeleteAdvisor.setDisable(false);
                buttonDeleteAdvisor.setDisable(false);
                buttonSearch.setDisable(false);
                Advisor selectedAdvisor = tableView.getSelectionModel().getSelectedItem();
                advisorNumberTextField.setText(String.valueOf(selectedAdvisor.getAdvisorID()));
                nameTextField.setText(selectedAdvisor.getName());
                phoneTextField.setText(selectedAdvisor.getPhoneNumber());
                faxTextField.setText(selectedAdvisor.getFax());
                primaryEmailTextField.setText(selectedAdvisor.getPrimaryEmail());
                secondaryEmailTextField.setText(selectedAdvisor.getSecondaryEmail());
                addressTextField.setText(selectedAdvisor.getAddress());
                cityStateZipTextField.setText(selectedAdvisor.getCityStateZip());
            }
        }
        //This method will remove the selected row(s) from the table

        public void deleteButtonPushed() {
            ObservableList<Advisor> selectedRows, allAdvisors;
            allAdvisors = tableView.getItems();

            //this gives use the rows that we selected
            selectedRows = tableView.getSelectionModel().getSelectedItems();

            //Loop over the selected rows and remove the Advisor object from the table
            for (Advisor advisor: selectedRows) {
                allAdvisors.remove(advisor);
                mydbconnection.deleteAdvisor(advisor);
            }
        }
        public void archiveButtonPushed() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select PDF files");
            fileChooser.setInitialDirectory(new File("C:\\"));

            File selectedFiles = fileChooser.showSaveDialog(null);
            String filename = "";
            if (selectedFiles != null) {
                filename = selectedFiles.getAbsolutePath();
                System.out.println("The selected file is : " + filename);
            }
            Advisor newAdvisor = new Advisor();
            newAdvisor.setAdvisorID(advisorNumberTextField.getText());
            newAdvisor.setName(nameTextField.getText());
            CheckBox chkBox = new CheckBox();
            chkBox.setSelected(false);
            newAdvisor.setArchive(chkBox);
            try {
                mydbconnection.createCSVFile(newAdvisor, filename);
            }catch(Exception e) {
                e.printStackTrace();
            }
            tableView.setItems(getAdvisors(null));
        }



        //This method will create a new Advisor object and add it to the table
        public void newAdvisorButtonPushed() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
            try {

                if (primaryAdvisorCheckBox.isSelected()) {
                    mydbconnection.updatePrimaryAdvisor(null);
                }

                Advisor newAdvisor = new Advisor();
                newAdvisor.setAdvisorID(advisorNumberTextField.getText());
                newAdvisor.setName(nameTextField.getText());
                newAdvisor.setPhoneNumber(phoneTextField.getText());
                newAdvisor.setFax(faxTextField.getText());
                newAdvisor.setPrimaryEmail(primaryEmailTextField.getText());
                newAdvisor.setSecondaryEmail(secondaryEmailTextField.getText());
                newAdvisor.setAddress(addressTextField.getText());
                newAdvisor.setCityStateZip(cityStateZipTextField.getText());
                newAdvisor.setPrimaryAdvisor(primaryAdvisorCheckBox);
                newAdvisor.setDateAdded(sdf.format(new Date()));
                CheckBox chkBox = new CheckBox();
                chkBox.setSelected(false);
                newAdvisor.setArchive(chkBox);
                newAdvisor.setChapter(chkBox);
                ArrayList<Advisor> al = mydbconnection.readAdvisor(newAdvisor);
                //Get all the items from the table as a list, then add the new Advisor to the list
                if (al.size() == 0) {
                    mydbconnection.insertAdvisor(newAdvisor);
                } else {
                    Advisor adv = al.get(0);
                    CheckBox chkBoxU = new CheckBox();
                    chkBoxU.setSelected(true);
                    adv.setArchive(chkBoxU);
                    mydbconnection.updateAdvisor(adv);
                    mydbconnection.insertAdvisor(newAdvisor);
                    buttonAddAdvisor.setText("Add Advisor");
                    buttonAddAdvisor.setDisable(false);
                    buttonDeleteAdvisor.setDisable(true);
                    buttonSearch.setDisable(false);
                }

                tableView.setItems(getAdvisors(null));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        //This method will search Advisor object and add it to the table
        public void searchAdvisorButtonPushed() {

            Advisor advisor = new Advisor();
            advisor.setAdvisorID(advisorNumberTextField.getText());
            advisor.setName(nameTextField.getText());
            advisor.setArchive(showArchiveCheckBox);
            //Get all the items from the table as a list, then add the new Advisor to the list
            tableView.setItems(getAdvisors(advisor));
        }

        public ObservableList<Advisor> getAdvisors(Advisor passedAdvisor)  {
            ObservableList<Advisor> advisor = FXCollections.observableArrayList();
            ArrayList<Advisor> al = mydbconnection.readAdvisor(passedAdvisor);
            for (Advisor adv : al) {
                Advisor iAd = new Advisor();
                iAd.setAdvisorID(adv.getAdvisorID());
                iAd.setName(adv.getName());
                iAd.setPhoneNumber(adv.getPhoneNumber());
                iAd.setFax(adv.getFax());
                iAd.setPrimaryEmail(adv.getPrimaryEmail());
                iAd.setSecondaryEmail(adv.getSecondaryEmail());
                iAd.setAddress(adv.getAddress());
                iAd.setCityStateZip(adv.getCityStateZip());
                System.out.println("NAME : " + adv.getName() + " : primary : " + adv.getPrimaryAdvisor().isSelected());
                iAd.setPrimaryAdvisor(adv.getPrimaryAdvisor());
                iAd.setDateAdded(adv.getDateAdded());
                //CheckBox chkBox = new CheckBox();
                //chkBox.setSelected(false);
                iAd.setArchive(adv.getArchive());
                advisor.add(iAd);
            }
            return advisor;
        }








    }
