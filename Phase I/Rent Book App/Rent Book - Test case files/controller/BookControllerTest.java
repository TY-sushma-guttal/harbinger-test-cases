/**
 * 
 */
package com.example.rentbook.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.rentbook.dto.BookDTO;
import com.example.rentbook.pojo.Book;
import com.example.rentbook.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sahid
 *
 */
@SpringBootTest
class BookControllerTest {

	@InjectMocks
	private BookController bookController;

	@Mock
	private BookService bookService;

	private MockMvc mockMvc;

	private Book book;

	private ObjectMapper mapper;
	HashMap<String, Object> flashAttr;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
		book = new Book(1, "test", "test", "test", LocalDate.now(), "test", 1000);
		mapper = new ObjectMapper();
		Model model = mock(Model.class);
		flashAttr = new HashMap<>();
		flashAttr.put("model", model);
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#getAllBookDetails(java.lang.Integer, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetAllBookDetailsWhenSuccess() throws Exception {
		List<Book> books = List.of(book);

		Page<Book> pages = new PageImpl<>(books);

		when(bookService.getAllBookDetails(Mockito.any())).thenReturn(pages);

		mockMvc.perform(get("/rent-book/getallbooks/{page}", 0).flashAttrs(flashAttr)).andExpect(status().isOk())
				.andDo(print()).andExpect(forwardedUrl("bookLibrary")).andExpect(model().attributeExists("listOfBooks"))
				.andExpect(model().attributeExists("currentPage")).andExpect(model().attributeExists("totalPages"));
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#getAllBookDetails(java.lang.Integer, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetAllBookDetailsWhenFail() throws Exception {
		Page<Book> books = Page.empty();

		when(bookService.getAllBookDetails(Mockito.any())).thenReturn(books);

		mockMvc.perform(get("/rent-book/getallbooks/{page}", 0).flashAttrs(flashAttr)).andExpect(status().isOk())
				.andDo(print()).andExpect(forwardedUrl("errorPage"));
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#searchBooks(com.example.rentbook.dto.BookDTO, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchBooksWhenSuccess() throws Exception {
		BookDTO bookDTO = new BookDTO("Test", 0);

		List<Book> books = List.of(book);

		Page<Book> pages = new PageImpl<>(books);

		when(bookService.searchBooksByBookName(Mockito.any(), Mockito.any())).thenReturn(pages);

		String bookDtoValue = mapper.writeValueAsString(bookDTO);

		mockMvc.perform(post("/rent-book/searchBooks").flashAttrs(flashAttr).content(bookDtoValue))
				.andExpect(status().isOk()).andDo(print()).andExpect(forwardedUrl("searchedbooks"))
				.andExpect(model().attributeExists("totalPages")).andExpect(model().attributeExists("currentPage"))
				.andExpect(model().attributeExists("showBooks"));

	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#searchBooks(com.example.rentbook.dto.BookDTO, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchBooksWithAuthorName() throws Exception {
		BookDTO bookDTO = new BookDTO("Test", 0);

		Page<Book> pages = Page.empty();

		List<Book> books = List.of(book);

		Page<Book> pagesAuthor = new PageImpl<>(books);

		when(bookService.searchBooksByBookName(Mockito.any(), Mockito.any())).thenReturn(pages);
		when(bookService.findByAuthorName(Mockito.any(), Mockito.any())).thenReturn(pagesAuthor);

		String bookDtoValue = mapper.writeValueAsString(bookDTO);

		mockMvc.perform(post("/rent-book/searchBooks").flashAttrs(flashAttr).content(bookDtoValue))
				.andExpect(status().isOk()).andDo(print()).andExpect(forwardedUrl("searchedbooks"))
				.andExpect(model().attributeExists("totalPages")).andExpect(model().attributeExists("currentPage"))
				.andExpect(model().attributeExists("showBooks"));

	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#searchBooks(com.example.rentbook.dto.BookDTO, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchBooksWithOutAuthorName() throws Exception {
		BookDTO bookDTO = new BookDTO("Test", 0);

		Page<Book> pages = Page.empty();

		Page<Book> pagesAuthor = Page.empty();

		when(bookService.searchBooksByBookName(Mockito.any(), Mockito.any())).thenReturn(pages);
		when(bookService.findByAuthorName(Mockito.any(), Mockito.any())).thenReturn(pagesAuthor);

		String bookDtoValue = mapper.writeValueAsString(bookDTO);

		mockMvc.perform(post("/rent-book/searchBooks").flashAttrs(flashAttr).content(bookDtoValue))
				.andExpect(status().isOk()).andDo(print()).andExpect(forwardedUrl("errorPage"))
				.andExpect(model().attributeExists("bookDTO"));
	}


	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#bookDetails(java.lang.Integer, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testBookDetailsWhenSuccess() throws Exception {
		BookDTO bookDTO=new BookDTO("qwerty",1,1,1,1,1,"qwerty");
		when(bookService.findBySlNo(Mockito.any())).thenReturn(bookDTO);
		mockMvc.perform(get("/rent-book/getBookDetails/{slNo}", 0)
				.flashAttrs(flashAttr))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(forwardedUrl("bookDetails"))
				.andExpect(model().attributeExists("bookPrice"))
				.andExpect(model().attributeExists("bookName"));
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#bookDetails(java.lang.Integer, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testBookDetailsWhenFail() throws Exception {
		int slNo = 1;
		when(bookService.findBySlNo(Mockito.any())).thenReturn(null);

		mockMvc.perform(get("/rent-book/getBookDetails/{slNo}", slNo).flashAttrs(flashAttr)).andExpect(status().isOk())
				.andDo(print()).andExpect(forwardedUrl("errorPage"));
	}

	/**
	 * Test method for
	 * {@link com.example.rentbook.controller.BookController#rentedBooks(java.lang.Integer, java.lang.Integer, org.springframework.ui.ModelMap)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testRentedBooks() throws Exception {
		mockMvc.perform(get("/rent-book/rentBooks/{noOfHours}/{bookPrice}/{userId}/{adminId}/{bookId}", 1, 1, 1, 1, 1)
				.flashAttrs(flashAttr))
				.andExpect(status().is(302))
				.andDo(print()).andExpect(redirectedUrl("/rent-book/getBookDetails/1?totalPrice=1"))
				.andExpect(model().attributeExists("totalPrice"));
	}

}
