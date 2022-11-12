package com.chigirh.eh.rem.infra.repository;

import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.model.RealEstateSearchCondition;
import com.chigirh.eh.rem.domain.repository.RealEstateRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class RealEstateRepositoryImpl implements RealEstateRepository {

    private Map<String, RealEstate> store = new HashMap<>();

    @Override
    public void create(RealEstate model) {
        store.put(model.getReId(), model);
    }

    @Override
    public int update(RealEstate model) {
        store.put(model.getReId(), model);
        return 1;
    }

    @Override
    public List<String> fetchRegisterAreas() {
        return List.of("吉塚", "箱崎");
    }

    @Override
    public List<RealEstate> fetchByCondition(RealEstateSearchCondition condition) {
        var ret = new ArrayList<>(store.values());
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        ret.add(
            new RealEstate(UUID.randomUUID().toString(), "〇×マンション", "マルバツマンション", List.of("吉塚"), "xxx", 50000, "", "", "1", "", "", LocalDateTime.now())
        );
        return ret;
    }

    @Override
    public RealEstate fetchByReId(String reId) {
        return store.get(reId);
    }
}
