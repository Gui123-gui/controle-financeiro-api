package api.controleFinanceiro.service;

import api.controleFinanceiro.exception.BusinessException;
import api.controleFinanceiro.exception.ResourceNotFoundException;
import api.controleFinanceiro.model.Category;
import api.controleFinanceiro.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category save(Category category) {
        log.info("Saving category!");
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new BusinessException("Category already exists: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        log.info("Find all categories!");
        return categoryRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (!c.getTransactions().isEmpty()) {
            throw new BusinessException("Cannot delete category with transactions linked to it");
        }

        categoryRepository.delete(c);
    }
}