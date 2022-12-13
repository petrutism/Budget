package budget;

import data.Income;
import data.IncomeCategory;
import data.Outcome;
import data.OutcomeCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

class Budget {
    private Income[] incomes;
    private Outcome[] outcomes;

    void addIncome(Income income) {
        if (incomes == null) {
            incomes = new Income[1];
        } else {
            Income[] temp = new Income[incomes.length + 1];
            for (int i = 0; i < incomes.length; i++) {
                temp[i] = incomes[i];
            }
            incomes = temp;
        }
        incomes[incomes.length - 1] = income;
    }

    void addOutcome(Outcome outcome) {
        if (outcomes == null) {
            outcomes = new Outcome[1];
        } else {
            Outcome[] temp = new Outcome[outcomes.length + 1];
            for (int i = 0; i < outcomes.length; i++) {
                temp[i] = outcomes[i];
            }
            outcomes = temp;
        }
        outcomes[outcomes.length - 1] = outcome;
    }

    Income getIncome(IncomeCategory category, LocalDate date) {
        for (Income income : incomes) {
            if (income.getCategory().equals(category) && income.getDate().isEqual(date)) {
                return income;
            }
        }
        return null;
    }

    Outcome getOutcome(OutcomeCategory category, LocalDateTime date) {
        for (Outcome outcome : outcomes) {
            if (outcome.getCategory().equals(category) && outcome.getDate().isEqual(date)) {
                return outcome;
            }
        }
        return null;
    }
}

//create main class add few income and few outcome find added income and outcome
