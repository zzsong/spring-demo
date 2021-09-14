package com.zss.transaction.manager;

import com.alibaba.fastjson.JSON;
import com.zss.result.ModelResult;
import com.zss.transaction.domain.Wallet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class TransactionExampleManager {

    @Autowired
    private WalletManager walletManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     *
     */
    @Transactional
    public void example1(){
        BigDecimal amount = BigDecimal.valueOf(20);
        walletManager.add(1,amount,false);
        walletManager.subtract(2,amount,true);
    }

    public void example2(){
        example2_private();
    }

    /**
     * @see org.springframework.transaction.interceptor.AbstractFallbackTransactionAttributeSource
     * computeTransactionAttribute
     * // Don't allow no-public methods as required.
     * 		if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
     * 			return null;
     *       }
     */
    @Transactional
    private void example2_private(){
        BigDecimal amount = BigDecimal.valueOf(20);
        walletManager.add(1,amount,false);
        walletManager.subtract(2,amount,true);
    }

    public void example3(int id){

        /**
         * execute 抛出异常，自动回滚。此方法需要添加对异常的捕获处理业务逻辑代码
         *
         *
         */
        ModelResult<Wallet> modelResult = transactionTemplate.execute((status -> {
            ModelResult<Wallet> mr = new ModelResult<>();

            BigDecimal amount = BigDecimal.valueOf(20);
            walletManager.add(1,amount,false);
            //添加事务预存节点，用于回滚
            Object a = status.createSavepoint();

            try {
                walletManager.subtract(2,amount,true);
            } catch (Exception e){
                status.rollbackToSavepoint(a);
            }

            try{
                Wallet w = walletManager.queryById(id);
                mr.setModel(w);
                if (id == 100){
                    mr.withError("RuntimeException","id=100,error");
                }
            } catch (Exception exception){
                mr.withError(exception.getMessage(), ExceptionUtils.getMessage(exception));
            }



            return mr;
        }));
        System.out.println(JSON.toJSONString(modelResult));
    }
}
