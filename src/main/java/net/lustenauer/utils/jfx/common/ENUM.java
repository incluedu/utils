/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Patric Hollenstein
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

package net.lustenauer.utils.jfx.common;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Some common enum methods
 *
 * @author Patric Hollenstein
 * @since Version 1.2.0
 */
public class ENUM {

    /*
     * CONSTRUCTORS
     */
    private ENUM() {
    }

    /*
     * PUBLIC METHODS
     */

    /**
     * This method returns a list witch contains all not {@link Deprecated} annotated entries off the given enum. This
     * is useful when you use the enum in UI lists like {@link javafx.scene.control.ComboBox} and you don't like to
     * display the deprecated values.
     *
     * @param enumType Enum class to put in list
     * @param <E>      Generic type
     * @return A list with all not deprecated enum entries from the given enum
     * @throws NoSuchFieldException some errors what could excepted during annotation check
     * @since Version 1.2.0
     */
    public static <E extends Enum<E>> List<E> toList(Class<E> enumType) throws NoSuchFieldException {

        EnumSet<E> enumTypeValues = EnumSet.allOf(enumType);
        List<E> toRemove = new ArrayList<>();
        for (E myEnum : enumTypeValues) {
            Annotation[] annotations = myEnum.getClass().getField(myEnum.name()).getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Deprecated) {
                    toRemove.add(myEnum);
                }
            }
        }
        enumTypeValues.removeAll(toRemove);
        return new ArrayList<>(enumTypeValues);
    }
}
