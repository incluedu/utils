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

package net.lustenauer.utils.bits;

/**
 * @author Patric Hollenstein
 * @since 1.0.0
 */
public class Bit {

    /**
     * Set a bit in the given byte with the given value
     *
     * @param _byte source byte
     * @param bitPosition from 0 to 7
     * @param bitValue    true or false
     * @return byte with the changed bits
     * @since 1.0.0
     */
    public final static byte setBit(byte _byte, int bitPosition, boolean bitValue) {
        if (bitValue)
            return (byte) (_byte | (1 << bitPosition));
        return (byte) (_byte & ~(1 << bitPosition));
    }

    /**
     * Get the value off a bit in the given byte
     *
     * @param _byte source byte
     * @param bitPosition from 0 to 7
     * @return the value off the requested bit
     * @since 1.0.0
     */
    public final static Boolean getBit(byte _byte, int bitPosition) {
        return (_byte & (1 << bitPosition)) != 0;
    }
}
