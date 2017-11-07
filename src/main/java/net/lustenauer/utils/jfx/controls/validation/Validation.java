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

package net.lustenauer.utils.jfx.controls.validation;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patric Hollenstein on 01.11.17.
 *
 * @author Patric Hollenstein
 */
public class Validation {

    private List<Validator> validators = new ArrayList();


    /**
     * Add a given validator
     * @param validator
     */
    public void addValidator(Validator validator) {
        validators.add(validator);

        // TODO: 01.11.17 implement validation
/*
        control.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                System.out.println("lost focus --> " + control);
                control.setStyle("-fx-background-color: lightcoral;");

            }
        });
*/
    }

    /**
     * check all validators and return false when one of theme is not valid
     *
     * @return false if one validator is not valid
     */
    public boolean isValid() {
        for (Validator v : validators) {
            if (!v.isValid()) return false;
        }
        return true;
    }

    /**
     * Give a list with all the messages from validation
     *
     * @return Messages from the validation
     */
    public List<String> getMessages() {
        List<String> messages = new ArrayList<>();

        for (Validator v : validators) {
            if (!v.isValid()) messages.add(v.getMessage());
        }

        return messages;
    }
}
