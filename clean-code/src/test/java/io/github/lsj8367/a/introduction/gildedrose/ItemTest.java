package io.github.lsj8367.a.introduction.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void makeZeroQuality() {
        //given
        Item item = new Item("name", 0, 10);
        //when
        item.zeroQuality();
        //then
        assertThat(item).isEqualTo(new Item("name", 0, 0));
    }

    @Test
    @DisplayName("Sulfuras가 아니고 품질이 0보다 큰 경우 quality +1")
    void notEqualSulfurasThenMinusQuality() {
        //given
        Item item = new Item("Aged Brie", 10, 5);
        //when
        item.isNotEqualSulfurasThenQualityMinus1();
        //then
        assertThat(item).isEqualTo(new Item("Aged Brie", 10, 4));
    }

    @Test
    @DisplayName("name이 Sulfuras가 아닌경우 sellin -1")
    void isNotSulfurasThenMinusSellIn() {
        //given
        final Item item = new Item("Aged Brie", 10, 10);
        //when
        item.isNotEqualSulfurasThenSellinMinus1();
        //then
        assertThat(item).isEqualTo(new Item("Aged Brie", 9, 10));
    }

    @Test
    @DisplayName("최대값 50 을 넘은 경우 아무일도 하지 않음")
    void moreThanMaxValueEarlyReturn() {
        //given
        final Item item = new Item("Aged Brie", 0, 50);
        //when, then
        assertThatCode(item::isBackStageThenIncreaseByCondition)
            .doesNotThrowAnyException();
        assertThat(item).isEqualTo(new Item("Aged Brie", 0, 50));
    }

    @Test
    @DisplayName("최대값 넘지 않고, BackStage가 아닌경우 1 증가")
    void isNotEqualBackStage() {
        //given
        final Item item = new Item("Aged Brie", 0, 30);
        //when
        item.isBackStageThenIncreaseByCondition();
        //then
        assertThat(item).isEqualTo(new Item("Aged Brie", 0, 31));
    }

    @Test
    @DisplayName("최대값 넘지 않고, BackStage이고, 만료 5일 이상 10일 이하인 경우 quality + 2")
    void equalBackStageAndMoreThan5DaysLessThen10Days() {
        //given
        final Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30);
        //when
        item.isBackStageThenIncreaseByCondition();
        //then
        assertThat(item).isEqualTo(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 32));
    }

}