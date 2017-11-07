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

import static org.junit.Assert.assertEquals;

public class BitTest {
    @org.junit.Test
    public void setBit() throws Exception {
        byte b1 = 1;
        byte b2;

        b2 = Bit.setBit(b1,3,true);
        assertEquals(9,b2);


        b2 = Bit.setBit(b2,0,false);
        assertEquals(8,b2);


    }

    @org.junit.Test
    public void getBit() throws Exception {

        byte b1 = 1;
        byte b2 = 2;

        assertEquals(Bit.getBit(b1,0),true);
        assertEquals(Bit.getBit(b1,1),false);

        assertEquals(Bit.getBit(b2,0),false);
        assertEquals(Bit.getBit(b2,1),true);


    }

}