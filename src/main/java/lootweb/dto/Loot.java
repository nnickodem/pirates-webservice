package lootweb.dto;

public class Loot {

    private Integer killCount;
    private Integer crudeCount;
    private Integer commonCount;
    private Integer rareCount;
    private Integer famedCount;
    private Integer legendaryCount;

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

    @Override
    public String toString() {
        return "Loot{" +
                "killCount=" + killCount +
                ", crudeCount=" + crudeCount +
                ", commonCount=" + commonCount +
                ", rareCount=" + rareCount +
                ", famedCount=" + famedCount +
                ", legendaryCount=" + legendaryCount +
                '}';
    }
}
