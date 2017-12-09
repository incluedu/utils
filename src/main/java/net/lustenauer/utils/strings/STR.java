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

package net.lustenauer.utils.strings;

/**
 * Some String methodes
 *
 * @author Patric Hollenstein
 * @since 1.0.0
 */
public class STR {

    /**
     * Similar to substring from the {@link String} class but with more error handling. It is also possible to set
     * the end value bigger then the String is long
     *
     * @param value Original string
     * @param start start value from the substring
     * @param end   end value from the substring
     * @return the wanted part of the string or a blank string when the given values out of range
     * @version 1.0.0
     * @since 1.0.0
     */
    public static String substr(String value, int start, int end) {
        if (start >= end) return "";

        if (value.length() < end) {
            return value.substring(start);
        }

        return value.substring(start, end);
    }
}
