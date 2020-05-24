package com.chenshop.btlui;

import com.chenshop.model.bean.Bank;
import com.chenshop.model.db.Table;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testBean() {
        Bank bank = new Bank();
        bank.setId(4);
        bank.setName("a name");
        bank.setDetails("some detail");
        System.out.println(bank);

        assertEquals(bank.getId(), new Integer(4));
        assertEquals(bank.getName(), "a name");
        assertEquals(bank.getDetails(), "some detail");

        assertEquals(bank.getValue("id"), 4);
        assertEquals(bank.getValue("name"), "a name");
        assertEquals(bank.getValue("details"), "some detail");
    }

    @Test
    public void testTable() {
        Table<Bank> bankTable = new Table<>(Bank.class);
        Bank bank = new Bank();
        bank.setId(4);
        bank.setName("a name");
        bank.setDetails("some detail");
        bankTable.add(bank);

        System.out.println(bankTable.get(null, null));
    }
}