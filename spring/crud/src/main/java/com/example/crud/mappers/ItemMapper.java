package com.example.crud.mappers;

import com.example.crud.dto.ItemsDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Insert("INSERT INTO items VALUES(NULL, #{itemName}, #{itemPrice})")
    public void setAddItem(ItemsDto idto);

    @Select("SELECT * FROM items ORDER BY item_id DESC")
    public List<ItemsDto> getListItem();

    @Select("SELECT COUNT(*) FROM items")
    public int getCount();

    @Delete("DELETE FROM items WHERE item_id = #{id}")
    public void deleteItem(int id);

    @Select("SELECT * FROM items WHERE item_id = #{id}")
    public ItemsDto viewItem(int id);

    @Update("UPDATE items SET item_name = #{itemName}, item_price = #{itemPrice} WHERE item_id = #{itemId}")
    public void setUpdateItem(ItemsDto itemsDto);
}
