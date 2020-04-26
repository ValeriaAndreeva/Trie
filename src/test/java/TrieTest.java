import com.company.Trie;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class TrieTest {

    private Trie trie = new Trie();

    @Before
    public void add() {
        trie.add("кот");
        trie.add("ком");
        trie.add("комод");
        trie.add("язык");
        trie.add("язва");
        trie.add("яБлОКо");
    }

    @Test
    public void addAndFind() {
        Assert.assertFalse(trie.find("камод"));
        Assert.assertTrue(trie.find("кот"));
        Assert.assertTrue(trie.find("ком"));
        Assert.assertTrue(trie.find("комод"));
        Assert.assertTrue(trie.find("язык"));
        Assert.assertTrue(trie.find("язва"));
        Assert.assertTrue(trie.find("яБлОКо"));
        Assert.assertFalse(trie.find("кит"));
    }

    @Test
    public void delete() throws IllegalArgumentException {
        trie.delete("язык");
        Assert.assertTrue(trie.find("яБлОКо"));
        Assert.assertTrue(trie.find("язва"));
        Assert.assertFalse(trie.find("язык"));

        trie.delete("комод");
        Assert.assertTrue(trie.find("ком"));
        Assert.assertTrue(trie.find("кот"));
        Assert.assertFalse(trie.find("комод"));
    }

    @Test
    public void prefixFind() {
        ArrayList<String> arrLst = new ArrayList<String>();
        arrLst.add("кит");
        Assert.assertEquals(arrLst, trie.prefixFind("ки"));

        arrLst.clear();
        arrLst.add("кот");
        arrLst.add("ком");
        arrLst.add("комод");
        Assert.assertEquals(arrLst, trie.prefixFind("ко"));

        arrLst.clear();
        arrLst.add("язык");
        arrLst.add("язва");
        Assert.assertEquals(arrLst, trie.prefixFind("яз"));
    }
}