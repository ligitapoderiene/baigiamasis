package sample.filmai2.controler;

import sample.filmai2.MainApplication;
import sample.filmai2.model.Film;
import sample.filmai2.model.FilmDao;
import sample.filmai2.model.UserDao;
import sample.filmai2.utils.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label status;
    @FXML
    private TableView filmTableView;
    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;

    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn ratingColumn;
    @FXML
    private TableColumn categoryColumn;




    ObservableList<Film> list = FXCollections.observableArrayList();

    @FXML
    public void searchButtonClick() {
        list.clear();
        String titleField2 = titleField.getText();

        List<Film> filmList = FilmDao.searchByName(titleField2);


        for (Film film : filmList) {
            list.add(new Film(film.getId(), film.getTitle(), film.getDescription(), film.getRating(), film.getCategory()));

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));


            filmTableView.setItems(list);
        }
        if(filmList.isEmpty()){
            status.setText("Nepavyko atlikti paieška pagal knygos pavadinima");
        } else {
            status.setText("Pavyko atlikti paieška pagal knygos pavadinima");
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}