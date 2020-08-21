__config()-> {'stay_loaded' -> true,'scope'->'global'};

//change this value to adjust the radius that blocks start moving toward you
global_pickup_radius = 7;

global_magnet_status = true;

__on_tick()->(
    if(global_magnet_status == true,
        magnet_slot = inventory_find(player(), 'nether_star');
        magnet_info = inventory_get(player(),magnet_slot);
        if(
            magnet_slot != null && __is_magnet('nether_star',magnet_info:2),
            _magnetize();
        );
    );
);

__is_magnet(item_name,item_nbt) -> (
    if(
        //item_name == 'nether_star' && lower(split('"',split('":"',parse_nbt(item_nbt):'display':'Name'):1):0) == 'magnet',
        item_name == 'nether_star' && lower(parse_nbt(parse_nbt(item_nbt):'display':'Name'):'text') == 'magnet',

        return(true),
    );
);

_display_title(target,location,title) -> run('title '+target+' '+location+' '+title);

__on_player_uses_item(player, item_tuple, hand)->(
    if(
        __is_magnet(item_tuple:0,item_tuple:2) == true,
        if(
        global_magnet_status == true,
        global_magnet_status = false; _display_title(player(),'actionbar','"Magnet toggled off"'),
        global_magnet_status = true;  _display_title(player(),'actionbar','"Magnet toggled on"');
        );
    );
);


_magnetize() -> (
    nearby_items = entity_selector('@e[type=item]');
    for(nearby_items,
        item = _;
        is_near = [false,false,false]; //per axis
        for(pos(item)-pos(player()),
            if(abs(_)<global_pickup_radius,is_near:_i = true);
        );
        if(is_near:0 == true && is_near:1 == true && is_near:2 == true,
            vector = pos(player())-pos(item);
            for(vector, vector:_i = _/70);

            modify(item, 'accelerate', vector);
        )
    )
)