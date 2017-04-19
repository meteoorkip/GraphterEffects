package screens.idescreen.topbar.menubar;

import general.ViewModel;
import general.compiler.CompilerRunnable;
import general.files.DocumentModel;
import general.files.IOManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import screens.idescreen.about.AboutView;
import screens.idescreen.tab.simpleviewer.SimpleViewerPresenter;
import screens.idescreen.tab.simpleviewer.SimpleViewerView;
import utils.FileUtils;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuPresenter implements Initializable {

    public Pane borderPane;
    public MenuBar menuBar;
    @Inject
    private ViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bind();
    }

    public void bind(){
        borderPane.prefWidthProperty().bind( viewModel.sceneWidthProperty() );
        menuBar.prefWidthProperty().bind(borderPane.prefWidthProperty());
    }

    public void exitMenuItemPressed(ActionEvent actionEvent) {
        borderPane.fireEvent(
                new WindowEvent(
                        borderPane.getScene().getWindow(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    public void loadScriptMenuItemPressed(ActionEvent actionEvent) {
        if (!DocumentModel.getInstance().graafvisChangesSaved()){
            if(IOManager.showGraafvisScriptSaveDialog(DocumentModel.getInstance().getGraafVisFilePath(), DocumentModel.getInstance().graafVisCode)){
                IOManager.showLoadScriptPopup();
            }
        } else {
            IOManager.showLoadScriptPopup();
        }


    }

    public void newScriptMenuItemPressed(ActionEvent actionEvent) {
        if (!DocumentModel.getInstance().graafvisChangesSaved()){
            if(IOManager.showGraafvisScriptSaveDialog(DocumentModel.getInstance().getGraafVisFilePath(), DocumentModel.getInstance().graafVisCode)){
                DocumentModel.getInstance().newGraafVisFile();
            }
        } else {
            DocumentModel.getInstance().newGraafVisFile();
        }
        DocumentModel.getInstance().newGraafVisFile();
    }

    public void saveMenuItemPressed(ActionEvent actionEvent) {
        //IOManager.showSaveScriptPopup(Model.getInstance().getCodePaneTextArea().getText());
        TabPane tabPane = (TabPane) (((BorderPane) (viewModel.getMainView())).getCenter());
        Tab viewerTab = tabPane.getSelectionModel().getSelectedItem();
        String viewerTabID = viewerTab.getText();

        if (viewerTabID.split("\\.")[1].equals("vis")){
            Path codePath = DocumentModel.getInstance().getGraafVisFilePath();

            String code = DocumentModel.getInstance().graafVisCode;
            List<String> codeList = new ArrayList<>();
            codeList.add(code);
            try {
                Files.write(codePath, codeList, Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            IOManager.showSaveSVGPopup(DocumentModel.getInstance().getGeneratedSVG(viewerTabID));
        }
    }

    public void loadGraphMenuItemPressed(ActionEvent actionEvent) {
        IOManager.showLoadGraphsPopup(false);
    }

    public void saveAsMenuItemPressed(ActionEvent actionEvent) {
        TabPane tabPane = (TabPane) (((BorderPane) ((StackPane)viewModel.getMainView()).getChildren().get(0)).getCenter());
        Tab viewerTab = tabPane.getSelectionModel().getSelectedItem();
        String viewerTabID = viewerTab.getText();

        if (tabPane.getSelectionModel().getSelectedIndex() == 0){
            Path codePath = DocumentModel.getInstance().getGraafVisFilePath();
            String code = DocumentModel.getInstance().graafVisCode;
            IOManager.showSaveScriptPopup(codePath, code);
        } else {
            IOManager.showSaveSVGPopup(DocumentModel.getInstance().getGeneratedSVG(viewerTabID));
        }
    }

    public void graphAsImageMenuItemPressed(ActionEvent actionEvent) {
        BorderPane centralBorder = ((BorderPane) (((AnchorPane) borderPane.getParent()).getParent().getParent()).getParent());
        SplitPane topBar = (SplitPane) centralBorder.getTop();
        ButtonBar buttonBar = (ButtonBar) ((SplitPane) ((AnchorPane) topBar.getItems().get(1)).getChildren().get(0)).getItems().get(1);
        ComboBox comboBox = (ComboBox) buttonBar.getButtons().get(0);
        String selectedGraphName = comboBox.getSelectionModel().getSelectedItem().toString();
        Path selectedGraphPath = DocumentModel.getInstance().getGraphPathMap().get(selectedGraphName);
        CompilerRunnable compilerRunnable = new CompilerRunnable(Paths.get("defaultvisualization.vis"), selectedGraphPath);
        new Thread(compilerRunnable).start();
    }

    public void graphAsTextMenuItemPressed(ActionEvent actionEvent) {
        BorderPane centralBorder = ((BorderPane) (((AnchorPane) borderPane.getParent()).getParent().getParent()).getParent());
        SplitPane topBar = (SplitPane) centralBorder.getTop();
        ButtonBar buttonBar = (ButtonBar) ((SplitPane) ((AnchorPane) topBar.getItems().get(1)).getChildren().get(0)).getItems().get(1);
        ComboBox comboBox = (ComboBox) buttonBar.getButtons().get(0);
        String selectedGraphName = comboBox.getSelectionModel().getSelectedItem().toString();

        Path selectedGraphPath = DocumentModel.getInstance().getGraphPathMap().get(selectedGraphName);
        String graphAsString = "";
        try {
            graphAsString = FileUtils.readFromFile(selectedGraphPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TabPane centralTabPane = (TabPane) centralBorder.getCenter();
        SimpleViewerView simpleViewerView = new SimpleViewerView();
        SimpleViewerPresenter simpleViewerPresenter = (SimpleViewerPresenter) simpleViewerView.getPresenter();
        simpleViewerPresenter.loadContent(selectedGraphName, graphAsString);
        centralTabPane.getTabs().add(new Tab(selectedGraphName, simpleViewerView.getView()));
    }

    public void undoChangesMenutemPressed(ActionEvent actionEvent) {
        Path graafvisScriptPath = DocumentModel.getInstance().getGraafVisFilePath();
        if (!DocumentModel.getInstance().graafvisChangesSaved()) {
            if (IOManager.showGraafvisScriptSaveDialog(graafvisScriptPath, DocumentModel.getInstance().graafVisCode)) {
                DocumentModel.getInstance().loadGraafVisFile(graafvisScriptPath);
            }
        }
    }

    public void helpMenuItemPressed(ActionEvent actionEvent) {
        AboutView aboutView = new AboutView();
        Scene scene = new Scene(aboutView.getView());
        Stage aboutDialog = new Stage();
        aboutDialog.setScene(scene);
        aboutDialog.show();
    }
}