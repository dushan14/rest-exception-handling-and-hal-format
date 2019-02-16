package com.practice.demo.controller.resourceAssembler;

import com.practice.demo.controller.BookController;
import com.practice.demo.model.Book;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BookResourceAssembler implements ResourceAssembler<Book, Resource<Book>> {

    @Override
    public Resource<Book> toResource(Book book) {
        return new Resource<>(book,
                linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getBooks()).withRel("books")
        );
    }
}
