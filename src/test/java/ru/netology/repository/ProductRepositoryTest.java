package ru.netology.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;


import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
  private ProductRepository repository = new ProductRepository();
  private Book coreJava = new Book();

  @Test
  public void shouldSaveOneItem() {
    repository.save(coreJava);

    Product[] expected = new Product[]{coreJava};
    Product[] actual = repository.findAll();
    assertArrayEquals(expected, actual);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 4})
  public void shouldRemoveId(int idToRemove){

    //int idToRemove = 3;
    Product first = new Product(1, "first", 100);
    Product second = new Product(2, "second", 110);
    Product third = new Product(3, "third", 80);
    repository.save(first);
    repository.save(second);
    repository.save(third);

    repository.removeById(idToRemove);

    Product[] actual = repository.findAll();
    Product[] expected = new Product[]{first, second};
    assertArrayEquals(expected, actual);
  }
  @Test
  public void shouldThrowCheckedException(){
    int idToRemove = 4;
    assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
  }
}
