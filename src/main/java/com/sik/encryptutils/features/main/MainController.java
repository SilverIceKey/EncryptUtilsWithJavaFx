package com.sik.encryptutils.features.main;

import cn.hutool.core.util.HexUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.sik.encryptutils.utils.EncryptUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    /**
     * 算法选择
     */
    @FXML
    private JFXComboBox<String> algorithm;
    /**
     * 模式选择
     */
    @FXML
    private JFXComboBox<String> mode;
    /**
     * 填充选择
     */
    @FXML
    private JFXComboBox<String> padding;
    /**
     * 密钥
     */
    @FXML
    private TextField key;
    /**
     * 是否解密HEX
     */
    @FXML
    private JFXComboBox<String> isDecodeHEX;
    /**
     * 偏移
     */
    @FXML
    private TextField iv;
    /**
     * 加密
     */
    @FXML
    private JFXButton encrypt;
    /**
     * 解密
     */
    @FXML
    private JFXButton decrypt;
    /**
     * 内容
     */
    @FXML
    private TextArea content;
    /**
     * 结果
     */
    @FXML
    private TextArea result;
    /**
     * 算法数据
     */
    private ObservableList<String> algorithmList = FXCollections.observableArrayList("AES", "DES", "DESede","SM4");
    /**
     * 模式数据
     */
    private ObservableList<String> modeList = FXCollections.observableArrayList("ECB", "CBC");
    /**
     * 填充数据
     */
    private ObservableList<String> paddingList = FXCollections.observableArrayList("ZeroPadding", "Pkcs7Padding");
    /**
     * 是否解密
     */
    private ObservableList<String> isDecodeHEXList = FXCollections.observableArrayList("是", "否");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algorithm.setItems(algorithmList);
        algorithm.getSelectionModel().select(0);
        mode.setItems(modeList);
        mode.getSelectionModel().select(0);
        padding.setItems(paddingList);
        padding.getSelectionModel().select(1);
        isDecodeHEX.setItems(isDecodeHEXList);
        isDecodeHEX.getSelectionModel().select(0);
        mode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if ("ECB".equals(t1)) {
                    iv.setDisable(true);
                } else {
                    iv.setDisable(false);
                }
            }
        });
        iv.setDisable(true);
        encrypt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (EncryptUtils.isNeedIV(mode.getSelectionModel().getSelectedItem())) {
                    if (iv.getText().isEmpty()) {
                        showInfoDialog("请输入偏移");
                        return;
                    }
                }
                if (key.getText().isEmpty()) {
                    showInfoDialog("请输入密钥");
                    return;
                }
                if (content.getText().isEmpty()) {
                    showInfoDialog("请输入内容");
                    return;
                }
                result.setText(EncryptUtils.encryptBase64(algorithm.getSelectionModel().getSelectedItem(),
                        mode.getSelectionModel().getSelectedItem(), padding.getSelectionModel().getSelectedItem(),
                        key.getText(), iv.getText(), content.getText()));
            }
        });
        decrypt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (EncryptUtils.isNeedIV(mode.getSelectionModel().getSelectedItem())) {
                    if (iv.getText().isEmpty()) {
                        showInfoDialog("请输入偏移");
                        return;
                    }
                }
                if (key.getText().isEmpty()) {
                    showInfoDialog("请输入密钥");
                    return;
                }
                if (content.getText().isEmpty()) {
                    showInfoDialog("请输入内容");
                    return;
                }
                byte[] dealKey;
                if ("是".equals(isDecodeHEX.getSelectionModel().getSelectedItem()) && HexUtil.isHexNumber(key.getText())) {
                    dealKey = HexUtil.decodeHex(key.getText());
                } else {
                    dealKey = key.getText().getBytes(Charset.defaultCharset());
                }
                result.setText(EncryptUtils.decryptBase64(algorithm.getSelectionModel().getSelectedItem(),
                        mode.getSelectionModel().getSelectedItem(), padding.getSelectionModel().getSelectedItem(),
                        new SecretKeySpec(dealKey,algorithm.getSelectionModel().getSelectedItem()).getEncoded()
                        , iv.getText(), content.getText()));
            }
        });
    }

    private void showInfoDialog(String content) {
        Alert alert = new Alert(Alert.AlertType.NONE, content, ButtonType.OK);
        alert.showAndWait();
    }
}