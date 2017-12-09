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

package net.lustenauer.utils.jfx.controls.tableview;

import javafx.event.Event;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * (Fairly) reusable edit cell that commits on loss of focus on the text field. Overriding the commitEdit(...) method
 * is difficult to do without relying on knowing the default implementation, which I had to do here. The test code
 * includes a key handler on the table that initiates editing on a key press.
 * <p>
 * Class base was https://gist.github.com/james-d/be5bbd6255a4640a5357
 *
 * @param <S>
 * @param <T>
 * @author james-d
 * @author Patric Hollenstein
 * @since Version 1.1.0
 */
public class EditCell<S, T> extends TableCell<S, T> {

    // Text field for editing
    // TODO: allow this to be a plugable control.
    private final TextField textField = new TextField();

    // Converter for converting the text in the text field to the user type, and vice-versa:
    private final StringConverter<T> converter;

    public EditCell(StringConverter<T> converter) {
        this.converter = converter;

        itemProperty().addListener((obx, oldItem, newItem) -> {
            if (newItem == null) {
                setText(null);
            } else {
                setText(converter.toString(newItem));
            }
        });
        setGraphic(textField);
        setContentDisplay(ContentDisplay.TEXT_ONLY);

        textField.setOnAction(evt -> {
            commitEdit(this.converter.fromString(textField.getText()));
        });
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                commitEdit(this.converter.fromString(textField.getText()));
            }
        });
        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                textField.setText(converter.toString(getItem()));
                cancelEdit();
                event.consume();
            } else if (event.getCode() == KeyCode.RIGHT) {
                getTableView().getSelectionModel().selectRightCell();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                getTableView().getSelectionModel().selectLeftCell();
                event.consume();
            } else if (event.getCode() == KeyCode.UP) {
                getTableView().getSelectionModel().selectAboveCell();
                event.consume();
            } else if (event.getCode() == KeyCode.DOWN) {
                getTableView().getSelectionModel().selectBelowCell();
                event.consume();
            }
        });
    }

    /**
     * Convenience converter that does nothing (converts Strings to themselves and vice-versa...).
     */
    public static final StringConverter<String> IDENTITY_CONVERTER = new StringConverter<String>() {

        @Override
        public String toString(String object) {
            return object;
        }

        @Override
        public String fromString(String string) {
            return string;
        }

    };

    /**
     * Convenience method for creating an EditCell for a String value.
     *
     * @return the created EditCell
     */
    public static <S> EditCell<S, String> createStringEditCell() {
        return new EditCell<>(IDENTITY_CONVERTER);
    }


    // set the text of the text field and display the graphic
    @Override
    public void startEdit() {
        super.startEdit();
        textField.setText(converter.toString(getItem()));
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.requestFocus();
    }

    // revert to text display
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    // commits the edit. Update property if possible and revert to text display
    @Override
    public void commitEdit(T item) {

        // This block is necessary to support commit on losing focus, because the baked-in mechanism
        // sets our editing state to false before we can intercept the loss of focus.
        // The default commitEdit(...) method simply bails if we are not editing...
        if (!isEditing() && !item.equals(getItem())) {
            TableView<S> table = getTableView();
            if (table != null) {
                TableColumn<S, T> column = getTableColumn();
                CellEditEvent<S, T> event = new CellEditEvent<>(table,
                        new TablePosition<>(table, getIndex(), column),
                        TableColumn.editCommitEvent(), item);
                Event.fireEvent(column, event);
            }
        }

        super.commitEdit(item);

        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

}