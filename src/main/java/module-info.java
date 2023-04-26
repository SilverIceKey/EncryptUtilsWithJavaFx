module com.sik.encryptutils {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.jfoenix;

    requires cn.hutool;

    opens com.sik.encryptutils to javafx.fxml;
    exports com.sik.encryptutils;
    exports com.sik.encryptutils.features.main;
    opens com.sik.encryptutils.features.main to javafx.fxml;
}