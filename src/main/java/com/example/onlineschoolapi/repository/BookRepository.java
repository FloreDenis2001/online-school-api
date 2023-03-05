package com.example.onlineschoolapi.repository;

import com.example.onlineschoolapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


    @Query("select b from Book b order by b.price asc")
    Optional<List<Book>> orderBooksAscendentByPrice();

    @Query("select b from Book b order by b.price desc")
    Optional<List<Book>> orderBooksDescendentByPrice();

    @Query("select b from Book b where b.price>?1")
    Optional<List<Book>> lowestPriceBook(double priceMin);
    @Query("select b from Book b where b.price<?1")
    Optional<List<Book>> highPriceBook(double priceMax);


    @Query("select b from Book b where b.stars=(select max(b.stars) from Book b)")
    Optional<List<Book>> bestBooks();

    @Query("select b from Book b where b.stars=?1")
    Optional<List<Book>> filterStarBook(Long starsWanted);

    @Query("select b from Book b where b.student.id=?1 and b.title=?2")
    Optional<Book> getBookByStudentAndTitle(Long studentId,String title);

    @Query("delete from Book b where b.id=?1")
    void removeBookById(Long idBook);




}
