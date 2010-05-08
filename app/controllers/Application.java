package controllers;

import java.util.List;
import models.BabyName;
import models.Vote;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        List<BabyName> boys = BabyName.find("byGirl", false).fetch();
        List<BabyName> girls = BabyName.find("byGirl", true).fetch();
        render(boys, girls); // send boys and girls
    }

    public static void addVote(long babyId) {
        BabyName baby = BabyName.findById(babyId);
        baby.addVote(request.remoteAddress);
        results();
    }

    public static void results() {
        List<BabyName> boys = BabyName.find("byGirl", false).fetch();
        List<BabyName> girls = BabyName.find("byGirl", true).fetch();
        render(boys, girls);
    }

    public static void about() {
        render();
    }

    // below here added for testing with lots of data
    public static void load() {
        render();
    }

    public static void addRandom(int num, int commitNum) {
//        Vote.addRandom(num, commitNum, request.remoteAddress);
        results();
    }
     
}
