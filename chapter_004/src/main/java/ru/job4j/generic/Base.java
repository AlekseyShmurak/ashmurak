package ru.job4j.generic;

public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        boolean rslt = false;
        if (object instanceof Base) {
            rslt =  this.id.equals(((Base) object).id) ? true : false;
        }
        return rslt;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getId() {
        return id;
    }
}
