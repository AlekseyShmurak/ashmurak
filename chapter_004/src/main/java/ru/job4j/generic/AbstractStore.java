package ru.job4j.generic;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public abstract class AbstractStore<T extends Base> implements Store {
    @GuardedBy("this")
    protected SimpleList<T> values;

    @Override
    public synchronized void add(Base model) {
        values.add((T) model);
    }

    @Override
    public synchronized boolean replace(String id, Base model) {
        values.set(values.getIndex((T) findById(id)), (T) model);
        return findById(id) != null;
    }

    @Override
    public synchronized boolean delete(String id) {
        boolean rslt = false;
        if (findById(id) != null) {
            values.delete(values.getIndex((T) findById(id)));
            rslt = true;
        }
        return  rslt;
    }

    @Override
    public synchronized Base findById(String id) {
        for (T value : values) {
            if (value != null && value.getId().equals(id)) {
                return value;
            }
        }
        return null;
    }
}
