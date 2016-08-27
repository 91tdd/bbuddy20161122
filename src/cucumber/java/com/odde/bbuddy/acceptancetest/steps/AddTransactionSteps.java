package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.transaction.EditableTransaction;
import com.odde.bbuddy.acceptancetest.data.transaction.TransactionRepoForTest;
import com.odde.bbuddy.acceptancetest.pages.AddTransactionPage;
import com.odde.bbuddy.transaction.domain.Transaction;
import cucumber.api.Format;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

public class AddTransactionSteps {

    @Autowired
    AddTransactionPage addTransactionPage;

    @Autowired
    TransactionRepoForTest transactionRepo;

    @When("^add a new transaction with the following information$")
    public void add_a_new_transaction_with_the_following_information(List<EditableTransaction> transactions) throws Throwable {
        addTransactionPage.add(transactions.get(0));
    }

    @Then("^a new transaction will be created$")
    public void a_new_transaction_will_be_created(@Format("yyyy-MM-dd") List<Transaction> expected) throws Throwable {
        assertTransactionsEquals(expected, transactionRepo.findAll());
    }

    private void assertTransactionsEquals(List<Transaction> expected, List<Transaction> actual) {
        assertEquals(1, actual.size());
        assertReflectionEquals(expected, actual, IGNORE_DEFAULTS);
    }
}
