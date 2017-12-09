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

package net.lustenauer.utils.collections;

import java.util.*;

/**
 * @author Patric Hollenstein
 * @version 1.1.0
 * @since 1.0.0
 */
public class COL {

    /**
     * Return the toString from all values off the collection as a list of String
     *
     * @param col collection with the data
     * @param <E> the type of the collection
     * @return a list with all toString values from the collection
     * @version 1.0.0
     * @since 1.0.0
     */
    public static <E> List<String> toStringList(Collection<E> col) {
        List<String> list = new ArrayList<>();
        for (E element : col) {
            list.add(element.toString());
        }
        return list;
    }

    /**
     * Returns a {@link Collection} of obsolete elements. This mean you get a collection (Set, List, ...) witch contains all
     * the obsolet elements from with was in the original list but not in the updated list.
     *
     * @param original Original collection with all elements
     * @param updated  Updated collection may have les elements
     * @param <E>      Type of the collection
     * @return Collection with contains all elements included in orignal list but not in updated list
     * @version 1.0.0
     * @since 1.0.0
     */
    public static <E> Collection<E> findObsoleteElements(Collection<E> original, Collection<E> updated) {
        Collection<E> obsoleteList = new ArrayList<>();
        boolean found;

        for (E e1 : original) {
            found = false;
            for (E e2 : updated) {
                if (e1.equals(e2)) {
                    found = true;
                    break;
                }
            }
            if (!found) obsoleteList.add(e1);
        }
        return obsoleteList;
    }

    /**
     * Returns a {@link List} of obsolete elements. More information you can find at
     * {@link #findObsoleteElements(Collection, Collection)}
     *
     * @param original Original collection with all elements
     * @param updated  Updated collection may have les elements
     * @param <E>      Type of the collection
     * @return Collection with contains all elements included in orignal list but not in updated list
     * @version 1.1.0
     * @see #findObsoleteElements(Collection, Collection)
     * @since 1.1.0
     */
    public static <E> List<E> findObsoleteElementsList(Collection<E> original, Collection<E> updated) {
        return new ArrayList<E>(findObsoleteElements(original, updated));
    }

    /**
     * Returns a {@link Set} of obsolete elements. More information you can find at
     * {@link #findObsoleteElements(Collection, Collection)}
     *
     * @param original Original collection with all elements
     * @param updated  Updated collection may have les elements
     * @param <E>      Type of the collection
     * @return Collection with contains all elements included in orignal list but not in updated list
     * @version 1.1.0
     * @see #findObsoleteElements(Collection, Collection)
     * @since 1.1.0
     */
    public static <E> Set<E> findObsoleteElementsSet(Collection<E> original, Collection<E> updated) {
        return new HashSet<E>(findObsoleteElements(original, updated));
    }

}
