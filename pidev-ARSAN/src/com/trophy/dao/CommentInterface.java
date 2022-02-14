/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.trophy.dao;

import com.trophy.entity.News;
import java.util.List;

/**
 *
 * @author Mouelhi
 */
public interface CommentInterface<Comment,News> {
    public void insert(Comment p,News n);
    public void delete(Comment p,News n);
    public List<Comment> displayAll(News n);
    public Comment displayById(int ID_COMMENT, int ID_NEWS);
    public boolean update(Comment p,News n);
    
}
