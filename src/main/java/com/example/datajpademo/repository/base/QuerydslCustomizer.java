package com.example.datajpademo.repository.base;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIN;

public interface QuerydslCustomizer<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {
    @Override
    default void customize(QuerydslBindings bindings, @NonNull T root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.bind(LocalDateTime.class)
                .first((SingleValueBinding<DateTimePath<LocalDateTime>, LocalDateTime>) (path, value) -> path.between(value.with(MIN), value.with(MAX)));
    }
}