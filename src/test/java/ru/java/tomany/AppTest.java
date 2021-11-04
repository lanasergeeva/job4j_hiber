package ru.java.tomany;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AppTest {
    @Test
    public void whenCount() {
        App a = new App();
        assertThat(a.count(2, 3), is(5));
    }
}