package com.calorie.calculator.service;

import com.calorie.calculator.model.Meal;
import com.calorie.calculator.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal saveMeal(Meal meal) {
        if (meal.getDate() == null) {
            meal.setDate(LocalDate.now());
        }
        return mealRepository.save(meal);
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found with id: " + id));
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    public List<Meal> getMealsByDate(LocalDate date) {
        return mealRepository.findByDate(date);
    }

    public List<Meal> getMealsByDateRange(LocalDate startDate, LocalDate endDate) {
        return mealRepository.findByDateBetween(startDate, endDate);
    }

    public List<Meal> getMealsByCategoryAndDate(String category, LocalDate date) {
        return mealRepository.findByCategoryAndDate(category, date);
    }
}
