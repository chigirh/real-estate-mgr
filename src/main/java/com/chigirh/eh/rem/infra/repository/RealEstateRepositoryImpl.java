package com.chigirh.eh.rem.infra.repository;

import com.chigirh.eh.rem.domain.common.AreasConst;
import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.model.RealEstateSearchCondition;
import com.chigirh.eh.rem.domain.repository.RealEstateRepository;
import com.chigirh.eh.rem.infra.config.DataAccess;
import com.chigirh.eh.rem.infra.entity.RealEstateAreaEntity;
import com.chigirh.eh.rem.infra.entity.RealEstateEntity;
import com.chigirh.eh.rem.infra.mapper.RealEstateAreaMapper;
import com.chigirh.eh.rem.infra.mapper.RealEstateMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class RealEstateRepositoryImpl implements RealEstateRepository {

    private final RealEstateMapper realEstateMapper;
    private final RealEstateAreaMapper realEstateAreaMapper;

    @Override
    @DataAccess(process = "real_estate insert.")
    public void create(RealEstate model) {
        var entity = toEntity(model);
        realEstateMapper.insert(entity);

        // area
        final String reId = model.getReId();
        var areaEntities = model.getAreas()
            .stream()
            .map(e -> toEntity(reId, e))
            .collect(Collectors.toList());

        realEstateAreaMapper.bulkInsert(areaEntities);

    }

    @Override
    @DataAccess(process = "real_estate update.")
    public int update(RealEstate model) {
        var entity = toEntity(model);
        var count = realEstateMapper.updateByKey(entity);

        if (count == 0) {
            return 0;
        }

        // area
        final String reId = model.getReId();
        var areaEntities = model.getAreas()
            .stream()
            .map(e -> toEntity(reId, e))
            .collect(Collectors.toList());

        realEstateAreaMapper.deleteByReId(reId);
        realEstateAreaMapper.bulkInsert(areaEntities);

        return 1;
    }

    @Override
    @DataAccess(process = "real_estate_area find.")
    public List<String> fetchRegisterAreas() {
        return realEstateAreaMapper.findAllAreas();
    }

    @Override
    @DataAccess(process = "real_estate find.")
    public List<RealEstate> fetchByCondition(RealEstateSearchCondition condition) {
        var conditionEntity = new RealEstateMapper.Condition();
        conditionEntity.setReName(StringUtils.isEmpty(condition.getReName()) ? null : condition.getReName() + "%");

        var area = AreasConst.DEFAULT.equals(condition.getArea()) ? null : condition.getArea();
        conditionEntity.setArea(area);

        conditionEntity.setRentPrice(condition.getRentPrice());
        conditionEntity.setForeignerLiveSts(condition.getForeignerLiveSts());

        var results = realEstateMapper.findByCondition(conditionEntity);

        return results.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    @DataAccess(process = "real_estate find.")
    public RealEstate fetchByReId(String reId) {
        var entity = realEstateMapper.findByKey(reId);
        var model = toModel(entity);

        // area
        var areaEntities = realEstateAreaMapper.findByReId(reId);
        var areas = areaEntities.stream().map(RealEstateAreaEntity::getArea).collect(Collectors.toList());
        model.setAreas(areas);

        return model;
    }

    private RealEstateAreaEntity toEntity(String reId, String area) {
        return new RealEstateAreaEntity(reId, area);
    }

    private RealEstateEntity toEntity(RealEstate model) {
        var entity = new RealEstateEntity();
        entity.setReId(model.getReId());
        entity.setReName(model.getReName());
        entity.setReNameKana(model.getReNameKana());
        entity.setInitial(model.getReNameKana().substring(0, 1));
        entity.setAddress(model.getAddress());
        entity.setRentPrice(model.getRentPrice());
        entity.setMgrCompanyName(model.getMgrCompanyName());
        entity.setMgrCompanyTel(model.getMgrCompanyTel());
        entity.setForeignerLiveSts(model.getForeignerLiveSts());
        entity.setNote(model.getNote());
        entity.setPdf(model.getPdf());
        entity.setUpdatedAt(model.getUpdatedAt());
        return entity;
    }

    private RealEstate toModel(RealEstateEntity entity) {
        var model = new RealEstate();
        model.setReId(entity.getReId());
        model.setReName(entity.getReName());
        model.setReNameKana(entity.getReNameKana());
        model.setAddress(entity.getAddress());
        model.setRentPrice(entity.getRentPrice());
        model.setMgrCompanyName(entity.getMgrCompanyName());
        model.setMgrCompanyTel(entity.getMgrCompanyTel());
        model.setForeignerLiveSts(entity.getForeignerLiveSts());
        model.setNote(entity.getNote());
        model.setPdf(entity.getPdf());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }
}
