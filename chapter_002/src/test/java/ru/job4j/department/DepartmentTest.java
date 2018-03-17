package ru.job4j.department;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentTest {

    @Test
    public void sortByAscendingTest() {
        Department department = new Department();
        String[] departments = {"K1\\SK1", "K2\\SK1\\SSK2", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1"};
        String[] expect = {"K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2","K1\\SK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertThat(expect, is(department.sortByAscending(departments)));
    }

    @Test
    public void sortByDescendingTest() {
        Department department = new Department();
        String[] departments = {"K1\\SK1", "K2\\SK1\\SSK2", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1"};
        String[] expect = {"K2", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        assertThat(expect, is(department.sortByDescending(departments)));
    }
}