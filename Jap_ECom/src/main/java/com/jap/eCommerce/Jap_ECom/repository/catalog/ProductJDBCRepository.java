package com.jap.eCommerce.Jap_ECom.repository.catalog;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;
import com.jap.eCommerce.Jap_ECom.model.dto.catalog.ProductDTO;
import com.jap.eCommerce.Jap_ECom.model.dto.catalog.ProductFilterRQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductJDBCRepository {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ProductDTO> searchCatalog(ProductFilterRQ filterRQ) {
        final List<ProductDTO> results = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        StringBuilder SQL = new StringBuilder();

        SQL.append("SELECT p.product_id, \n");
        SQL.append("       p.code, \n");
        SQL.append("       p.description, \n");
        SQL.append("       p.is_enabled, \n");
        SQL.append("       p.name, \n");
        SQL.append("       p.price \n");
        SQL.append("FROM product p \n");
        SQL.append("    WHERE p.product_id IS NOT NULL \n");

        if (StringUtils.isNotEmpty(filterRQ.getCode())) {
            SQL.append("  AND UPPER(p.code) LIKE '%" + filterRQ.getCode().toUpperCase() + "%' \n");
        }
        if (StringUtils.isNotEmpty(filterRQ.getName())) {
            SQL.append("  AND UPPER(p.name) LIKE '%" + filterRQ.getName().toUpperCase() + "%' \n");
        }
        if (filterRQ.getIsEnabled() != null) {
            SQL.append("  AND p.is_enabled = :isEnabled \n");
            params.put("isEnabled", filterRQ.getIsEnabled().toString());
        }

        SQL.append("ORDER BY p.code ");

        return namedParameterJdbcTemplate.query(SQL.toString(), params, new ResultSetExtractor<List<ProductDTO>>() {
            @Override
            public List<ProductDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    ProductDTO productDTO = new ProductDTO();

                    productDTO.setProductID(rs.getLong("product_id"));
                    productDTO.setCode(rs.getString("code"));
                    productDTO.setName(rs.getString("name"));
                    productDTO.setDescription(rs.getString("description"));
                    productDTO.setPrice(rs.getDouble("price"));
                    productDTO.setIsEnabled(AppsConstants.YesNo.resolver(rs.getString("is_enabled")));

                    results.add(productDTO);
                }
                return results;
            }
        });
    }
}
