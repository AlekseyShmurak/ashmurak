package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnagramTest {
    String str1 = "qwertasdfgq";
    String str2 = "gfdsaqwertq";
    Anagram anagram = new Anagram();

    @Test
    public void isAnagramTrue() {
        assertThat(true, is(anagram.isAnagram(str1, str2)));
        assertThat(false, is(anagram.isAnagram(str1, "qweutasdfgq")));

    }

    @Test
    public void isAnagramSum() {
        assertThat(true, is(anagram.isAnagramSum(str1, str2)));
        assertThat(false, is(anagram.isAnagramSum(str1, "qweutasdfgq")));
    }
}