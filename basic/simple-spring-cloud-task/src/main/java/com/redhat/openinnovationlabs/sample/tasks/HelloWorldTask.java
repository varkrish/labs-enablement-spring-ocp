package com.redhat.openinnovationlabs.sample.tasks;

import com.redhat.openinnovationlabs.sample.model.Transaction;
import com.redhat.openinnovationlabs.sample.model.TransactionHistory;
import com.redhat.openinnovationlabs.sample.repository.TransactionHistoryRepository;
import com.redhat.openinnovationlabs.sample.repository.TransactionRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class HelloWorldTask implements ApplicationRunner {

    private Log logger = LogFactory.getLog(HelloWorldTask.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    /**
     * in this sample we are trying to input data from Transaction table into Transaction History
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("begin inputting data");

        // get all
        List<Transaction> transactions = transactionRepository.findAll();

        // convert and insert into Transaction History table
        for (Transaction transaction : transactions) {
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setAmount(transaction.getAmount());
            transactionHistory.setPriceTotal(transaction.getPriceTotal());
            transactionHistory.setProductname(transaction.getProductname());
            transactionHistory.setTrxDate(new Date());

            transactionHistoryRepository.save(transactionHistory);
        }

        logger.info("finish inputting data");

        logger.info("========================== \t start printing secret");

        logger.info("========================== \t end of printing secret");
    }
}
