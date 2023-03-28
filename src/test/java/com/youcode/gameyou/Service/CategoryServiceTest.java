//package com.youcode.gameyou.Service;
//
//import com.youcode.gameyou.Entity.Category;
//import com.youcode.gameyou.Repository.CategoryRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//public class CategoryServiceTest {
//
//    @MockBean
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @TestConfiguration
//    static class CategoryServiceContextConfiguration {
//        @Bean
//        public CategoryService categoryService() {
//            return new CategoryService();
//        }
//    }
//
//    @Test
//    public void testFindAllCategories() {
//        int page = 0;
//        int size = 2;
//
//        // category1
//        Category category1 = new Category();
//        category1.setId(1L);
//        category1.setName("tech");
//        category1.setDescription("tech");
//        category1.setImagepath("imagePath1");
//        category1.setIsactive(true);
//
//        // category2
//        Category category2 = new Category();
//        category2.setId(1L);
//        category2.setName("phones");
//        category2.setDescription("phone");
//        category2.setImagepath("imagePath2");
//        category2.setIsactive(true);
//
//        List<Category> categories = Arrays.asList(category1, category2);
//
//        BDDMockito.given(categoryRepository.findAll()).willReturn(categories);
//
////        assertThat();
//
//    }
//
//}
