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

package net.lustenauer.utils.jfx;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * <p>
 * Contains some helper for javafx
 *
 * @author Patric Hollenstein
 * @since Version 1.0.0
 */
public class FX {

    /**
     * Helper returns the window from the given node
     *
     * @param rootPane Root pane off the Window
     * @return Window of the node
     */
    public static Window win(Pane rootPane) {
        return rootPane.getScene().getWindow();
    }

    /**
     * Helper returns the stage from the given node
     *
     * @param rootPane Root pane off the Stage
     * @return Stage of the node
     */
    public static Stage stage(Pane rootPane) {
        return (Stage) rootPane.getScene().getWindow();
    }

    /**
     * Helper to set the given value as title in the given pane
     *
     * @param rootPane This must be a {@link Stage}
     * @param title    Text to set as title
     */
    public static void title(Pane rootPane, String title) {
        ((Stage) rootPane.getScene().getWindow()).setTitle(title);
    }

    /**
     * Helper to close the given stage
     *
     * @param rootPane Stage to close as a rootPane
     */
    public static void closeWindow(Pane rootPane) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }
}
