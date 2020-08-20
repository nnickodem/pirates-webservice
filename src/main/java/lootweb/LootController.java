package lootweb;

import java.util.List;
import lootweb.dao.LootDAO;
import lootweb.domain.Boss;
import lootweb.dto.Loot;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LootController {

    @RequestMapping(value = "/bosses")
    @ResponseBody
    public String getBosses() {
        JSONObject json = new JSONObject();
        List<Boss> bosses = LootDAO.getBosses();
        json.put("bosses", bosses);
        return json.toString();
    }

    @RequestMapping(value = "/loots")
    @ResponseBody
    public String getLoots() {
        JSONObject json = new JSONObject();
        List<lootweb.domain.Loot> loots = LootDAO.getLoot(); //TODO Update
        json.put("loots", loots);
        return json.toString();
    }

    @PostMapping(value = "/loot")
    public ResponseEntity submitLoot(
            @RequestBody Loot loot) {
        lootweb.domain.Loot domainLoot = new lootweb.domain.Loot();
        Boss domainBoss = new Boss();
        domainBoss.setId(loot.getBossId());
        domainLoot.setUser(loot.getUser());
        domainLoot.setBoss(domainBoss);
        domainLoot.setKillCount(loot.getKillCount());
        domainLoot.setCrudeCount(loot.getCrudeCount());
        domainLoot.setCommonCount(loot.getCommonCount());
        domainLoot.setRareCount(loot.getRareCount());
        domainLoot.setFamedCount(loot.getFamedCount());
        domainLoot.setLegendaryCount(loot.getLegendaryCount());
        LootDAO.addLoot(domainLoot);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
