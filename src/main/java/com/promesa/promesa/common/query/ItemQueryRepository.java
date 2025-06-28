package com.promesa.promesa.common.query;

import com.promesa.promesa.domain.home.dto.ItemPreviewResponse;
import com.promesa.promesa.domain.item.domain.Item;
import com.promesa.promesa.domain.wish.domain.TargetType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.promesa.promesa.domain.artist.domain.QArtist.artist;
import static com.promesa.promesa.domain.item.domain.QExhibitionItem.exhibitionItem;
import static com.promesa.promesa.domain.item.domain.QItem.item;
import static com.promesa.promesa.domain.item.domain.QItemImage.itemImage;
import static com.promesa.promesa.domain.itemCategory.domain.QItemCategory.itemCategory;
import static com.promesa.promesa.domain.wish.domain.QWish.wish;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 기획전 상품을 위시리스트 여부와 함께 반환
     * @param memberId
     * @return
     */
    public List<ItemPreviewResponse> findExhibitionItem(Long memberId, Long exhibitionId) {
        return queryFactory
                .select(Projections.fields(ItemPreviewResponse.class,
                        item.id.as("itemId"),
                        item.name.as("itemName"),
                        item.description.as("itemDescription"),
                        item.price,
                        itemImage.imageKey,
                        artist.name.as("artistName"),
                        wish.id.isNotNull().as("isWished")
                ))
                .from(exhibitionItem)
                .join(exhibitionItem.item, item)
                .join(item.artist, artist)
                .leftJoin(item.itemImages, itemImage)
                    .on(itemImage.isThumbnail.isTrue())
                .leftJoin(wish)
                    .on(
                            wish.member.id.eq(memberId), // 로그인한 사용자인지
                            wish.targetType.eq(TargetType.ITEM),    // 위시리스트 type이 상품인지
                            wish.targetId.eq(item.id)   // 해당 상품이 위시리스트인지
                    )
                .where(exhibitionItem.exhibition.id.eq(exhibitionId))
                .fetch();
    }

    /**
     * 카테고리 작품 조회
     * @param memberId
     * @param categoryId
     * @return
     */
    public Page<ItemPreviewResponse> findCategoryItem(Long memberId, Long categoryId, Pageable pageable) {
        List<ItemPreviewResponse> content = queryFactory
                .select(Projections.fields(ItemPreviewResponse.class,
                        item.id.as("itemId"),
                        item.name.as("itemName"),
                        item.description.as("itemDescription"),
                        item.price,
                        itemImage.imageKey,
                        artist.name.as("artistName"),
                        wish.id.isNotNull().as("isWished")
                ))
                .from(itemCategory)
                .join(itemCategory.item, item)
                .join(item.artist, artist)
                .leftJoin(item.itemImages, itemImage)
                .on(itemImage.isThumbnail.isTrue())
                .leftJoin(wish)
                .on(
                        wish.member.id.eq(memberId), // 로그인한 사용자인지
                        wish.targetType.eq(TargetType.ITEM),    // 위시리스트 type이 상품인지
                        wish.targetId.eq(item.id)   // 해당 상품이 위시리스트인지
                )
                .where(itemCategory.category.id.eq(categoryId))
                .orderBy(createOrderSpecifiers(pageable.getSort()))
                .offset(pageable.getOffset())   // 몇 번째 페이지부터 보여줄지
                .limit(pageable.getPageSize())  // 페이지마다 몇 개씩 보여줄지
                .fetch();

        //페이징처리를 위한 count 쿼리
        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .from(itemCategory)
                .join(itemCategory.item, item)
                .where(itemCategory.category.id.eq(categoryId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private OrderSpecifier<?>[] createOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> specifiers = new ArrayList<>();

        for (Sort.Order order : sort) {
            String property = order.getProperty();  // 정렬 조건
            Order dicrection = order.isAscending() ? Order.ASC : Order.DESC;    // 오름차순, 내림차순
            PathBuilder path = new PathBuilder(Item.class, "item");

            specifiers.add(new OrderSpecifier(dicrection, path.get(property))); // 복합 정렬
        }

        return specifiers.toArray(new OrderSpecifier[0]);   // List를 Array로 변환
    }
}
