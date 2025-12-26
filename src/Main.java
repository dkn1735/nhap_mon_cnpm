import controller.DangNhap_DangKyController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DangNhap_DangKy.fxml"));
        DangNhap_DangKyController controller = loader.getController();
        controller.setStage(stage);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();      
    }

    public static void main(String[] args) {
        launch(args); 
    }
}