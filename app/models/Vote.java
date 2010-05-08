/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Date;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @author GooF
 */
@Entity
public class Vote extends Model {
    Date dateAdded = new Date();
    String ipAddress;
    @ManyToOne
    BabyName babyName;

    public Vote(BabyName bName, String ip) {
        this.babyName = bName;
        this.ipAddress = ip;
    }

    public static void addRandom(int num, int commitNum, String ip) {
        Random r = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < num; i++) {
            if (i % commitNum == 0) {
                Vote.commit();
            }
            BabyName baby = BabyName.findById(Long.valueOf(r.nextInt(10) + 1));
            baby.addVote(ip);
        }
        Vote.commit();
        long end = System.nanoTime();
    }

    private static void commit() {
        Vote.em().getTransaction().commit();
        Vote.em().getTransaction().begin();
        Vote.em().flush();
        Vote.em().clear();
    }
    
}
