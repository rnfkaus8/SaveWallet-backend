package io.cloudtype.Demo.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.cloudtype.Demo.controller.request.FindItemsRequest;
import io.cloudtype.Demo.domain.Item;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.cloudtype.Demo.domain.QCategory.category;
import static io.cloudtype.Demo.domain.QItem.item;

@Repository
public class ItemRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ItemRepositorySupport(JPAQueryFactory queryFactory) {
        super(Item.class);
        this.queryFactory = queryFactory;
    }

    public List<Tuple> getCategoryTotalCount(FindItemsRequest request) {
        return queryFactory
                .select(category.name, category.color, item.price.sum())
                .from(item)
                .join(item.category, category)
                .where(item.boughtDate.between(request.getStartDate(), request.getEndDate()))
                .where(item.member.id.eq(request.getMemberId()))
                .groupBy(item.category.name)
                .groupBy(item.category.color)
                .fetch();
    }
}
