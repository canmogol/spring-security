package com.pluralsight.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;
import java.io.Serializable;

public class FitnessPermissionEvaluator implements PermissionEvaluator {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Object[] args = {
                ((User) authentication.getPrincipal()).getUsername(),
                targetDomainObject.getClass().getName(),
                permission.toString()
        };

        String sql = "SELECT count(*) FROM permissions p WHERE p.username = ? AND p.target = ? AND p.permission = ?";
        int count = template.queryForObject(sql, args, Integer.class);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
