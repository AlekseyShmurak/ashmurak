package ru.job4j.threads;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UnblockingCacheTest {

    @Test
    public void addTest() {
        UnblockingCache<String> cache = new UnblockingCache();
        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.add("qwer", "1");
                cache.add("asdf", "2");
            }
        });
        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.add("zxcv", "3");
                cache.add("asdf", "4");
            }
        });
        firstThread.start();
        secondThread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.getValue("asdf"));
        assertThat(cache.getValue("zxcv"), is("3"));
        assertThat(cache.getValue("qwer"), is("1"));
    }

    @Test
    public void deleteTest() {
        UnblockingCache<String> cache = new UnblockingCache();
        cache.add("qwer", "1");
        assertThat(cache.getValue("qwer"), is("1"));
        assertThat(cache.delete("qwer"), is(true));
        assertThat(cache.delete("qwer"), is(false));
    }
}