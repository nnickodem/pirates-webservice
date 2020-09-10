package lootweb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class LootId implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//TODO: change to sequence
    @Column(name = "session_id", length = 11, nullable = false, unique = true)
    private Integer sessionId;

    @Column(name = "chest_type", length = 15, nullable = false)
    private String chestType;

    public LootId() {}

    public LootId(final String chestType) {
        this.chestType = chestType;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(final Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getChestType() {
        return chestType;
    }

    public void setChestType(final String chestType) {
        this.chestType = chestType;
    }
}
