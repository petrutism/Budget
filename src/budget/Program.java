package budget;


import data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Budget budget = new Budget();
        Program program = new Program();
        program.selectAction(sc, budget);
    }

    private void selectAction(Scanner sc, Budget budget) {

        String info = """
                1 - Add income
                2 - Add outcome
                3 - Get income by category and date
                4 - Get outcome by category and date
                5 - Print all incomes
                6 - Print all outcomes
                7 - Delete income by number
                8 - Delete outcome by number
                9 - Get balance
                0 - END
                """;

        boolean repeat = true;

        while (repeat) {
            System.out.println(info);
            int choice = onlyNumber(sc);
            switch (choice) {
                case 1 -> inputIncome(sc, budget);
                case 2 -> inputOutcome(sc, budget);
                case 3 -> printSomeIncomes(sc, budget);
                case 4 -> printSomeOutcomes(sc, budget);
                case 5 -> printAllIncomes(budget);
                case 6 -> printAllOutcomes(budget);
                case 7 -> deleteIncome(sc, budget);
                case 8 -> deleteOutcome(sc, budget);
                case 9 -> getBalance(budget);
                case 0 -> {
                    System.out.println("FINISHING...");
                    repeat = false;
                }
                default -> System.out.println("There is no such action...");
            }
        }
    }

    private void inputIncome(Scanner sc, Budget budget) {

        BigDecimal sum = inputSum(sc);

        System.out.print("Input income date YYYY-MM-DD: ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Income category, 1 = SALARY or other number = EXTRA_MONEY. ");
        int cat = onlyNumber(sc);
        IncomeCategory category = null;
        if (cat == 1) {
            category = IncomeCategory.SALARY;
        } else if (cat == 2) {
            category = IncomeCategory.EXTRA_MONEY;
        }

        System.out.print("Is income bank transfer, 1 = YES or other number = NO. ");
        int tr = onlyNumber(sc);
        boolean isBankTransfer;
        isBankTransfer = tr == 1;

        Person person = inputPerson(sc);

        TransferStatus transferStatus = inputTransferStatus(sc);

        Income.incomeCount++;
        budget.addIncome(new Income(Income.incomeCount, sum, date, category, isBankTransfer, person, transferStatus));

        System.out.println("\nIncome record no." + Income.incomeCount + " added successfully...\n");

    }

    private void inputOutcome(Scanner sc, Budget budget) {
        BigDecimal sum = inputSum(sc);

        System.out.print("Input outcome date, yyyy-MM-dd: ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Input outcome category, 1 = FOOD, 2 = GAS, 3 = TAXES, 4 = ENTERTAINMENT, 5 = RENT or other number = EXTRA. ");
        int cat = onlyNumber(sc);
        OutcomeCategory category;

        switch (cat) {
            case 1 -> category = OutcomeCategory.FOOD;
            case 2 -> category = OutcomeCategory.GAS;
            case 3 -> category = OutcomeCategory.TAXES;
            case 4 -> category = OutcomeCategory.ENTERTAINMENT;
            case 5 -> category = OutcomeCategory.RENT;
            default -> category = OutcomeCategory.EXTRA;
        }

        System.out.print("Input outcome type, 1 = CACHE, 2 = CARD or other number = TRANSFER. ");
        int typ = onlyNumber(sc);

        OutcomeType type;
        if (typ == 1) {
            type = OutcomeType.CACHE;
            Outcome.outcomeCount++;
            budget.addOutcome(new Outcome(Outcome.outcomeCount, sum, category, type));
        } else if (typ == 2) {
            type = OutcomeType.CARD;
            Outcome.outcomeCount++;
            budget.addOutcome(new Outcome(Outcome.outcomeCount, sum, category, type));
        } else {
            type = OutcomeType.TRANSFER;
            Person person = inputPerson(sc);
            TransferStatus status = inputTransferStatus(sc);
            Outcome.outcomeCount++;
            budget.addOutcome(new Outcome(Outcome.outcomeCount, sum, date, category, type, person, status));
        }

        System.out.println("\nOutcome record no." + Outcome.outcomeCount + " added successfully...\n");
    }

    private void printSomeIncomes(Scanner sc, Budget budget) {
        if (incomesIsEmpty(budget)) {

            return;
        }

        System.out.println("Input income category to search, 1 = SALARY or other number = EXTRA_MONEY. ");
        int cat = onlyNumber(sc);
        IncomeCategory inCategory;
        if (cat == 1) {
            inCategory = IncomeCategory.SALARY;
        } else inCategory = IncomeCategory.EXTRA_MONEY;

        System.out.print("Input income date to search YYYY-MM-DD: ");
        LocalDate inDate = LocalDate.parse(sc.nextLine());

        List<Income> incSearchResult = budget.getSomeIncome(inCategory, inDate);
        for (Income income : incSearchResult) {
            System.out.println(income);
        }
    }

    private void printSomeOutcomes(Scanner sc, Budget budget) {
        if (outcomesIsEmpty(budget)) {
            return;
        }

        System.out.println("Input outcome category to search, 1 = FOOD, 2 = GAS, 3 = TAXES, 4 = ENTERTAINMENT, 5 = RENT or other number = EXTRA. ");
        int cat = onlyNumber(sc);

        OutcomeCategory outCategory;
        switch (cat) {
            case 1 -> outCategory = OutcomeCategory.FOOD;
            case 2 -> outCategory = OutcomeCategory.GAS;
            case 3 -> outCategory = OutcomeCategory.TAXES;
            case 4 -> outCategory = OutcomeCategory.ENTERTAINMENT;
            case 5 -> outCategory = OutcomeCategory.RENT;
            default -> outCategory = OutcomeCategory.EXTRA;
        }

        System.out.print("Input outcome date to search YYYY-MM-DD: ");
        LocalDate outDate = LocalDate.parse(sc.nextLine());

        List<Outcome> outSearchResult = budget.getSomeOutcome(outCategory, outDate);
        for (Outcome outcome : outSearchResult) {
            System.out.println(outcome);
        }
    }

    private void printAllIncomes(Budget budget) {
        if (incomesIsEmpty(budget)) {
            return;
        }

        List<Income> allIncomes = budget.getAllIncomes();
        System.out.println("There is all your incomes: ");

        for (Income income : allIncomes) {
            String prn = String.format("Income number %s, date %s, sum %s.%n%n", income.getNumber(), income.getDate(), income.getSum());
            System.out.print(prn);
        }
    }

    private void printAllOutcomes(Budget budget) {
        if (outcomesIsEmpty(budget)) {
            return;
        }

        List<Outcome> allOutcomes = budget.getAllOutcomes();
        System.out.println("There is all your outcomes: ");

        for (Outcome outcome : allOutcomes) {
            String prn = String.format("Outcome number %s, date %s, sum %s.%n%n", outcome.getNumber(), outcome.getDate(), outcome.getSum());
            System.out.print(prn);
        }
    }

    private void deleteIncome(Scanner sc, Budget budget) {
        if (incomesIsEmpty(budget)) {

            return;
        }
        System.out.print("Record number to delete. ");
        int number = onlyNumber(sc);
        if(budget.incomeDeleted(number)){
            System.out.printf("Income record number %s deleted.%n", number);
        } else {
            System.out.println("There is nothing to delete.");
        }
    }

    private void deleteOutcome(Scanner sc, Budget budget) {
        if (outcomesIsEmpty(budget)) {

            return;
        }
        System.out.print("Outcome record number to delete. ");
        int number = onlyNumber(sc);
        if(budget.outcomeDeleted(number)){
            System.out.printf("Outcome record number %s deleted.%n", number);
        } else {
            System.out.println("There is nothing to delete.");
        }

    }

    private void getBalance(Budget budget) {
        System.out.printf("Your balance is: %s%n", budget.getBalance());
    }

    private Person inputPerson(Scanner sc) {
        System.out.print("Input persons name: ");

        String name = sc.nextLine();

        System.out.print("Input persons surname: ");
        String surname = sc.nextLine();

        System.out.print("Input persons age: ");
        int age = sc.nextInt();
        sc.nextLine();

        return new Person(name, surname, age);
    }

    private BigDecimal inputSum(Scanner sc) {

        while (true) {
            try {
                System.out.println("Input sum: ");
                String s = sc.nextLine();
                return BigDecimal.valueOf(Double.parseDouble(s));
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong format, numbers only please...");
            }
        }
    }

    private int onlyNumber(Scanner sc) {
        while (true) {
            try {
                System.out.println("Input number: ");
                String s = sc.nextLine();
                return Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong format, numbers only please...");
            }
        }
    }

    private TransferStatus inputTransferStatus(Scanner sc) {

        System.out.print("Transfer status, 1 = IN_PROGRESS, 2 = COMPLETED or other number = REJECTED: ");
        int st = onlyNumber(sc);

        if (st == 1) {

            return TransferStatus.IN_PROGRESS;

        } else if (st == 2) {

            return TransferStatus.COMPLETED;

        } else {

            return TransferStatus.REJECTED;
        }
    }

    private boolean incomesIsEmpty(Budget budget) {
        if (budget.getAllIncomes() == null || budget.getAllIncomes().size() == 0) {
            System.out.println("Income list is empty. Create some incomes. \n");

            return true;
        }
        return false;
    }

    private boolean outcomesIsEmpty(Budget budget) {
        if (budget.getAllOutcomes() == null || budget.getAllOutcomes().size() == 0) {
            System.out.println("Outcome list is empty. Create some outcomes.\n");

            return true;
        }
        return false;
    }
}

