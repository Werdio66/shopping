package com._520.webhomework.commodity.dao;

import com._520.webhomework.commodity.domain.Commodity;
import java.util.List;

public interface ICommodityDAO {

    /**
     *  增加
     * @param commodity
     */
    void save(Commodity commodity);

    void delete(Long id);

    void update(Commodity commodity, Long id);

    Commodity get(Long id);

    List<Commodity> listAll() throws Exception;

    Commodity getByName(String name);
}
