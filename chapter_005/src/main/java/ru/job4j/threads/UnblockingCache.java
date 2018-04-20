package ru.job4j.threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnblockingCache<T> {
    private Map<String, Model> container = new ConcurrentHashMap();

    public T getValue(String id) {
        return container.get(id).value;
    }

    public void add(String id, T value) {
        if (container.containsKey(id)) {
            update(id, value);
        } else {
            container.computeIfAbsent(id, (k)-> new Model(value));
        }
    }

    public boolean delete(String id) {
        boolean rslt = false;
        if (container.containsKey(id)) {
            container.remove(id);
            rslt = true;
        }
        return rslt;
    }

    public boolean update(String id, T value) {
        boolean rslt = false;
        if (container.computeIfPresent(id, (k, v) -> {
            int expVer = v.version;
            Model mod = new Model(value);
            mod.version = expVer + 1;
            if (expVer != v.version) {
                mod = null;
            }
            return mod;
        }) != null) {
            rslt = true;
        }
        return rslt;
    }

    private class Model {
        volatile int version = 0;
        T value;

        Model(T value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            boolean rslt = false;
            if (!obj.getClass().equals(this.getClass())) {
                Model model = (Model) obj;
                rslt = this.value.equals(model.value);
            }
            return rslt;
        }
    }
}
