package com.zss.transaction.dao;

import com.zss.transaction.domain.Wallet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRowMapper implements RowMapper<Wallet> {
    @Override
    public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wallet wallet = new Wallet();
        wallet.setId(rs.getLong("id"));
        wallet.setAccount(rs.getString("account"));
        wallet.setMoney(rs.getBigDecimal("money"));
        return wallet;
    }
}
