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

package net.lustenauer.utils.strings;

/**
 * Some String methods
 *
 * @author Patric Hollenstein
 * @since 1.0.0
 */
public final class STR {

    /*
     * PUBLIC METHODS
     */

    /**
     * Similar to substring from the {@link String} class but with more error handling. It is also possible to set
     * the end value bigger then the String is long
     *
     * @param value Original string
     * @param start start value from the substring
     * @param end   end value from the substring
     * @return the wanted part of the string or a blank string when the given values out of range
     * @since 1.0.0
     */
    public static String substr(String value, int start, int end) {
        if (start >= end) return "";

        if (value.length() < end) {
            return value.substring(start);
        }

        return value.substring(start, end);
    }

    /**
     * Performs single argument substitution for the 'pattern' passed as parameter.
     * <p>
     * For example,
     * <p>
     * <pre>
     * STR.form(&quot;Hi {}.&quot;, &quot;there&quot;);
     * </pre>
     * <p>
     * will return the string "Hi there.".
     * <p>
     *
     * @param pattern The pattern which will be parsed and formatted
     * @param arg     The argument to be substituted in place of the formatting anchor
     * @return The formatted string
     * @since 1.2.0
     */
    public static String form(String pattern, Object arg) {
        return Formatter.arrayFormat(pattern, new Object[]{arg});
    }

    /**
     * Performs a two argument substitution for the 'pattern' passed as parameter.
     * <p>
     * For example,
     * <p>
     * <pre>
     * STR.form(&quot;Hi {}. My name is {}.&quot;, &quot;Alice&quot;, &quot;Bob&quot;);
     * </pre>
     * <p>
     * will return the string "Hi Alice. My name is Bob.".
     *
     * @param pattern The pattern which will be parsed and formatted
     * @param arg1    The first argument to be substituted in place of the formatting anchor
     * @param arg2    The second argument to be substituted in place of the formatting anchor
     * @return The formatted string
     * @since 1.2.0
     */
    public static String form(String pattern, Object arg1, Object arg2) {
        return Formatter.arrayFormat(pattern, new Object[]{arg1, arg2});
    }

    /**
     * Performs a multiple argument substitution for the 'pattern' passed as parameter.
     * <p>
     * For example,
     * <p>
     * <pre>
     * STR.form(&quot;Hi {}. My name is {} and i am {} years old.&quot;, &quot;Alice&quot;, &quot;Bob&quot, 27;);
     * </pre>
     * <p>
     * will return the string "Hi Alice. My name is Bob and i am 27 years old.".
     *
     * @param pattern The pattern which will be parsed and formatted
     * @param args    The arguments to be substituted in place of the formatting anchor
     * @return The formatted string
     * @since 1.2.0
     */
    public static String form(String pattern, Object... args) {
        return Formatter.arrayFormat(pattern, args);
    }


    /*
     * CONSTRUCTORS
     */

    private STR() {
    }
}
