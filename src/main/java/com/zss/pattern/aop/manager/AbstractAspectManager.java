package com.zss.pattern.aop.manager;

import com.zss.result.BaseResult;
import com.zss.result.ModelResult;

public abstract class AbstractAspectManager<T, R> {

    public ModelResult<R> around(T t){
        ModelResult<R> modelResult = new ModelResult<>();

        BaseResult beforeResult = before();
        System.out.println("before");

        modelResult = process(t);

        after();

        return modelResult;
    }

    protected abstract BaseResult before();

    protected abstract ModelResult<R> process(T t);

    protected abstract BaseResult after();
}
