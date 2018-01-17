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

package net.lustenauer.common;

/**
 * couple off stats for common use
 * <ul>
 * <li>Status.NONE</li>
 * <li>Status.OK</li>
 * <li>Status.FAULT</li>
 * <li>Status.WARN</li>
 * <li>Status.INFO</li>
 * </ul>
 *
 * @since 1.2.0
 */
public enum Status {
    NONE,
    OK,
    FAULT,
    ERROR,
    WARN,
    INFO,
    ANDROID,
    PROGRESS;

    private boolean active;

    /**
     * Empty Constructor
     */
    Status() {
    }

    /**
     * Constructor with active flag for free use
     *
     * @param active TRUE or FALSE
     */
    Status(boolean active) {
        this.active = active;
    }

    /**
     * @return current state off the active flag, this flag is for free use
     */
    boolean isActive() {
        return active;
    }
}