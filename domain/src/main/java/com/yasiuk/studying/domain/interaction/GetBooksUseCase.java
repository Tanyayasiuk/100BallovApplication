package com.yasiuk.studying.domain.interaction;

import com.yasiuk.studying.data.entity.BookData;
import com.yasiuk.studying.data.net.RestService;
import com.yasiuk.studying.domain.entity.Book;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class GetBooksUseCase extends UseCase<Integer, List<Book>> {

    @Override
    protected Observable<List<Book>> buildUseCase(Integer integer) {
        return RestService.getInstance().getBooksListing(integer)
                .map(new Function<List<BookData>, List<Book>>() {
                    @Override
                    public List<Book> apply(@NonNull List<BookData> books) throws Exception {
                        List<Book> booksListing = new ArrayList<>();
                        for (BookData b: books) {
                            Book book = new Book();
                            book.setName(b.getName());
                            book.setPublicUrl(b.getPublicUrl());
                            booksListing.add(book);
                        }
                        return booksListing;
                    }
                });
    }
}
