package com.springMVC.practice1.dao;

import com.springMVC.practice1.dto.ContentDto;

import java.util.ArrayList;

public interface IDao {
    public ArrayList<ContentDto> listDao();

    public void writeDao(String mWriter, String mContent);
    public ContentDto viewDao(String strID);

    public void deleteDao(String mId);
}
