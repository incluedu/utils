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

package net.lustenauer.utils.jfx.controls.statusbar;

import javafx.scene.control.ProgressBar;
import org.controlsfx.control.StatusBar;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

public class StatBar {

    /**
     * Set the given text with a status to the status bar
     *
     * @param statusBar {@link StatusBar} from ControlsFx
     * @param text      text to set
     * @param status    use a status from {@link Status}
     */
    public static void set(StatusBar statusBar, String text, Status status) {
        statusBar.setText(text);

        if (status == null) status = Status.NONE;

        switch (status) {
            case OK:
                statusBar.getRightItems().setAll(new Glyph("FontAwesome", FontAwesome.Glyph.CHECK_CIRCLE));
                break;

            case FAULT:
                statusBar.getRightItems().setAll(new Glyph("FontAwesome", FontAwesome.Glyph.EXCLAMATION_CIRCLE));
                break;

            case WARN:
                statusBar.getRightItems().setAll(new Glyph("FontAwesome", FontAwesome.Glyph.EXCLAMATION_CIRCLE));
                break;

            case INFO:
                statusBar.getRightItems().setAll(new Glyph("FontAwesome", FontAwesome.Glyph.INFO_CIRCLE));
                break;

            case ANDROID:
                statusBar.getRightItems().setAll(new Glyph("FontAwesome", FontAwesome.Glyph.ANDROID));
                break;

            case PROGRESS:
                statusBar.getRightItems().setAll(new ProgressBar());
                break;

            default:
            case NONE:
                statusBar.getRightItems().clear();
                break;
        }
    }

    /**
     * couple off stats for StatBar
     * <ul>
     * <li>Status.NONE</li>
     * <li>Status.OK</li>
     * <li>Status.FAULT</li>
     * <li>Status.WARN</li>
     * <li>Status.INFO</li>
     * </ul>
     */
    public static enum Status {
        NONE,
        OK,
        FAULT,
        WARN,
        INFO,
        ANDROID,
        PROGRESS
    }
}
