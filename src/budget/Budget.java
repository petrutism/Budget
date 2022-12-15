package budget;

import data.Income;
import data.IncomeCategory;
import data.Outcome;
import data.OutcomeCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Budget {
    private List<Income> incomes;
    private List<Outcome> outcomes;
    private List<Income> incomeResults;
    private List<Outcome> outcomeResults;

    void addIncome(Income income) {
        if (incomes == null) {
            incomes = new ArrayList<>();
        }
        incomes.add(income);
    }

    void addOutcome(Outcome outcome) {
        if (outcomes == null) {
            outcomes = new ArrayList<>();
        }
        outcomes.add(outcome);
    }

    void deleteIncome(int number) {
        for (Income income : incomes) {
            if (income.getNumber() == number) {
                incomes.remove(income);
                break;
            }
        }
    }

    void deleteOutcome(int number) {
        for (Outcome outcome : outcomes) {
            if (outcome.getNumber() == number) {
                incomes.remove(outcome);
                break;
            }
        }
    }

    List<Income> getSomeIncome(IncomeCategory category, LocalDate date) {
        if (incomeResults == null) {
            incomeResults = new ArrayList<>();
        }
        incomeResults.clear();
        for (Income income : incomes) {
            if (income.getCategory().equals(category) && income.getDate().isEqual(date)) {
                incomeResults.add(income);
            }
        }

        return incomeResults;
    }

    List<Outcome> getSomeOutcome(OutcomeCategory category, LocalDate date) {
        if (outcomeResults == null) {
            outcomeResults = new ArrayList<>();
        }
        outcomeResults.clear();
        for (Outcome outcome : outcomes) {
            if (outcome.getCategory().equals(category) && outcome.getDate().isEqual(date)) {
                outcomeResults.add(outcome);
            }
        }

        return outcomeResults;
    }

    List<Income> getAllIncomes() {

        return incomes;
    }

    List<Outcome> getAllOutcomes() {

        return outcomes;
    }

    double getBalance() {
        Double incomeSum = 0d;
        for (Income income : incomes) {
            incomeSum += income.getSum().doubleValue();
        }

        Double outcomeSum = 0d;
        for (Outcome outcome : outcomes) {
            outcomeSum += outcome.getSum().doubleValue();
        }

        return incomeSum - outcomeSum;
    }
}