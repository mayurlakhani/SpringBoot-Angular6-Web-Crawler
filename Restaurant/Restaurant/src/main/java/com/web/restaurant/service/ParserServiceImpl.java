package com.web.restaurant.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.restaurant.model.Reader;
import com.web.restaurant.model.Category;
import com.web.restaurant.model.Dish;
import com.web.restaurant.model.RestaurantDetails;
import com.web.restaurant.repository.CategoryRepository;
import com.web.restaurant.repository.DishRepository;
import com.web.restaurant.repository.RestaurantDetailsRepository;

import com.web.restaurant.enums.CrawlerType;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

@Service
@Transactional
public class ParserServiceImpl implements ParserService {

	private static final String PDF_URL = ".//menu_namaste_nepal_chemnitz_vegan_vegetarisch.pdf";

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private RestaurantDetailsRepository reDetailsRepository;
	@Autowired
	private ParserService parserService;

	public void parse(CrawlerType type) throws IOException {
		// TODO Auto-generated method stub
		if (type == CrawlerType.UPDATE) {
			dishRepository.deleteAllDishes();
			categoryRepository.deleteAllCategories();
		}
		webParsing();
		pdfParsing();
	}

	private void pdfParsing() throws IOException {
		
		RestaurantDetails restaurantDetails =  reDetailsRepository.findByRestaurantName("NAMASTE NEPAL");
		String url = "http://namastenepal.de/menu_namaste_nepal_chemnitz_vegan_vegetarisch.pdf";
		try {
			parserService
					.downloadUsingStream(
							url,PDF_URL
							);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Reader reader = new Reader();
		reader.setFilePath(PDF_URL);

		String text = reader.ToText();
		int x = 0;
		String lines[] = text.split("\\r?\\n");

		boolean checker = false;
		String descrip = "";
		String price = "";
		String name = "";
		String id = "";
		int i = 0;

		Category category = new Category();

		category.setRestaurantId(restaurantDetails);
		category.setCatName("Nepal Food");

		categoryRepository.save(category);

		for (String line : lines) {

			String cols[] = line.split("\\.");
			Set<Dish> dishList = new HashSet<>();

			for (String col : cols) {
				col = col.trim();
				if (!col.isEmpty()) {
					if (Character.isDigit(col.charAt(0)) && col.length() < 4) {
						Dish dish = new Dish();
						if (i > 0) {
							// System.out.println("ID : "+id);
							dish.setDishName(name);
							System.out.println("Name : " + name);
							dish.setDishIngredients(descrip);
							System.out.println("Description : " + descrip);
							dish.setDishPrice(price);
							System.out.println("Price : " + price);
							System.out
									.println("----------------------------------------------");
							id = "";
							dish.setCategory(category);
							dishList.add(dish);

						}

						id = col.trim();
						// System.out.println("ID : "+col.trim());
						checker = true;

						x = 0;
						descrip = "";
						name = "";
						price = "";
						i++;
					} else if (!Character.isDigit(col.charAt(0))
							&& checker == true) {
						if (x == 0) {
							name = col.trim();
							// System.out.println("Name : "+col.trim());
							x++;
						} else if (x > 1) {
							descrip += col.trim() + " ";
							// System.out.println("Description : "+descrip);
						}
						x++;
					} else if (Character.isDigit(col.charAt(0))
							&& col.length() < 10) {
						price = col.trim();

						// System.out.println("Price : "+col.trim());
						// checker=false;

					}
				} // }

			}
			dishRepository.save(dishList);
			category.setDishList(dishList);

		}

	}

	private void webParsing() {
		reDetailsRepository
				.findByRestaurantNameNot("NAMASTE NEPAL")
				.forEach(
						restaurant -> {
							String url = restaurant.getHomeUrl();
							Document doc = null;
							try {
								doc = Jsoup.connect(url).get();
								List<Category> categoryList = new ArrayList<>();
								for (int i = 1; i < 5; i++) {
									Category category = new Category();
									category.setRestaurantId(restaurant);
									Element realcategories = doc
											.select("div.full").first()
											.select("div.menuproducts").first()
											.select("div.widget").select("h3")
											.get(i);
									String catString = realcategories.text();
									System.out.println(catString);
									category.setCatName(catString);
									// Adding Elements to List
									categoryList.add(category);
									// Adding data to Database
									Elements dishName1 = doc.select("div.full")
											.first().select("div.menuproducts")
											.first().children()
											.select("div#cat" + i)
											.select("ul.zebra").select("li")
											.select("a");
									Set<Dish> dishList = new HashSet<>();
									for (Element dishName : dishName1) {
										Dish dish = new Dish();
										String dishString = dishName
												.select("b").text();
										dish.setDishName(dishString);
										String dishDescString = dishName
												.select("a").select("span")
												.last().text();
										dish.setDishIngredients(dishDescString);
										String dishPriceString = dishName
												.select("a").select("span")
												.get(1).text();
										dish.setDishPrice(dishPriceString);
										dish.setCategory(category);
										System.out.println(dishString);
										System.out.println(dishDescString);
										System.out.println(dishPriceString);

										// Adding each field to List from dish
										// Object
										dishList.add(dish);
										System.out.println(dish);
										// Adding each field to Database from
										// dishList
									}
									category.setDishList(dishList);
								}
								categoryRepository.save(categoryList);
							} catch (IOException e) {
								System.out.println(e);
							}
						});
	}

	@Override
	public void downloadUsingStream(String urlStr, String file)
			throws IOException {
		URL url = new URL(urlStr);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();
	}

}