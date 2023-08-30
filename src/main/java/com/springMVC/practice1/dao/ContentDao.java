package com.springMVC.practice1.dao;

import com.springMVC.practice1.dto.ContentDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContentDao implements IDao{
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public ContentDao() {
    }

    @Override
    public ArrayList<ContentDto> listDao() {
        String query = "select * from board order by mId desc";
        return (ArrayList<ContentDto>) template.query(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
    }

    @Override
    public void writeDao(String mWriter, String mContent) {
        String query = "insert into board (mWriter, mContent) values (?,?)";
        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,mWriter);
                ps.setString(2,mContent);
            }
        });
    }

    @Override
    public ContentDto viewDao(String strID) {
        String query = "select * from board where mId=" + strID;
        return (ContentDto) template.query(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
    }

    @Override
    public void deleteDao(String mId) {
        String query = "delete from board where mId=?";
        template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, Integer.parseInt(mId));
            }
        });

    }
}
