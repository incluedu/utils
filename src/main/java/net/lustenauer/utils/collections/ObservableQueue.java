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

package net.lustenauer.utils.collections;

import javafx.collections.ObservableListBase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Found class @https://stackoverflow.com/questions/28449809/why-are-there-no-observablequeues-in-javafx/28468340
 *
 * @param <E>
 * @since Version 1.2.0
 */
public class ObservableQueue<E> extends ObservableListBase<E> implements Queue<E> {

    private final Queue<E> queue;


    /**
     * Creates an ObservableQueue backed by the supplied Queue.
     * Note that manipulations of the underlying queue will not result
     * in notification to listeners.
     *
     * @param queue
     * @since Version 1.2.0
     */
    public ObservableQueue(Queue<E> queue) {
        this.queue = queue;
    }

    /**
     * Creates an ObservableQueue backed by a LinkedList.
     */
    public ObservableQueue() {
        this(new LinkedList<>());
    }

    @Override
    public boolean offer(E e) {
        beginChange();
        boolean result = queue.offer(e);
        if (result) {
            nextAdd(queue.size() - 1, queue.size());
        }
        endChange();
        return result;
    }

    @Override
    public boolean add(E e) {
        beginChange();
        try {
            queue.add(e);
            nextAdd(queue.size() - 1, queue.size());
            return true;
        } finally {
            endChange();
        }
    }


    @Override
    public E remove() {
        beginChange();
        try {
            E e = queue.remove();
            nextRemove(0, e);
            return e;
        } finally {
            endChange();
        }
    }

    @Override
    public E poll() {
        beginChange();
        E e = queue.poll();
        if (e != null) {
            nextRemove(0, e);
        }
        endChange();
        return e;
    }

    @Override
    public E element() {
        return queue.element();
    }

    @Override
    public E peek() {
        return queue.peek();
    }

    @Override
    public E get(int index) {
        Iterator<E> iterator = queue.iterator();
        for (int i = 0; i < index; i++) iterator.next();
        return iterator.next();
    }

    @Override
    public int size() {
        return queue.size();
    }

}