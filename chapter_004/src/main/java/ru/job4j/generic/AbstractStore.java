package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store {
    protected SimpleList<T> values;

    @Override
    public void add(Base model) {
        values.add((T) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        values.set(values.getIndex((T) findById(id)), (T) model);
        return findById(id) != null;
    }

    @Override
    public boolean delete(String id) {
        boolean rslt = false;
        if (findById(id) != null) {
            values.delete(values.getIndex((T) findById(id)));
            rslt = true;
        }
        return  rslt;
    }

    @Override
    public Base findById(String id) {
        for (T value : values) {
            if (value != null && value.getId().equals(id)) {
                return value;
            }
        }
        return null;
    }
}
