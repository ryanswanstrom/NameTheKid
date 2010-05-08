/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author GooF
 */
@Entity
public class BabyName extends Model {
    @Required
    public String name;
    @Required
    public boolean girl;

    @OneToMany(mappedBy="babyName", fetch=FetchType.LAZY)
    List<Vote> votes;

    public BabyName(String name, boolean isGirl) {
        this.name = name;
        this.girl = isGirl;
        this.votes = new ArrayList<Vote>();
    }

    public BabyName addVote(String ipAddress) {
        Vote vote = new Vote(this, ipAddress).save();
        this.votes.add(vote);
        return this;
    }

    public long totalVotes() {
        long numberVotes = Vote.count("byBabyName", this);
        return numberVotes;
    }

}
