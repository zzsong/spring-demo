package com.zss.transaction.dao;

import com.alibaba.fastjson.JSON;
import com.zss.transaction.domain.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateMoney(Wallet wallet, boolean ex){
        StringBuffer buffer = new StringBuffer();
        buffer.append("update wallet set ");
        buffer.append("money=").append(wallet.getMoney());
        buffer.append(" where id = ").append(wallet.getId());
        int effect = jdbcTemplate.update(buffer.toString());
        System.out.println("execute effect:\t"+effect);
        if (ex){
            throw new RuntimeException("throw exception : "+wallet.getAccount());
        }
        return effect;
    }

    public Wallet query(long id){
        String sql = "select * from wallet where id = "+id;
        return jdbcTemplate.queryForObject(sql, new WalletRowMapper());
    }
    public Wallet queryById(long id){
        String sql = "select * from wallet where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new WalletRowMapper());
    }

    /**
     * jdbcTemplate.queryForObject(sql, Wallet.class);
     * queryForObject参数只接受基础类型，如：Integer,String
     * 走getSingleColumnRowMapper(Class<T> requiredType)
     * Create a new RowMapper for reading result objects from a single column.
     */
}
