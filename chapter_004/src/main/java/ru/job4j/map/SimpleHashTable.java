package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleHashTable<K, V> {
    private Object[] container = new Object[16];
    private int capacity = 16;
    private int size = 0;
    private int modCount = 0;

    public V get(K key) {
        Cell target = getCell(key);
        return target != null ? target.value : null;
    }

    public boolean containsKey(K key) {
        return getCell(key) != null;
    }

    private Cell getCell(K key) {
        Cell rslt = null;
        int index = key.hashCode() & (capacity - 1);
        if (container[index] != null) {
            rslt = (Cell) container[index];
            while (rslt != null) {
                if (rslt.key.equals(key)) {
                    break;
                } else {
                    rslt = rslt.next;
                }
            }
        }
        return rslt;
    }

    public boolean insert(K key, V value) {
        if (size > capacity * 0.75) {
            enlargeCont();
        }
        return insertCell(new Cell(key, value));
    }

    private boolean insertCell(Cell inputCell) {
        boolean rslt = false;
        int index = inputCell.hash & (capacity - 1);
        if (container[index] == null) {
            container[index] = inputCell;
            size++;
            rslt = true;
        } else {
            Cell last = (Cell) container[index];
            while (last.next != null) {
                if (last.key.equals(inputCell.key)) {
                    last = null;
                    break;
                } else {
                    last = last.next;
                }
            }
            if (last != null && !last.key.equals(inputCell.key)) {
                last.next = inputCell;
                size++;
                rslt = true;
            }
        }
        if (rslt) {
            modCount++;
        }
        return rslt;
    }

    public boolean delete(K key) {
        boolean rslt = false;
        int index = key.hashCode() & (capacity - 1);
        Cell previous = (Cell) container[index];
        Cell target = getCell(key);
        if (target != null) {
            rslt = true;
            size--;
            Cell chain = target.next;
            if (previous == target) {
                container[index] = null;
            } else {
                while (previous.next != target) {
                    previous = previous.next;
                }
                previous.next = null;
            }
            if (chain != null) {
                size--;
                insertCell(chain);
            }
        }
        if (rslt) {
            modCount++;
        }
        return rslt;
    }

    public int getSize() {
        return size;
    }

    public Iterator getKeyIterator() {
        return new KeyIterator(container, size, modCount);
    }

    private void enlargeCont() {
        size = 0;
        Object[] oldCont = container;
        capacity *= 2;
        container = new Object[capacity];
        for (Object cell : oldCont) {
            if (cell != null) {
                insertCell((Cell) cell);
            }
        }
    }

    private class KeyIterator implements Iterator {
            Cell nextCell;
            int size;
            int index = 0;
            int entredMod;
            Object[] cells;

            KeyIterator(Object[] cells, int size, int entredMod) {
                this.cells = cells; this.size = size; this.entredMod = entredMod;
                if (size > 0) {
                    for (int i = 0; i < cells.length; i++) {
                        if (cells[i] != null) {
                            nextCell = (Cell) cells[i];
                            index = i;
                            break;

                        }
                    }
                }
            }

            @Override
            public boolean hasNext() {
                if (entredMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nextCell != null;
            }

            @Override
            public K next() {
                K rslt = null;
                if (hasNext()) {
                    rslt = nextCell.key;
                    nextCell = nextCell.next;
                    if (nextCell == null) {
                        for (int i = index + 1; i < cells.length; i++) {
                            if (cells[i] != null) {
                                nextCell = (Cell) cells[i];
                                index = i;
                                break;
                            }
                        }
                    }
                }
                return rslt;
            }
        }


    private class Cell {
        K key;
        V value;
        Cell next;
        int hash;

        Cell(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = this.key.hashCode();
        }
    }
}
