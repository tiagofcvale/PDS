package Aula10.X1;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC;

    @SuppressWarnings("unchecked")

    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null)
            return false;
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if (nElem>=dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem );
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for (int i = 0; i < nElem; i++) {
            if (vec[i].equals(elem)) {
                if (nElem-i-1 > 0) // not last element
                    System.arraycopy(vec, i+1, vec, i, nElem-i-1 );
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    // Retorna um Iterator simples
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            public boolean hasNext() {
                return cursor < nElem;
            }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return vec[cursor++];
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Retorna um ListIterator começando no início
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    // Retorna um ListIterator começando no índice especificado
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > nElem)
            throw new IndexOutOfBoundsException();
        return new ListIterator<T>() {
            private int cursor = index;
            public boolean hasNext() {
                return cursor < nElem;
            }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return vec[cursor++];
            }
            public boolean hasPrevious() {
                return cursor > 0;
            }
            public T previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                return vec[--cursor];
            }
            public int nextIndex() {
                return cursor;
            }
            public int previousIndex() {
                return cursor - 1;
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            public void set(T e) {
                throw new UnsupportedOperationException();
            }
            public void add(T e) {
                throw new UnsupportedOperationException();
            }
        };
    }
}