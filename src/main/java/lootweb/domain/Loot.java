package lootweb.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loots")
public class Loot {

    @EmbeddedId
    private LootId lootId;

    @Column(name = "user", length = 20, nullable = false)
    private String user;

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Boss boss;

    @Column(name = "kill_count", length = 11, nullable = false)
    private Integer killCount;

    @Column(name = "crude_count", length = 11, nullable = false)
    private Integer crudeCount;

    @Column(name = "common_count", length = 11, nullable = false)
    private Integer commonCount;

    @Column(name = "rare_count", length = 11, nullable = false)
    private Integer rareCount;

    @Column(name = "famed_count", length = 11, nullable = false)
    private Integer famedCount;

    @Column(name = "legendary_count", length = 11, nullable = false)
    private Integer legendaryCount;

    public LootId getLootId() {
        return lootId;
    }

    public void setLootId(final LootId lootId) {
        this.lootId = lootId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public void setKillCount(Integer killCount) {
        this.killCount = killCount;
    }

    public Integer getCrudeCount() {
        return crudeCount;
    }

    public void setCrudeCount(Integer crudeCount) {
        this.crudeCount = crudeCount;
    }

    public Integer getCommonCount() {
        return commonCount;
    }

    public void setCommonCount(Integer commonCount) {
        this.commonCount = commonCount;
    }

    public Integer getRareCount() {
        return rareCount;
    }

    public void setRareCount(Integer rareCount) {
        this.rareCount = rareCount;
    }

    public Integer getFamedCount() {
        return famedCount;
    }

    public void setFamedCount(Integer famedCount) {
        this.famedCount = famedCount;
    }

    public Integer getLegendaryCount() {
        return legendaryCount;
    }

    public void setLegendaryCount(Integer legendaryCount) {
        this.legendaryCount = legendaryCount;
    }
}
