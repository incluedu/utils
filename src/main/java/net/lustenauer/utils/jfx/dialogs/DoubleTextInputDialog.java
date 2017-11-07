/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Patric Hollenstein
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.lustenauer.utils.jfx.dialogs;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by Patric Hollenstein on 28.10.17.
 * <p>
 * Create a dialogs with two text inputs
 * </p>
 *
 * @author Patric Hollenstein
 */
public class DoubleTextInputDialog extends Dialog {

    private TextField textField1 = new TextField();
    private TextField textField2 = new TextField();

    private Label label1 = new Label("label1");
    private Label label2 = new Label("label2");

    // TODO: 28.10.17 implement class


    public DoubleTextInputDialog() {
        init();
    }

    /**
     * @param label1Value value for the 1st label
     * @param label2Value value for the 2nd label
     */
    public DoubleTextInputDialog(String label1Value, String label2Value) {
        label1.setText(label1Value);
        label2.setText(label2Value);
        init();
    }

    private void init() {

        //ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        GridPane.setHgrow(textField1, Priority.ALWAYS);
        GridPane.setHgrow(textField2, Priority.ALWAYS);

        grid.add(label1, 0, 0);
        grid.add(textField1, 1, 0);
        grid.add(label2, 0, 1);
        grid.add(textField2, 1, 1);

        getDialogPane().setContent(grid);

        // Request focus on the first textfield by default.
        Platform.runLater(() -> textField1.requestFocus());

    }

    /**
     * Set the text of the first textField
     *
     * @param value Text to set
     */
    public void setText1Value(String value) {
        textField1.setText(value);
    }


    /**
     * Set the text of the 2nd text field
     *
     * @param value Text to set
     */
    public void setText2Value(String value) {
        textField2.setText(value);
    }


    /**
     * Get the text from the first text field
     *
     * @return Text
     */
    public String getText1Value() {
        return textField1.getText();
    }

    /**
     * Get the text from the 2nd text field
     *
     * @return Text
     */
    public String getText2Value() {
        return textField2.getText();
    }

    /**
     * Set the text of the 1st label
     *
     * @param value Text to set
     */
    public void setLabel1Value(String value) {
        label1.setText(value);
    }

    /**
     * Set the text of the 2nd label
     *
     * @param value Text to set
     */
    public void setLabel2Value(String value) {
        label2.setText(value);
    }
}
