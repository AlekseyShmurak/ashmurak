package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnagramTest {

    @Test
    public void isAnagramAr() {
        String str1 = "qwertasdfgq";
        String str2 = "gfdsaqwertq";
        Anagram anagram = new Anagram();
        assertThat(true, is(anagram.isAnagramAr(str1, str2)));
        assertThat(false, is(anagram.isAnagramAr(str1, "qweutasdfgq")));

    }

    @Test
    public void isAnagramSum() {
        String str1 = "qwertasdfgq";
        String str2 = "gfdsaqwertq";
        Anagram anagram = new Anagram();
        assertThat(true, is(anagram.isAnagramSum(str1, str2)));
        assertThat(false, is(anagram.isAnagramSum(str1, "qweutasdfgq")));
    }

    @Test
    public void isAnagramHash() {
        String str1 = "qwertasdfgq";
        String str2 = "gfdsaqwertq";
        Anagram anagram = new Anagram();
        assertThat(true, is(anagram.isAnagramHash(str1, str2)));
        assertThat(false, is(anagram.isAnagramHash(str1, "qweutasdfgq")));

    }
}