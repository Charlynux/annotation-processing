package annotation.service;

import annotation.SensibleMethod;
import annotation.bean.Book;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    List<Book> repository = ImmutableList.<Book>builder()
                                                .add(new Book("Fondation", "Asimov"))
                                                .add(new Book("Inferno", "Brown"))
                                                .add(new Book("Dune", "Herbert"))
                                                .build();

        Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Override
    @SensibleMethod(id="uniqueId")
    public Book searchForBook(String author) {
        logger.debug("Fouille dans ma bibliothèque ...");

        Optional<Book> bookOptional = FluentIterable.from(repository)
                                                    .firstMatch(b -> b.getAuthor().equals(author));

        return bookOptional.isPresent() ? bookOptional.get() : null;
    }
}
