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

package net.lustenauer.utils.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper methods to format String like SLF4J according to very simple substitution
 * rules. Substitutions can be made 1, 2 or more arguments.
 * <pre>
 * Formatter.arrayFormat(&quot;Hi {}.&quot;, &quot;there&quot;)
 * </pre>
 * will return the string "Hi there.".
 *
 * @author Patric Hollenstein
 * @see net.lustenauer.utils.strings.STR#form(String, Object) (String, int, int)
 * @since 1.2.0
 */
public final class Formatter {
    /*
     * CONSTANTS
     */
    private static final String DELIM_STR = "{}";
    private static final char DELIM_START = '{';
    private static final char DELIM_STOP = '}';
    private static final char ESCAPE_CHAR = '\\';


    /*
     * PUBLIC METHODS
     */

    /**
     * Use slf4j MessageFormatter but make some changes to return only String
     *
     * @param messagePattern The message pattern which will be parsed and formatted
     * @param argArray       The argument to be substituted in place of the formatting anchor
     * @return The formatted message as a string
     * @since 1.2.0
     */
    public static String arrayFormat(final String messagePattern, final Object[] argArray) {

        if (messagePattern == null) {
            return "";
        }

        if (argArray == null) {
            return messagePattern;
        }

        int i = 0;
        int j;
        StringBuilder builder = new StringBuilder(messagePattern.length() + 50);

        int L;
        for (L = 0; L < argArray.length; L++) {

            j = messagePattern.indexOf(DELIM_STR, i);

            if (j == -1) {
                // no more variables
                if (i == 0) { // this is a simple string
                    return messagePattern;
                } else { // add the tail string which contains no variables and return the result.
                    builder.append(messagePattern, i, messagePattern.length());
                    return builder.toString();
                }
            } else {
                if (isEscapedDelimiter(messagePattern, j)) {
                    if (!isDoubleEscaped(messagePattern, j)) {
                        L--; // DELIM_START was escaped, thus should not be incremented
                        builder.append(messagePattern, i, j - 1);
                        builder.append(DELIM_START);
                        i = j + 1;
                    } else {
                        // The escape character preceding the delimiter start is
                        // itself escaped: "abc x:\\{}"
                        // we have to consume one backward slash
                        builder.append(messagePattern, i, j - 1);
                        deeplyAppendParameter(builder, argArray[L], new HashMap<Object[], Object>());
                        i = j + 2;
                    }
                } else {
                    // normal case
                    builder.append(messagePattern, i, j);
                    deeplyAppendParameter(builder, argArray[L], new HashMap<Object[], Object>());
                    i = j + 2;
                }
            }
        }
        // append the characters following the last {} pair.
        builder.append(messagePattern, i, messagePattern.length());
        return builder.toString();
    }


    /*
     * PRIVATE METHODS
     */

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static boolean isEscapedDelimiter(String messagePattern, int delimiterStartIndex) {

        if (delimiterStartIndex == 0) {
            return false;
        }
        char potentialEscape = messagePattern.charAt(delimiterStartIndex - 1);
        if (potentialEscape == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static boolean isDoubleEscaped(String messagePattern, int delimiterStartIndex) {
        if (delimiterStartIndex >= 2 && messagePattern.charAt(delimiterStartIndex - 2) == ESCAPE_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void deeplyAppendParameter(StringBuilder builder, Object o, Map<Object[], Object> seenMap) {
        if (o == null) {
            builder.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            safeObjectAppend(builder, o);
        } else {
            // check for primitive array types because they
            // unfortunately cannot be cast to Object[]
            if (o instanceof boolean[]) {
                booleanArrayAppend(builder, (boolean[]) o);
            } else if (o instanceof byte[]) {
                byteArrayAppend(builder, (byte[]) o);
            } else if (o instanceof char[]) {
                charArrayAppend(builder, (char[]) o);
            } else if (o instanceof short[]) {
                shortArrayAppend(builder, (short[]) o);
            } else if (o instanceof int[]) {
                intArrayAppend(builder, (int[]) o);
            } else if (o instanceof long[]) {
                longArrayAppend(builder, (long[]) o);
            } else if (o instanceof float[]) {
                floatArrayAppend(builder, (float[]) o);
            } else if (o instanceof double[]) {
                doubleArrayAppend(builder, (double[]) o);
            } else {
                objectArrayAppend(builder, (Object[]) o, seenMap);
            }
        }
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void objectArrayAppend(StringBuilder builder, Object[] a, Map<Object[], Object> seenMap) {
        builder.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, null);
            final int len = a.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(builder, a[i], seenMap);
                if (i != len - 1)
                    builder.append(", ");
            }
            // allow repeats in siblings
            seenMap.remove(a);
        } else {
            builder.append("...");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void booleanArrayAppend(StringBuilder builder, boolean[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void byteArrayAppend(StringBuilder builder, byte[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void charArrayAppend(StringBuilder builder, char[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void shortArrayAppend(StringBuilder builder, short[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void intArrayAppend(StringBuilder builder, int[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void longArrayAppend(StringBuilder builder, long[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void floatArrayAppend(StringBuilder builder, float[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void doubleArrayAppend(StringBuilder builder, double[] a) {
        builder.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            builder.append(a[i]);
            if (i != len - 1)
                builder.append(", ");
        }
        builder.append(']');
    }

    /**
     * from org.slf4j.helpers.MessageFormatter
     *
     * @since 1.2.0
     */
    private static void safeObjectAppend(StringBuilder builder, Object o) {
        try {
            String oAsString = o.toString();
            builder.append(oAsString);
        } catch (Throwable t) {
            builder.append("[FAILED toString()]");
        }
    }


    /*
     * CONSTRUCTOR
     */
    private Formatter() {
    }
}
