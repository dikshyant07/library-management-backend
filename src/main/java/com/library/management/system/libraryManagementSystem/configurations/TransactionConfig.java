package com.library.management.system.libraryManagementSystem.configurations;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Configuration
public class TransactionConfig {
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf) {
            @Override
            public void setEntityManagerFactory(EntityManagerFactory emf) {
                super.setEntityManagerFactory(emf);

            }

            @Override
            protected void doBegin(Object transaction, TransactionDefinition definition) {
                super.doBegin(transaction, definition);
                System.out.println("transaction started");
            }

            @Override
            protected void doRollback(DefaultTransactionStatus status) {
                super.doRollback(status);
                System.out.println("Transaction rolled back");
            }

            @Override
            protected void doCommit(DefaultTransactionStatus status) {
                super.doCommit(status);
                System.out.println("Transaction commited");
            }
        };
    }
}
