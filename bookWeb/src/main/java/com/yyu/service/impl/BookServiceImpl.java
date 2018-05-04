package com.yyu.service.impl;

import com.yyu.domain.Book;
import com.yyu.mapper.BookMapper;
import com.yyu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getAll(){
        return bookMapper.findAll();
    }
}
