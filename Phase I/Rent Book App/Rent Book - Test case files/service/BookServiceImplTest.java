/**
 * 
 */
package com.example.rentbook.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.rentbook.dto.BookDTO;
import com.example.rentbook.pojo.Admin;
import com.example.rentbook.pojo.Book;
import com.example.rentbook.pojo.Notification;
import com.example.rentbook.pojo.User;
import com.example.rentbook.repository.AdminRepository;
import com.example.rentbook.repository.BookRepository;
import com.example.rentbook.repository.NotificationRepository;
import com.example.rentbook.repository.UserRepository;

/**
 * @author Sahid
 *
 */
@SpringBootTest
class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AdminRepository adminRepository;

	@Mock
	private NotificationRepository notificationRepository;

	private Book book;
	private Page<Book> pages;
	private List<Book> books;
	private Pageable pageable;
	private Admin admin;
	private User user;

	@BeforeEach
	public void setup() {
		book = new Book(1, "book", "author", "publish", LocalDate.now(), "desc", 1000);
		books = List.of(book);
		pages = new PageImpl<>(books);
		pageable = Pageable.ofSize(1);
		admin = new Admin(1, "admin", 1234);
		user = new User(1, "user");
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#getAllBookDetails(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	void testGetAllBookDetails() {
		when(bookRepository.findAll(pageable)).thenReturn(pages);
		Page<Book> bookDetails = bookServiceImpl.getAllBookDetails(pageable);
		Assertions.assertIterableEquals(pages, bookDetails);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#searchBooksByBookName(java.lang.String, org.springframework.data.domain.Pageable)}.
	 */
	@Test
	void testSearchBooksByBookName() {
		when(bookRepository.findByBookName(Mockito.any(), Mockito.any())).thenReturn(pages);
		Page<Book> bookDetails = bookServiceImpl.searchBooksByBookName("book", pageable);
		Assertions.assertIterableEquals(pages, bookDetails);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#findByAuthorName(java.lang.String, org.springframework.data.domain.Pageable)}.
	 */
	@Test
	void testFindByAuthorName() {
		when(bookRepository.findByAuthorName(Mockito.any(), Mockito.any())).thenReturn(pages);
		Page<Book> bookDetails = bookServiceImpl.findByAuthorName("author", pageable);
		Assertions.assertIterableEquals(pages, bookDetails);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#findBySlNo(java.lang.Integer)}.
	 */
	@Test
	void testFindBySlNo() {
		when(bookRepository.findBySlNo(Mockito.any())).thenReturn(book);
		when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		when(adminRepository.findById(Mockito.any())).thenReturn(Optional.of(admin));
		BookDTO bookDTO = bookServiceImpl.findBySlNo(book.getSlNo());
		Assertions.assertEquals(book.getBookDescription(), bookDTO.getBookDescription());
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#rentBook(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	void testRentBookWhenSuccess() {
		List<Notification> notifications = new ArrayList<>();
		Notification notification = new Notification(1, "desc", "type", 1);
		notifications.add(notification);
		when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
		when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		when(adminRepository.findById(Mockito.any())).thenReturn(Optional.of(admin));
		when(notificationRepository.saveAll(Mockito.any())).thenReturn(notifications);
		when(adminRepository.save(Mockito.any())).thenReturn(admin);
		String expected = bookServiceImpl.rentBook(1, 1, 1, 1234);
		Assertions.assertEquals("Book has been rented successfully", expected);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#rentBook(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)}.
	   when the Book is null 
	 */
	@Test
	void testRentBookWhenFailWithBookNull() {
		List<Notification> notifications = new ArrayList<>();
		Notification notification = new Notification(1, "desc", "type", 1);
		notifications.add(notification);
		when(bookRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		when(adminRepository.findById(Mockito.any())).thenReturn(Optional.of(admin));
		String expected = bookServiceImpl.rentBook(1, 1, 1, 1234);
		Assertions.assertEquals("Unable to rent the book", expected);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#rentBook(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)}.
	   when the Admin is null
	 */
	@Test
	void testRentBookWhenFailWithAdminNull() {
		List<Notification> notifications = new ArrayList<>();
		Notification notification = new Notification(1, "desc", "type", 1);
		notifications.add(notification);
		when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
		when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
		when(adminRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		String expected = bookServiceImpl.rentBook(1, 1, 1, 1234);
		Assertions.assertEquals("Unable to rent the book", expected);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.service.BookServiceImpl#rentBook(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)}.
	   when the User is null
	 */
	@Test
	void testRentBookWhenFailWithUserNull() {
		List<Notification> notifications = new ArrayList<>();
		Notification notification = new Notification(1, "desc", "type", 1);
		notifications.add(notification);
		when(bookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
		when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		when(adminRepository.findById(Mockito.any())).thenReturn(Optional.of(admin));
		String expected = bookServiceImpl.rentBook(1, 1, 1, 1234);
		Assertions.assertEquals("Unable to rent the book", expected);
	}

}
