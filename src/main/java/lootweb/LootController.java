package lootweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lootweb.dao.LootDAO;
import lootweb.domain.Boss;
import lootweb.domain.Loot;
import lootweb.domain.LootId;
import lootweb.dto.Session;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LootController {

    private LootDAO lootDAO;

    private Logger logger = LoggerFactory.getLogger(LootController.class);

    public LootController(final LootDAO lootDAO) {
        this.lootDAO = lootDAO;
    }

    @RequestMapping(value = "/bosses")
    @ResponseBody
    public String getBosses() {
        logger.error("got bosses!");
        JSONObject json = new JSONObject();
        List<Boss> bosses = lootDAO.getBosses();
        json.put("bosses", bosses);
        return json.toString();
    }

    @RequestMapping(value = "/loots")
    @ResponseBody
    public String getLoots() {
        JSONObject json = new JSONObject();
        List<lootweb.domain.Loot> loots = lootDAO.getLoot(); //TODO Update
        json.put("loots", loots);
        return json.toString();
    }

    @PostMapping(value = "/loot")
    public ResponseEntity submitLoot(@RequestBody Session session) {
        List<Loot> loots = new ArrayList<>();
        Boss domainBoss = new Boss();
        domainBoss.setId(session.getBoss().getId());
        logger.error("session: " + session);
        for(Map.Entry<String, lootweb.dto.Loot> lootEntry : session.getLootMap().entrySet()) {
            lootweb.dto.Loot loot = lootEntry.getValue();
            logger.error("adding loot for chest type: " + lootEntry.getKey());
            logger.error("loot: " + loot);
            if(ObjectUtils.defaultIfNull(loot.getKillCount(), 0) > 0) {
                Loot domainLoot = new Loot();
                domainLoot.setLootId(new LootId(lootEntry.getKey()));
                domainLoot.setUser(session.getUser());
                domainLoot.setBoss(domainBoss);
                domainLoot.setKillCount(loot.getKillCount());
                domainLoot.setCrudeCount(ObjectUtils.defaultIfNull(loot.getCrudeCount(), 0));
                domainLoot.setCommonCount(ObjectUtils.defaultIfNull(loot.getCommonCount(), 0));
                domainLoot.setRareCount(ObjectUtils.defaultIfNull(loot.getRareCount(), 0));
                domainLoot.setFamedCount(ObjectUtils.defaultIfNull(loot.getFamedCount(), 0));
                domainLoot.setLegendaryCount(ObjectUtils.defaultIfNull(loot.getLegendaryCount(), 0));
                loots.add(domainLoot);
            }
        }

        lootDAO.addLoot(loots);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/lootPage")
    public String lootPage(final Model model) {
        return "loot";
    }

    @GetMapping("/bossLoot")
    @ResponseBody
    public String getLootForBoss(@RequestParam final String bossName) {
        JSONObject result = new JSONObject();
        String[] rarities = {"Crude", "Common", "Rare", "Famed", "Legendary"};
        result.put("rarities", rarities);
        List<lootweb.domain.Loot> loots;
        if(StringUtils.isNotBlank(bossName)) {
             loots = lootDAO.getLootByBossName(bossName);
        } else {
            loots = lootDAO.getLoot();
        }
        Integer crudeCount = loots.stream().map(lootweb.domain.Loot::getCrudeCount).reduce(0, Integer::sum);
        Integer commonCount = loots.stream().map(lootweb.domain.Loot::getCommonCount).reduce(0, Integer::sum);
        Integer rareCount = loots.stream().map(lootweb.domain.Loot::getRareCount).reduce(0, Integer::sum);
        Integer famedCount = loots.stream().map(lootweb.domain.Loot::getFamedCount).reduce(0, Integer::sum);
        Integer legendaryCount = loots.stream().map(lootweb.domain.Loot::getLegendaryCount).reduce(0, Integer::sum);
        Integer[] counts = {crudeCount, commonCount, rareCount, famedCount, legendaryCount};
        result.put("counts", counts);
        return result.toString();
    }
}
