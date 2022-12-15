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
            int choice = enterAction(sc);
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
            }
        }
    }

    private void inputIncome(Scanner sc, Budget budget) {

        BigDecimal sum = inputSum(sc);
        sc.nextLine();

        System.out.print("Input income date YYYY-MM-DD: ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Input income category, 1 = SALARY or 2 = EXTRA_MONEY: ");
        int cat = sc.nextInt();
        IncomeCategory category = null;
        if (cat == 1) {
            category = IncomeCategory.SALARY;
        } else if (cat == 2) {
            category = IncomeCategory.EXTRA_MONEY;
        }

        System.out.print("Is income bank transfer, 1 = YES or 2 = NO: ");
        int tr = sc.nextInt();
        boolean isBankTransfer;
        isBankTransfer = tr == 1;

        sc.nextLine();

        Person person = inputPerson(sc);

        TransferStatus transferStatus = inputTransferStatus(sc);

        Income.incomeCount++;
        budget.addIncome(new Income(Income.incomeCount, sum, date, category, isBankTransfer, person, transferStatus));

        System.out.println("\nIncome record no." + Income.incomeCount + " added successfully...\n");

    }

    private void inputOutcome(Scanner sc, Budget budget) {
        BigDecimal sum = inputSum(sc);
        sc.nextLine();

        System.out.print("Input outcome date, yyyy-MM-dd: ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Input outcome category, 1 = FOOD, 2 = GAS, 3 = TAXES, 4 = ENTERTAINMENT, 5 = RENT or 6 = EXTRA: ");
        int cat = sc.nextInt();
        OutcomeCategory category;
        if (cat == 1) {
            category = OutcomeCategory.FOOD;
        } else if (cat == 2) {
            category = OutcomeCategory.GAS;
        } else if (cat == 3) {
            category = OutcomeCategory.TAXES;
        } else if (cat == 4) {
            category = OutcomeCategory.ENTERTAINMENT;
        } else if (cat == 5) {
            category = OutcomeCategory.RENT;
        } else {
            category = OutcomeCategory.EXTRA;
        }

        System.out.print("Input outcome type, 1 = CACHE, 2 = CARD or 3 = TRANSFER: ");
        int typ = sc.nextInt();
        sc.nextLine();

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
        System.out.println("Input income category to search, 1 = SALARY or 2 = EXTRA_MONEY: ");
        int cat = sc.nextInt();
        sc.nextLine();
        IncomeCategory inCategory;
        if (cat == 1) {
            inCategory = IncomeCategory.SALARY;
        } else inCategory = IncomeCategory.EXTRA_MONEY;

        System.out.print("Input income date to search YYYY-MM-DD: ");
        LocalDate inDate = LocalDate.parse(sc.nextLine());

        List<Income> inResult = budget.getSomeIncome(inCategory, inDate);
        for (Income income : inResult) {
            System.out.println(income);
        }
    }

    private void printSomeOutcomes(Scanner sc, Budget budget) {
        System.out.println("Input outcome category to search, 1 = FOOD, 2 = GAS, 3 = TAXES, 4 = ENTERTAINMENT, 5 = RENT or 6 = EXTRA: ");
        int cat = sc.nextInt();
        sc.nextLine();
        OutcomeCategory outCategory;
        if (cat == 1) {
            outCategory = OutcomeCategory.FOOD;
        } else if (cat == 2) {
            outCategory = OutcomeCategory.GAS;
        } else if (cat == 3) {
            outCategory = OutcomeCategory.TAXES;
        } else if (cat == 4) {
            outCategory = OutcomeCategory.ENTERTAINMENT;
        } else if (cat == 5) {
            outCategory = OutcomeCategory.RENT;
        } else {
            outCategory = OutcomeCategory.EXTRA;
        }
        System.out.print("Input outcome date to search YYYY-MM-DD: ");
        LocalDate outDate = LocalDate.parse(sc.nextLine());

        List<Outcome> outResult = budget.getSomeOutcome(outCategory, outDate);
        for (Outcome outcome : outResult) {
            System.out.println(outcome);
        }
    }

    private void printAllIncomes(Budget budget) {
        System.out.println("There is all your incomes: ");
        List<Income> allIncomes = budget.getAllIncomes();
        for (Income income : allIncomes) {
            String prn = String.format("Income number %s, date %s, sum %s.%n%n", income.getNumber(), income.getDate(), income.getSum());
            System.out.print(prn);
        }
    }

    private void printAllOutcomes(Budget budget) {
        System.out.println("There is all your outcomes: ");
        List<Outcome> allOutcomes = budget.getAllOutcomes();
        for (Outcome outcome : allOutcomes) {
            String prn = String.format("Outcome number %s, date %s, sum %s.%n%n", outcome.getNumber(), outcome.getDate(), outcome.getSum());
            System.out.print(prn);
        }
    }

    private void deleteIncome(Scanner sc, Budget budget) {
        System.out.print("Input income record number to delete: ");
        String number = sc.nextLine();
        budget.deleteIncome(Integer.parseInt(number));
        System.out.printf("Income record number %s deleted.%n", number);
    }

    private void deleteOutcome(Scanner sc, Budget budget) {
        System.out.print("Input outcome record number to delete: ");
        String number = sc.nextLine();
        budget.deleteOutcome(Integer.parseInt(number));
        System.out.printf("Outcome record number %s deleted.%n", number);
    }

    private void getBalance(Budget budget) {
        System.out.printf("Your balance is: %s%n", budget.getBalance());
    }

    private int enterAction(Scanner sc) {
        String input = sc.nextLine();
        while (!input.matches("[0-9]+") || input.length() > 1) {
            System.out.print("Input number from 0 to 9: ");
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
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
        System.out.print("Input sum: ");

        return sc.nextBigDecimal();
    }

    private TransferStatus inputTransferStatus(Scanner sc) {
        System.out.print("Input transfer status, 1 = IN_PROGRESS, 2 = COMPLETED, 3 = REJECTED: ");
        int st = sc.nextInt();
        sc.nextLine();

        if (st == 1) {

            return TransferStatus.IN_PROGRESS;

        } else if (st == 2) {

            return TransferStatus.COMPLETED;

        } else {

            return TransferStatus.REJECTED;
        }
    }
}
