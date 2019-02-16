package com.practice.demo.controller;

import com.practice.demo.model.Book;
import com.practice.demo.controller.resourceAssembler.BookResourceAssembler;
import com.practice.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public Resources<Resource<Book>> getBooks() {
        List<Resource<Book>> booksResources = bookService.getBooks().stream()
                .map(resourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources(booksResources, linkTo(methodOn(BookController.class).getBooks()).withSelfRel());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET,produces = "application/json")
    public Resource<Book> getBookById(@PathVariable int id) {
        return resourceAssembler.toResource(bookService.getBookById(id));
    }

    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<?> addBook(@RequestBody Book book) throws URISyntaxException {
        Resource<Book> bookResource = resourceAssembler.toResource(bookService.addBook(book));
        return ResponseEntity.created(new URI(bookResource.getId().expand().getHref()))
                .body(bookResource);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT,produces = "application/json")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable int id) throws URISyntaxException {
        Resource<Book> bookResource = resourceAssembler.toResource(bookService.updateBook(book, id));
        return ResponseEntity.created(new URI(bookResource.getId().expand().getHref()))
                .body(bookResource);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE,produces = "application/json")
    public ResponseEntity<?> deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
