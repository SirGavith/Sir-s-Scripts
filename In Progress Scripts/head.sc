//head by Sir_Gav
__config()-> ({'stay_loaded' -> true});


__command() -> (
    p = player();
    oldHeadItem = __storeHeadSlot();
    __decreaseStack();
    __putOnHead()

    print(oldHeadItem);
);
__storeHeadSlot() -> (
    inventory_get(player(),39);
);
__decreaseStack() -> (
    if(p~'gamemode' == 'survival' || p~'gamemode' == 'adventure',
        currentCount = p~'holds':1;
        handNum = p~'selected_slot';
        inventory_set(player(), handnum, currentcount-1);
    );
);
__putOnHead() -> (
    item = player~'holds':0
    inventory_set(player(),39,1,item);
    print(item + ' was put on your head');
);
__setHandItem(item) -> (
    
)