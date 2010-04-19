import java.util.List;
import models.BabyName;
import models.Vote;
import org.junit.*;
import play.test.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }
    
    @Test
    public void babyTest() {
        BabyName boy = new BabyName("bob", false).save();
        final String ip = "112.223.098.09";
        boy.addVote(ip);
        boy.addVote(ip);
        Assert.assertEquals(2, boy.totalVotes());

        BabyName girl = new BabyName("sally", true).save();
        girl.addVote(ip);
        girl.addVote(ip);
        girl.addVote(ip);
        Assert.assertEquals(3, girl.totalVotes());

        Assert.assertEquals(2, BabyName.count());
    }

    @Test
    public void boysGirlsTest() {
        new BabyName("bob", false).save();
        new BabyName("bob2", false).save();
        new BabyName("bob3", false).save();
        new BabyName("bob4", false).save();

        BabyName result = BabyName.find("byName", "bob3").first();

        Assert.assertNotNull(result);
        Assert.assertFalse(result.girl);

        new BabyName("sally", true).save();
        new BabyName("sally2", true).save();
        new BabyName("sally3", true).save();
        new BabyName("sally4", true).save();
        new BabyName("sally5", true).save();

        result = BabyName.find("byName", "sally5").first();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.girl);

        int girls = BabyName.find("byGirl", true).fetch().size();
        Assert.assertEquals(5, girls);

        int boys = BabyName.find("byGirl", false).fetch().size();
        Assert.assertEquals(4, boys);

        List<BabyName> boyNames = BabyName.find("byGirl", false).fetch();
        Assert.assertEquals(4, boyNames.size());

        List<BabyName> girlNames = BabyName.find("byGirl", true).fetch();
        Assert.assertEquals(5, girlNames.size());
    }

    @Test
    public void moreTest() {
        BabyName bob = new BabyName("bob", false).save();
        bob.addVote("283.22.33.11");
        bob.addVote("223.55.66.221");

        int bobVotes = Vote.find("byBabyName", bob).fetch().size();
        Assert.assertEquals(2, bobVotes);
        int boyVotes = Vote.find("byBabyName.girl", false).fetch().size();
        Assert.assertEquals(2, boyVotes);
        int girlVotes = Vote.find("byBabyName.girl", true).fetch().size();
        Assert.assertEquals(0, girlVotes);
    }
}
