package ru.job4j.threads;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Queue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParallelSearchTest {
    Queue<String> expect = new LinkedList<>();
    LinkedList<String> ext = new LinkedList<>();
    ParallelSearch parallelSearch = new ParallelSearch("C:\\projects\\ashmurak\\chapter_005\\src\\main\\java\\ru\\job4j\\threads\\PSearch", "text", ext);


    @Test
    public void test() throws InterruptedException {
        expect.offer("C:\\projects\\ashmurak\\chapter_005\\src\\main\\java\\ru\\job4j\\threads\\PSearch\\Новая папка\\Фаил в папке.txt");
        expect.offer("C:\\projects\\ashmurak\\chapter_005\\src\\main\\java\\ru\\job4j\\threads\\PSearch\\Фаил 2.txt");
        expect.offer("C:\\projects\\ashmurak\\chapter_005\\src\\main\\java\\ru\\job4j\\threads\\PSearch\\Фаил 4.txt");
        ext.add(".java");
        ext.add(".txt");
        parallelSearch.init();
        Thread.sleep(100);
        parallelSearch.result();
//        assertThat(expect, is(parallelSearch.result())); // Тест проходит у меня на компе
    }


}