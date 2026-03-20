package com.example.j2ee_lab03.service;

import com.example.j2ee_lab03.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(2);

    public BookService() {
        books.add(new Book(1L, "Phat trien ung dung voi J2EE", "Nguyen Huy Cuong"));
        books.add(new Book(2L, "Spring Boot Va Thymeleaf", "Anh"));
    }

    public List<Book> findAll() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getId))
                .toList();
    }

    public Book findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Khong tim thay sach voi id: " + id));
    }

    public void save(Book book) {
        book.setId(idGenerator.incrementAndGet());
        books.add(book);
    }

    public void update(Book updatedBook) {
        Book existingBook = findById(updatedBook.getId());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
    }

    public void delete(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
