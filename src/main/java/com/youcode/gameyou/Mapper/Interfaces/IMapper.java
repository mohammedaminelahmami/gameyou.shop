package com.youcode.gameyou.Mapper.Interfaces;

import java.util.List;

public interface IMapper<A, B> {
    B convertAtoB(A a, Class<B> bClass);
    A convertBtoA(B b, Class<A> aClass);
    List<B> convertListAToListB(List<A> listA, Class<B> bClass);
    List<A> convertListBToListA(List<B> listB, Class<A> aClass);
    // Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass);
}