package controller;

import dao.ThongKeDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class TrangChuController implements Initializable {

    @FXML
    private DatePicker dpTuNgay;
    @FXML
    private DatePicker dpDenNgay;
    @FXML
    private Label slHoKhau;
    @FXML
    private Label slThuongTru;
    @FXML
    private Label slTamTru;
    @FXML
    private Label slTamVang;

    private ThongKeDAO thongKeDAO = new ThongKeDAO();
    @FXML
    private PieChart pcGioiTinh;
    @FXML
    private BarChart<String, Number> bcNhomTuoi;
    @FXML
    private BarChart<String, Number> bcCuTru;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pcGioiTinh.setLegendVisible(false);
        bcNhomTuoi.setLegendVisible(false);
        bcCuTru.setLegendVisible(false);
        
        pcGioiTinh.setTitle("Giới tính");
        bcNhomTuoi.setTitle("Nhóm tuổi");
        bcCuTru.setTitle("Hình thức cư trú");
        
        // set default dates
        LocalDate today = LocalDate.now();
        dpTuNgay.setValue(LocalDate.of(today.getYear(), 1, 1));
        dpDenNgay.setValue(today);

        loadData();

        // load thống kê mặc định
        thongKe(null);
    }

    private void loadData() {
        try {
            slHoKhau.setText(String.valueOf(thongKeDAO.getTongHoKhau()));
            slThuongTru.setText(String.valueOf(thongKeDAO.getTongThuongTru()));
            slTamTru.setText(String.valueOf(thongKeDAO.getTongTamTru()));
            slTamVang.setText(String.valueOf(thongKeDAO.getTongTamVang()));
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải tổng số: " + e.getMessage());
        }
    }

    @FXML
    private void thongKe(ActionEvent event) {
        if (dpTuNgay.getValue() == null || dpDenNgay.getValue() == null) {
            showAlert("Thiếu dữ liệu", "Vui lòng chọn đầy đủ Từ ngày và Đến ngày");
            return;
        }

        if (dpTuNgay.getValue().isAfter(dpDenNgay.getValue())) {
            showAlert("Ngày không hợp lệ", "Từ ngày không được lớn hơn Đến ngày");
            return;
        }

        Date from = Date.valueOf(dpTuNgay.getValue());
        Date to = Date.valueOf(dpDenNgay.getValue());

        try {
            // ================= GIỚI TÍNH =================
            pcGioiTinh.getData().clear();
            thongKeDAO.getGioiTinhCount(from, to)
                    .forEach((gioiTinh, count) ->
                            pcGioiTinh.getData().add(
                                    new PieChart.Data(gioiTinh, count)
                            )
                    );

            // ================= NHÓM TUỔI =================
            bcNhomTuoi.getData().clear();
            BarChart.Series<String, Number> tuoiSeries = new BarChart.Series<>();
            tuoiSeries.setName("Nhóm tuổi");

            thongKeDAO.getDoTuoiCount(from, to)
                    .forEach((nhomTuoi, count) ->
                            tuoiSeries.getData().add(
                                    new BarChart.Data<>(nhomTuoi, count)
                            )
                    );

            bcNhomTuoi.getData().add(tuoiSeries);

            // ================= CƯ TRÚ =================
            bcCuTru.getData().clear();
            BarChart.Series<String, Number> cuTruSeries = new BarChart.Series<>();
            cuTruSeries.setName("Hình thức cư trú");

            thongKeDAO.getCuTruCount(from, to)
                    .forEach((loai, count) ->
                            cuTruSeries.getData().add(
                                    new BarChart.Data<>(loai, count)
                            )
                    );

            bcCuTru.getData().add(cuTruSeries);

        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể thống kê: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}