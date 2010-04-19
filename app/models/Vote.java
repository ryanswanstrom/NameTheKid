/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Date;
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
    
}
