package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

import lombok.var;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StockTest {

	 @Autowired
	 private MockMvc mockMvc;

	@Mock
	StockRepository sr;
	@InjectMocks
	StockServiceImpl ss;
	
	@Test
	public void RetrieveAllStockTest() {
		List<Stock> stocks = new ArrayList<Stock>();
		Stock s1= new Stock("libelle",20,1);
		Stock s2= new Stock("libelle1",14,1);
		Stock s3= new Stock("libelle2",25,1);
		stocks.add(s1);
		stocks.add(s2);
		stocks.add(s3);
		
		when(sr.findAll()).thenReturn(stocks);

		List<Stock> expected = ss.retrieveAllStocks();
		assertEquals(3, expected.size());
		//assertEquals(expected, stocks);
		verify(sr).findAll();
	}


	@Test
	public void AddStockTest() {
		Stock stock = new Stock("libelle1",12,5);

		Mockito.when(sr.save(ArgumentMatchers.any(Stock.class))).thenReturn(stock);

		Stock Stockadd = ss.addStock(stock);

		assertThat(Stockadd.getLibelleStock()).isSameAs(Stockadd.getLibelleStock());
		verify(sr).save(stock);
	}

	
	@Test
	public void DeleteStockIfExistTest() {
		Stock stock = new Stock();
		stock.setIdStock(1L);
		stock.setLibelleStock("libelle3");
		stock.setQte(20);
		stock.setQteMin(1);
		Mockito.when(sr.findById(stock.getIdStock())).thenReturn(Optional.of(stock));
		ss.deleteStock(stock.getIdStock());
		verify(sr).deleteById(stock.getIdStock());
	};
	//expected = RuntimeException.class
	@Test()
	public void should_throw_exception_when_stock_doesnt_exist() {
		Stock stock = new Stock();
		stock.setIdStock(90L);
		stock.setLibelleStock("libelle4");
		stock.setQte(20);
		stock.setQteMin(1);
		given(sr.findById(anyLong())).willReturn(Optional.ofNullable(null));
		sr.deleteById(stock.getIdStock());
		}
	}
