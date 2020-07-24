//i by Sir_Gav
import(invLib,giveItem,giveItems);




__command() -> (null);

i(item) ->
(
    if
    (
        item == grass, 
        print('you have grass');
        //_giveItem(grass_block, num),
        //else if
        item == pearl,
        _giveItem(ender_pearl);

        //insert special cases here


        //else
        _giveItem(item);

    )  
)


//_giveItems(player,item,num) ->
//(
//    print(item);
//    nextSlot = inventory_find(player());
//    print(nextSlot);
//    inventory_set(player(), nextSlot, null, 1, item);
//    print(item  + 'given');
//);