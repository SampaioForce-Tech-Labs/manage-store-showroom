package br.com.manage.store.domain.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.manage.store.infrastructure.util.VerifyNotNullUtils.notNull;

@Component
@AllArgsConstructor
public class GenericMapper {

    private final ModelMapper modelMapper;

    public <T, O> T map(O source, Class<T> sourceClass) {
        notNull(List.of(source, sourceClass));
        return modelMapper.map(source, sourceClass);
    }

    public <T, O> List<T> mapAll(List<O> sources, Class<T> sourceClass) {
        notNull(List.of(sources, sourceClass));
        return sources.stream().map(list ->
                modelMapper.map(list, sourceClass)).collect(Collectors.toList());
    }
}
