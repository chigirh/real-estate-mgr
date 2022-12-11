package com.chigirh.eh.rem.infra.repository;

import com.chigirh.eh.rem.domain.common.AreasConst;
import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.model.realestate.RealEstateSearchCondition;
import com.chigirh.eh.rem.domain.model.realestate.RealEstateSearchResult;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import com.chigirh.eh.rem.infra.config.DataAccess;
import com.chigirh.eh.rem.infra.entity.RealEstateAreaEntity;
import com.chigirh.eh.rem.infra.entity.RealEstateEntity;
import com.chigirh.eh.rem.infra.mapper.RealEstateAreaMapper;
import com.chigirh.eh.rem.infra.mapper.RealEstateMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

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
    public RealEstateSearchResult fetchByCondition(RealEstateSearchCondition condition) {
        var conditionEntity = new RealEstateMapper.Condition();
        conditionEntity.setReName(StringUtils.isEmpty(condition.getReName()) ? null : condition.getReName() + "%");

        var area = AreasConst.DEFAULT.equals(condition.getArea()) ? null : condition.getArea();
        conditionEntity.setArea(area);

        conditionEntity.setRentPrice(condition.getRentPrice());
        conditionEntity.setForeignerLiveSts(condition.getForeignerLiveSts());

        conditionEntity.setOffset(condition.getOffset());
        conditionEntity.setLimit(condition.getSize());

        var results = realEstateMapper.findByCondition(conditionEntity);
        var total = realEstateMapper.countByCondition(conditionEntity);

        var model = new RealEstateSearchResult();
        model.setRecord(results.stream().map(this::toModel).collect(Collectors.toList()));
        model.setTotal(total);
        return model;
    }

    @Override
    @DataAccess(process = "real_estate find.")
    public RealEstate fetchByReId(String reId) {
        var entity = realEstateMapper.findByKey(reId);
        if (entity == null) {
            return null;
        }
        var model = toModel(entity);

        // area
        var areaEntities = realEstateAreaMapper.findByReId(reId);
        var areas = areaEntities.stream().map(RealEstateAreaEntity::getArea).collect(Collectors.toList());
        model.setAreas(areas);

        return model;
    }

    @Override
    @DataAccess(process = "real_estate delete.")
    public int deleteByReId(String reId) {
        return realEstateMapper.deleteByKey(reId);
    }

    private RealEstateAreaEntity toEntity(String reId, String area) {
        return new RealEstateAreaEntity(reId, area);
    }

    private RealEstateEntity toEntity(RealEstate model) {
        var entity = new RealEstateEntity();
        entity.setReId(model.getReId());
        entity.setReName(model.getReName());
        entity.setReNameKana(model.getReNameKana());
        if (StringUtils.isEmpty(model.getReNameKana())) {
            entity.setInitial("");
        } else {
            entity.setInitial(model.getReNameKana().substring(0, 1));
        }
        entity.setAddress(model.getAddress());
        entity.setRentPrice(model.getRentPrice());
        entity.setCondoFee(model.getCondoFee());
        entity.setWaterFee(model.getWaterFee());
        entity.setOtherFee(model.getOtherFee());
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
        model.setCondoFee(entity.getCondoFee());
        model.setWaterFee(entity.getWaterFee());
        model.setOtherFee(entity.getOtherFee());
        model.setMgrCompanyName(entity.getMgrCompanyName());
        model.setMgrCompanyTel(entity.getMgrCompanyTel());
        model.setForeignerLiveSts(entity.getForeignerLiveSts());
        model.setNote(entity.getNote());
        model.setPdf(entity.getPdf());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }
}
