import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.util.ArrayList;

class TrieTest {

    private com.company.Trie trie = new com.company.Trie();

    @Test
    void find() {
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
    void prefixFind() {
    }

    @Before
    void add() {
        trie.add("кот");
        trie.add("ком");
        trie.add("комод");
        trie.add("язык");
        trie.add("язва");
        trie.add("яБлОКо");
    }
}