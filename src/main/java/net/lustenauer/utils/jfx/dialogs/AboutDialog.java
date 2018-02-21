/*
 * MIT License
 *
 * Copyright (c) 2017 - 2018 Patric Hollenstein
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
 *
 */

package net.lustenauer.utils.jfx.dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Window;

/**
 * Created by Patric Hollenstein on 01.11.17.
 *
 * @author Patric Hollenstein
 * @since Version 1.2.0
 */
public class AboutDialog extends Dialog {

    /*
     * CONSTRUCTORS
     */

    /**
     * Create a empty about dialog
     *
     * @since Version 1.2.0
     */
    public AboutDialog() {
        init(null, null, null);
    }

    /**
     * @param titleText Window title
     * @param aboutText Content text
     * @since Version 1.2.0
     */
    public AboutDialog(String titleText, String aboutText) {
        init(null, titleText, aboutText);
    }

    /**
     * @param titleText          Window title
     * @param aboutText          Content text
     * @param backgroundImageUrl Path to background image
     * @since Version 1.2.0
     */
    public AboutDialog(String titleText, String aboutText, String backgroundImageUrl) {
        init(backgroundImageUrl, titleText, aboutText);
    }

    /**
     * @param owner              Owner window
     * @param titleText          Window title
     * @param aboutText          Content text
     * @param backgroundImageUrl Path to background image
     * @since Version 1.2.0
     */
    public AboutDialog(Window owner, String titleText, String aboutText, String backgroundImageUrl) {
        initOwner(owner);
        init(backgroundImageUrl, titleText, aboutText);
    }

    /*
     * PUBLIC METHODS
     */

    /**
     * Sets the background image of the dialog with the given URL
     *
     * @param imageUrl URL to set as background Image
     * @since Version 1.2.0
     */
    public void setBackgroundImage(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return;
        }
        BackgroundImage backgroundImage = new BackgroundImage(new Image(imageUrl),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        getDialogPane().setBackground(new Background(backgroundImage));
    }


    /*
     * PRIVATE METHODS
     */
    private void init(String imageUrl, String titleText, String aboutText) {
        getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        getDialogPane().setPrefWidth(640);
        getDialogPane().setPrefHeight(480);

        setBackgroundImage(imageUrl);
        setTitleText(titleText);
        setAboutText(aboutText);
    }

    private void setAboutText(String aboutText) {
        if (aboutText == null || aboutText.isEmpty()) {
            setContentText("");
        } else {
            setContentText(aboutText);
        }
    }

    private void setTitleText(String titleText) {
        if (titleText == null || titleText.isEmpty()) {
            setTitle("About");
        } else {
            setTitle(titleText);
        }
    }

}
