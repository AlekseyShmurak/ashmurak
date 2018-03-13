package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class StupidBotTest {

    @Test
    public void sayKu() {
        String expectedAns = "ku,bro";
        StupidBot testBot = new StupidBot();
        Assert.assertThat(testBot.answer("Ku"), is(expectedAns));
    }

    @Test
    public void sayChao() {
        String expectedAns = "See you";
        StupidBot testBot = new StupidBot();
        Assert.assertThat(testBot.answer("Chao"), is(expectedAns));
    }

    @Test
    public void sayBlaBlaBla() {
        String expectedAns = "fuck off";
        StupidBot testBot = new StupidBot();
        Assert.assertThat(testBot.answer("Bla Bla"), is(expectedAns));
    }
}