package com.youcode.gameyou.Mapper;

import com.youcode.gameyou.Mapper.Interfaces.IMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mapper<A, B> implements IMapper<A, B> {
    private final ModelMapper mapper;
    @Override
    public A convertBtoA(B b, Class<A> aClass) {
        if(b == null) {
            return null;
        }
        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return mapper.map(b, aClass);
    }

    @Override
    public B convertAtoB(A a, Class<B> bClass) {
        if(a == null) {
            return null;
        }
        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return mapper.map(a, bClass);
    }

    @Override
    public List<A> convertListBToListA(List<B> listB, Class<A> aClass) {
        if(listB == null) {
            return Collections.emptyList();
        }
        return listB.stream().map((entity) -> convertBtoA(entity, aClass)).toList(); // immutable
        // return listEntity.stream().map((entity) -> convertToDTO(entity, dtoClass)).collect(Collectors.toList()); // mutable
    }

    @Override
    public List<B> convertListAToListB(List<A> listA, Class<B> bClass) {
        if(listA == null) {
            return Collections.emptyList();
        }
        return listA.stream().map(dto -> convertAtoB(dto, bClass)).collect(Collectors.toList());
    }

//    @Override
//    public Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass) {
//        if(entityList == null)
//            return Page.empty();
//
//        List<D> all =  entityList.stream().map(entity -> convertToDTO(entity,outClass)).collect(Collectors.toList());
//        return new PageImpl<>(all);
//    }
}
