package annotation.service;

import annotation.SensibleMethod;
import annotation.bean.Book;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    List<Book> repository = ImmutableList.<Book>builder()
                                                .add(new Book("Fondation", "Asimov"))
                                                .add(new Book("Inferno", "Brown"))
                                                .add(new Book("Dune", "Herbert"))
                                                .build();


    @Override
    @SensibleMethod(id="uniqueId")
    public Book searchForBook(String author) {
        Optional<Book> bookOptional = FluentIterable.from(repository)
                                                    .firstMatch(b -> b.getAuthor().equals(author));

        return bookOptional.isPresent() ? bookOptional.get() : null;
    }
}
