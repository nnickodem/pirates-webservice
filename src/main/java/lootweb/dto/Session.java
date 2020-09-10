package lootweb.dto;

import java.util.Map;

public class Session {
    private String user;
    private Boss boss;
    private Map<String, Loot> lootMap;

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(final Boss boss) {
        this.boss = boss;
    }

    public Map<String, Loot> getLootMap() {
        return lootMap;
    }

    public void setLootMap(final Map<String, Loot> chestToLoot) {
        this.lootMap = chestToLoot;
    }

    @Override
    public String toString() {
        return "Session{" +
                "user='" + user + '\'' +
                ", boss=" + boss +
                ", lootMap=" + lootMap +
                '}';
    }
}
